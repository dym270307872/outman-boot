package cn.dyaoming.privatelife.wechatmall.services;


import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.models.ApiResult;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.mappers.Hy01Mapper;
import cn.dyaoming.privatelife.wechatmall.mappers.Hy02Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Acb02;
import cn.dyaoming.privatelife.wechatmall.models.Hy01;
import cn.dyaoming.privatelife.wechatmall.models.Hy02;
import cn.dyaoming.privatelife.wechatmall.models.HyInfo;
import cn.dyaoming.privatelife.wechatmall.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;


@Service
public class Hy01Service extends BaseService {

	@Autowired
	private Hy01Mapper   hy01Mapper;
	@Autowired
	private Hy02Mapper   hy02Mapper;
	@Autowired
	private Acb02Service acb02Service;



	@Cacheable("userInfo")
	public DataResult getUserInfo(String openId) throws AppServiceException {
		DataResult dataResult = new DataResult();
		try {
			//TODO 调整绑定验证逻辑
			DataResult bdInfo = acb02Service.checkBind(openId);

			if (bdInfo.isFlag()) {
				//存在绑定关系
				Acb02 acb02 = (Acb02) bdInfo.getData();
				HyInfo hyInfo = getHyInfo(acb02.getHya001());
				dataResult.setData(hyInfo);
			} else {
				throw new AppServiceException("9021");
			}

			/*if (cacheDao.exists("cache:openId:" + openId)) {
				Map map = (Map) cacheDao.getCacheData("cache:openId:" + openId);
				dataResult.setData(map);
			} else {
				return new DataResult(false, "9021");
			}*/
		} catch(Exception e) {
			throw new AppServiceException(e.getMessage());
		}
		return dataResult;
	}



	private HyInfo getHyInfo(String hya001) {
		HyInfo hyInfo = new HyInfo();
		try {
			Hy01 hy01 = hy01Mapper.findById(hya001);
			if (hy01 != null) {
				hyInfo.setHyId(hy01.getHya001());
				hyInfo.setHyCardId(hy01.getHya002());
				hyInfo.setHyName(hy01.getHya007());
				hyInfo.setPhoneNum(hy01.getHya011());
				hyInfo.setYe(hy01.getHya021());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return hyInfo;
	}



	public DataResult binding(String acbb002, String openId, String hya011, String hya003) {
		DataResult dataResult = new DataResult();
		try {
			//根据手机号查询用户信息
			Hy01 hy01 = hy01Mapper.getHyInfoByNum(hya011);
			if (hy01 != null) {
				//判断用户名密码是否一致
				if (hy01.getHya003().equals(EncryptionUtil.encryptPassword(hya003))) {

					Acb02 acb02 = new Acb02();
					acb02.setHya001(hy01.getHya001());
					acb02.setCreater(hy01.getHya001());
					acb02.setAcbb002(acbb002);
					acb02.setAcbb003(openId);
					ApiResult result = acb02Service.toBind(acb02);

					if (result.isFlag()) {
						HyInfo hyInfo = new HyInfo();
						hyInfo.setHyId(hy01.getHya001());
						hyInfo.setHyCardId(hy01.getHya002());
						hyInfo.setHyName(hy01.getHya007());
						hyInfo.setPhoneNum(hy01.getHya011());
						hyInfo.setYe(hy01.getHya021());
						dataResult.setData(hyInfo);
					}
				} else {
					return new DataResult(false, "9016");
				}
			} else {
				return new DataResult(false, "9016");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dataResult;
	}



	@Transactional
	public ApiResult register(String phoneNumber, String password) {
		ApiResult apiResult = new ApiResult();
		try {
			HyInfo hyInfo = getHyInfoByNum(phoneNumber);

			if (hyInfo != null) {
				return new ApiResult(false, "9025");
			} else {
				//生成临时会员卡信息
				Hy02 hy02 = new Hy02();
				String hyb001 = hy02Mapper.autoKey();
				hy02.setHyb001(hyb001);
				hy02.setHyb002(hyb001);
				hy02.setHyb003(password);
				//初始金额0.00
				hy02.setHyb006(new BigDecimal(0));
				hy02.setHyb016("1");
				hy02.setHyb017("1");
				hy02.setHyb018("system");
				hy02.setHyb020(new BigDecimal(0));
				hy02.setHyb021(new Timestamp(new Date().getTime()));
				hy02.setHyb026(new Timestamp(new Date().getTime()));
				hy02.setHyb027("1");
				hy02.setHyb028("system");
				hy02.setHyb031(new Timestamp(new Date().getTime()));
				hy02.setHyb032("1");
				hy02.setHyb033("system");
				hy02.setHyb046("1");
				hy02.setHyb048(new Timestamp(new Date().getTime()));
				hy02.setHyb049("system");

				hy02Mapper.insert(hy02);

				//生成会员信息
				Hy01 hy01 = new Hy01();
				hy01.setHya001(hy01Mapper.autoKey());
				hy01.setHya002(hyb001);
				hy01.setHya003(EncryptionUtil.encryptPassword(password));
				hy01.setHya007(hyb001);
				hy01.setHya011(phoneNumber);
				hy01.setHya016("1");
				hy01.setHya021(hy02.getHyb006());
				hy01.setHya031(new Timestamp(new Date().getTime()));
				hy01.setHya032("1");
				hy01.setHya033("system");
				hy01.setHya042("0");
				hy01.setHya048(new Timestamp(new Date().getTime()));
				hy01.setHya049("system");

				hy01Mapper.insert(hy01);
			}

		} catch(Exception e) {
			apiResult = new ApiResult(false, "9025");
		}
		return apiResult;
	}



	private HyInfo getHyInfoByNum(String hya011) {
		HyInfo hyInfo = new HyInfo();
		try {
			Hy01 hy01 = hy01Mapper.getHyInfoByNum(hya011);
			if (hy01 != null) {
				hyInfo.setHyId(hy01.getHya001());
				hyInfo.setHyCardId(hy01.getHya002());
				hyInfo.setHyName(hy01.getHya007());
				hyInfo.setPhoneNum(hy01.getHya011());
				hyInfo.setYe(hy01.getHya021());
			}else{
				hyInfo = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return hyInfo;
	}

}
