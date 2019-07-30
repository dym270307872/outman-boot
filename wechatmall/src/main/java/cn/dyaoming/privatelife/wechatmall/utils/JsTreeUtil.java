package cn.dyaoming.privatelife.wechatmall.utils;

import cn.dyaoming.privatelife.wechatmall.models.CsInfo;

import java.util.ArrayList;
import java.util.List;

public class JsTreeUtil {

    public static List<CsInfo> transform(List<CsInfo> lCsInfo) {
        List<CsInfo> rjsTree = new ArrayList<CsInfo>();
        try {

            lCsInfo.forEach((p) -> {
                    setChildren(lCsInfo, p);
                    long i = lCsInfo.stream()
                            .filter((t) -> (t.getId() == p.getPid() || t.getId().equals(p.getPid()))).count();
                    if (i == 0L) {
                        p.setPid("");
                        rjsTree.add(p);
                    }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rjsTree;

    }


    private static void setChildren(List<CsInfo> lCsInfo, CsInfo csInfo) {
        lCsInfo.stream()
                .filter((t) -> (t.getPid() == csInfo.getId() || csInfo.getId().equals(t.getPid())))
                .forEach((t) -> {
//                    setChildren(ljstree, t);
                    csInfo.addChild(t);
                });
    }

    public static void main(String[] args) throws Exception {


    }

}
