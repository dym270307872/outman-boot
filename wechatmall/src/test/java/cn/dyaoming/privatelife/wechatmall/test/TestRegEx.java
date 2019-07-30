package cn.dyaoming.privatelife.wechatmall.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegEx {


    public static void main(String[] args){
        String num = "012.1235120";
        String regEx = "^([0-9]*)|(([0-9]+)([.])([0-9]*))$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(num);
        System.out.println(m.matches());
    }
}
