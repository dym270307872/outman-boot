package cn.dyaoming.privatelife.wechatmall.controllers;


import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.models.ApiResult;
import cn.dyaoming.privatelife.wechatmall.services.ShopService;
import cn.dyaoming.privatelife.wechatmall.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;


@RestController
@RequestMapping("/api")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopService;



	@RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
	public ApiResult getGoodsList(String openId, String goodsName, String goodsType, String type) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.getGoodsList(openId, goodsName, goodsType, type);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, "9999");
		}
		return apiResult;
	}



	@RequestMapping(value = "/getGoods", method = RequestMethod.GET)
	public ApiResult getGoods(String openId, String goodsId) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId) || isNull(goodsId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.getGoods(openId, goodsId);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, "9999");
		}
		return apiResult;
	}



	@RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
	public ApiResult sendOrder(String openId, String param) {
		ApiResult apiResult = new ApiResult();
		try {
			//param示例：{"name":"董耀明","phoneNum":"18603928785","address":"河南省郑州市金水区","remark":"快点送货","ydsj":"2019-07-30","goodsList":["2019072821351|5","2019072821350|2","2019072821355|1","2019072821358|1"]}
			//            param = "{\"name\":\"董耀明\",\"phoneNum\":\"18603928785\",\"address\":\"河南省郑州市金水区\",\"remark\":\"快点送货\",\"ydsj\":\"2019-07-30\",\"goodsList\":[\"2019072821351|5\",\"2019072821350|2\",\"2019072821355|1\",\"2019072821358|1\"]}";
			if (isNull(openId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.sendOrder(openId, param);
		} catch(AppServiceException e) {
			apiResult = new ApiResult(false, e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, "9999");
		}
		return apiResult;
	}



	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	public ApiResult getOrderList(String openId, String type, String pageNum) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId)) {
				return new ApiResult(false, "9015");
			}

			if (isNull(pageNum) || "".equals(pageNum)) {
				pageNum = "1";
			} else if (Integer.valueOf(pageNum) < 1) {
				pageNum = "1";
			}
			return shopService.getOrderList(openId, type, Integer.valueOf(pageNum));
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, "9999");
		}
		return apiResult;
	}



	@RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
	public ApiResult getOrderInfo(String openId, String orderId) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId) || isNull(orderId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.getOrderInfo(openId, orderId);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, "9999");
		}
		return apiResult;
	}



	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	public ApiResult deleteOrder(String openId, String orderId) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId) || isNull(orderId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.deleteOrder(openId, orderId);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, "9999");
		}
		return apiResult;
	}



	@RequestMapping(value = "/getSnapshot", method = RequestMethod.GET)
	public ApiResult getSnapshot(String openId, String snapshotId) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId) || isNull(snapshotId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.getSnapshot(openId, snapshotId);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, "9999");
		}
		return apiResult;
	}



	@RequestMapping(value = "/getShopCart", method = RequestMethod.GET)
	public ApiResult getShopCart(String openId) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.getShopCart(openId);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, e.getMessage());
		}
		return apiResult;
	}



	@RequestMapping(value = "/addShopCart", method = RequestMethod.POST)
	public ApiResult addShopCart(String openId, String goodsId, String amount) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId) || isNull(goodsId) || isNull(amount)) {
				return new ApiResult(false, "9015");
			}
			return shopService.addShopCart(openId, goodsId, amount);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, e.getMessage());
		}
		return apiResult;
	}



	@RequestMapping(value = "/deleteShopCart", method = RequestMethod.POST)
	public ApiResult deleteShopCart(String openId, String goodsIds) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId) || isNull(goodsIds)) {
				return new ApiResult(false, "9015");
			}
			return shopService.deleteShopCart(openId, goodsIds);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, e.getMessage());
		}
		return apiResult;
	}



	@RequestMapping(value = "/changeShopCart", method = RequestMethod.POST)
	public ApiResult changeShopCart(String openId, String goodsId, String amount) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId) || isNull(goodsId) || isNull(amount)) {
				return new ApiResult(false, "9015");
			}
			return shopService.changeShopCart(openId, goodsId, amount);
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, e.getMessage());
		}
		return apiResult;
	}



	@RequestMapping(value = "/payOrder", method = RequestMethod.POST)
	public ApiResult payOrder(String openId, String orderId) {
		ApiResult apiResult = new ApiResult();
		try {
			if (isNull(openId) || isNull(orderId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.payOrder(openId, orderId);
		} catch(AppServiceException e) {
			apiResult = new ApiResult(false, e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			apiResult = new ApiResult(false, "9999");
		}
		return apiResult;
	}



	/**
	 * 此函数会被执行多次，如果支付状态已经修改为已支付，则下次再调的时候判断是否已经支付，如果已经支付了，则什么也执行
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/paymentCallback", produces = MediaType.APPLICATION_JSON_VALUE)
	public String paymentCallback(HttpServletRequest request, HttpServletResponse response) throws
			IOException {
		try {
			System.out.println("微信支付回调");
			InputStream inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			String resultxml = new String(outSteam.toByteArray(), "utf-8");
			Map<String, String> params = WXPayUtil.xmlToMap(resultxml);
			outSteam.close();
			inStream.close();

			return WXPayUtil.mapToXml(shopService.paymentCallback(params));

		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	@RequestMapping(value = "/getPsDate", method = RequestMethod.GET)
	public ApiResult getPsDate(String openId,String ssqy) {
		try {
			if (isNull(openId)) {
				return new ApiResult(false, "9015");
			}
			return shopService.getPsDate(openId,ssqy);
		} catch(Exception e) {
			return new ApiResult(false, e.getMessage());
		}

	}
}
