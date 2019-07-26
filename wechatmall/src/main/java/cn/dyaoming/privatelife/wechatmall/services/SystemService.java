package cn.dyaoming.privatelife.wechatmall.services;

import cn.dyaoming.errors.AppServiceException;
import cn.dyaoming.models.DataResult;
import cn.dyaoming.privatelife.wechatmall.mappers.Cs01Mapper;
import cn.dyaoming.privatelife.wechatmall.models.Cs01;
import cn.dyaoming.privatelife.wechatmall.models.CsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

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
                dataResult.setData(map);
            } else {
                return new DataResult(false, "9011");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppServiceException("9999");
        }
        return dataResult;
    }


    @Cacheable("systemInfo")
    public DataResult getIndex(String openId) throws AppServiceException {
        DataResult dataResult = new DataResult();
        try {
            if (checkSession(openId)) {
                Map<String, Object> map = new HashMap<String, Object>();
//banner图列表
                List<Map> l_banner = new ArrayList<Map>();
                for (int i = 0; i < 4; i++) {
                    Map banner_map = new HashMap();
                    banner_map.put("image", "/images/" + i + ".jpg");
                    banner_map.put("goods_id", "123123" + i);
                    l_banner.add(banner_map);
                }
                map.put("banners", l_banner);
                //菜单按钮列表
                List<Map> l_button = new ArrayList<Map>();
                for (int i = 0; i < 4; i++) {
                    Map button_map = new HashMap();
                    button_map.put("image", "/images/" + i + ".jpg");
                    button_map.put("type", "123123" + i);
                    button_map.put("id", "123123" + i);
                    l_button.add(button_map);
                }
                map.put("buttons", l_button);
                //推荐列表
                List<Map> l_recommend = new ArrayList<Map>();

                Map tj1 = new HashMap();
                tj1.put("title", "本月新品");
                tj1.put("image", "/abcd.png");
                tj1.put("type", "01");
                List<Map> l_m_1 = new ArrayList<Map>();
                for (int i = 0; i < 6; i++) {
                    Map button_map = new HashMap();
                    button_map.put("image", "/images/" + i + ".jpg");
                    button_map.put("goods_id", "123123" + i);
                    l_m_1.add(button_map);
                }
                tj1.put("children", l_m_1);
                l_recommend.add(tj1);

                Map tj2 = new HashMap();
                tj2.put("title", "会员权益");
                tj2.put("image", "/abcd.png");
                tj2.put("type", "01");
                List<Map> l_m_2 = new ArrayList<Map>();
                for (int i = 0; i < 4; i++) {
                    Map button_map = new HashMap();
                    button_map.put("image", "/images/" + i + ".jpg");
                    button_map.put("goods_id", "123123" + i);
                    l_m_2.add(button_map);
                }
                tj2.put("children", l_m_2);
                l_recommend.add(tj2);
                map.put("recommends", l_recommend);
                dataResult.setData(map);
            } else {
                return new DataResult(false, "9011");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppServiceException("9999");
        }
        return dataResult;
    }
}
