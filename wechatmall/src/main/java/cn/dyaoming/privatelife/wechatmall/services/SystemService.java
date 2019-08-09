package cn.dyaoming.privatelife.wechatmall.services;

import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.mappers.Cs01Mapper;
import cn.dyaoming.privatelife.wechatmall.entitys.Cs01;
import cn.dyaoming.privatelife.wechatmall.models.CsInfo;
import cn.dyaoming.privatelife.wechatmall.utils.JsTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SystemService extends BaseService {

    @Autowired
    private Cs01Mapper cs01Mapper;

    @Cacheable("systemInfo")
    public DataResult getParam(String openId) throws AppServiceException {
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                List<Cs01> l_cs01 = cs01Mapper.selectAll();
                Map<String, List<CsInfo>> map = new HashMap<String, List<CsInfo>>();
                l_cs01.stream().filter(p -> ("1".equals(p.getCsa017()))).forEach((f) -> {
                    if (map.get(f.getCsa006()) == null) {
                        map.put(f.getCsa006(), new ArrayList<CsInfo>());
                    }
                    ((List<CsInfo>) map.get(f.getCsa006())).add(f.toInfo());
                });

                //会员区域处理
                if (map.get("HYQY") != null) {
                    List<CsInfo> hyqy = (List<CsInfo>)map.get("HYQY");
                    map.put("HYQY", JsTreeUtil.transform(hyqy));
                }

                dataResult.setData(map);
            } else {
                throw new AppServiceException("9011");
            }
        } catch (AppServiceException e) {
            e.printStackTrace();
            throw new AppServiceException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppServiceException("9999");
        }
        return dataResult;
    }


    @Cacheable(value = "systemInfo",key = "'homeParam'")
    public DataResult getIndex(String openId) throws AppServiceException {
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                Map<String, Object> map = new HashMap<String, Object>();
//banner图列表
                List<Map> l_banner = new ArrayList<Map>();
                for (int i = 0; i < 4; i++) {
                    Map banner_map = new HashMap();
                    banner_map.put("image", "/statics/images/banner" + i + ".jpg");
                    banner_map.put("goods_id", "123123" + i);
                    l_banner.add(banner_map);
                }
                map.put("banners", l_banner);
                //菜单按钮列表
                List<Map> l_button = new ArrayList<Map>();
                Map button_map0 = new HashMap();
                button_map0.put("image", "/statics/icons/button0.png");
                button_map0.put("name", "每日十件");
                button_map0.put("url", "/pages/classify/classify");
                button_map0.put("typeId", "4");
                l_button.add(button_map0);
                Map button_map1 = new HashMap();
                button_map1.put("image", "/statics/icons/button1.png");
                button_map1.put("name", "精选商品");
                button_map1.put("url", "/pages/classify/classify");
                button_map1.put("typeId", "5");
                l_button.add(button_map1);
                Map button_map2 = new HashMap();
                button_map2.put("image", "/statics/icons/button2.png");
                button_map2.put("name", "我的预定");
                button_map2.put("url", "/subPackages/member/reserve/reserve");
                button_map2.put("typeId", "");
                l_button.add(button_map2);
                Map button_map3 = new HashMap();
                button_map3.put("image", "/statics/icons/button3.png");
                button_map3.put("name", "会员中心");
                button_map3.put("url", "/pages/info/info");
                button_map3.put("typeId", "");
                l_button.add(button_map3);

                map.put("buttons", l_button);
                //推荐列表
                List<Map> l_recommend = new ArrayList<Map>();

                Map tj1 = new HashMap();
                tj1.put("title", "本月新品");
                tj1.put("image", "/statics/upload/title0.png");
                tj1.put("url", "/pages/classify/classify");
                tj1.put("typeId", "4");
                List<Map> l_m_1 = new ArrayList<Map>();
                for (int i = 0; i < 6; i++) {
                    Map button_map = new HashMap();
                    button_map.put("image", "/statics/upload/sp00" + i + ".jpg");
                    button_map.put("goods_id", "201707270018");
                    l_m_1.add(button_map);
                }
                tj1.put("children", l_m_1);
                l_recommend.add(tj1);

                Map tj2 = new HashMap();
                tj2.put("title", "会员权益");
                tj2.put("image", "/statics/upload/title1.png");
                tj2.put("url", "/pages/classify/classify");
                tj2.put("typeId", "6");
                List<Map> l_m_2 = new ArrayList<Map>();
                for (int i = 0; i < 4; i++) {
                    Map button_map = new HashMap();
                    button_map.put("image", "/statics/upload/sp00" + i + ".jpg");
                    button_map.put("goods_id", "201707270015");
                    l_m_2.add(button_map);
                }
                tj2.put("children", l_m_2);
                l_recommend.add(tj2);
                map.put("recommends", l_recommend);
                dataResult.setData(map);
            } else {
                throw new AppServiceException("9011");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppServiceException("9999");
        }
        return dataResult;
    }
}
