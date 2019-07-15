package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.models.HyInfo;
import cn.dyaoming.privatelife.wechatmall.models.Sq01;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService extends BaseService {

	@Autowired
	private AccessService accessService;
	@Autowired
	private Hy01Service   hy01Service;
	@Autowired
	private Acb02Service  acb02Service;



	public ApiResult register(String accessToken, String phoneNumber, String password) {
		ApiResult apiResult = new ApiResult();
		try {
			if (accessService.check(accessToken)) {
				phoneNumber = accessService.decrypt(accessToken, phoneNumber);
				password = accessService.decrypt(accessToken, password);
			} else {
				return new DataResult(false, "9011");
			}

			apiResult = hy01Service.register(phoneNumber,password);

		} catch(Exception e) {

		}
		return apiResult;
	}



	public DataResult getUserInfo(String accessToken, String openId) {
		DataResult dataResult = new DataResult();
		try {
			if (accessService.check(accessToken)) {
				openId = accessService.decrypt(accessToken, openId);
			} else {
				dataResult = new DataResult(false, "9011");
			}

			dataResult = hy01Service.getUserInfo(openId);

		} catch(Exception e) {
			//			e.printStackTrace();
			dataResult = new DataResult(false, e.getMessage());
		}
		return dataResult;
	}



	public DataResult binding(String accessToken, String openId, String phoneNumber,
			String password) {
		DataResult dataResult = new DataResult();
		try {
			if (accessService.check(accessToken)) {
				openId = accessService.decrypt(accessToken, openId);
				phoneNumber = accessService.decrypt(accessToken, phoneNumber);
				password = accessService.decrypt(accessToken, password);

			} else {
				return new DataResult(false, "9011");
			}
			Sq01 sq01 = accessService.getSqInfo(accessToken);
			//判断是否已经绑定
			if (acb02Service.checkBind(openId).isFlag()) {
				//微信已绑定用户账号
				DataResult hyData = hy01Service.getUserInfo(openId);
				//判断绑定的用户账号是否与当前要绑定用户账号一致
				HyInfo hyInfo = (HyInfo) hyData.getData();
				if (phoneNumber.equals(hyInfo.getPhoneNum())) {
					return hyData;
				} else {
					return new DataResult(false, "9024");
				}

			} else {
				//未绑定，继续验证数据库判断用户账号是否绑定其他微信
				if (acb02Service.checkBindByHy(sq01.getSqa001(), phoneNumber).isFlag()) {
					//用户账号已绑定其他微信
					return new DataResult(false, "9024");
				} else {
					//用户账号未绑定其他微信即用户和微信都处于未绑定状态
					return hy01Service.binding(sq01.getSqa001(), openId, phoneNumber, password);
				}

			}

		} catch(Exception e) {
			e.printStackTrace();
			dataResult = new DataResult(false, e.getMessage());
		}
		return dataResult;
	}

}
