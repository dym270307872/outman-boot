package cn.dyaoming.privatelife.wechatmall.test;

public class TestRegEx {


    public static void main(String[] args){
        String p = "201707270017|5";
        String[] goods = p.toString().split("\\|");

        for (String a:goods
             ) {
            System.out.println(a);
        }
    }
}
