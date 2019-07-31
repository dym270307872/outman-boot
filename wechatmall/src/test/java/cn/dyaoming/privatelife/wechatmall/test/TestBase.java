package cn.dyaoming.privatelife.wechatmall.test;

public class TestBase {


    public static void main(String[] args){

        String ssqy = "410100:410141:892364";
        ssqy = ssqy.substring(ssqy.lastIndexOf(":")+1);

        System.out.println(ssqy);
    }
}
