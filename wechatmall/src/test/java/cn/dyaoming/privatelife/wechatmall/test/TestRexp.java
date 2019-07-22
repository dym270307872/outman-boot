package cn.dyaoming.privatelife.wechatmall.test;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestRexp {

public static void main(String[] args){
    String a="lo523ve23n12e234534xt234csdn3423jav16432aeye";
    String regEx="[^0-9]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(a);

    String str = StringUtils.join(Arrays.asList(m.replaceAll("").split ("")).stream().distinct().sorted().collect(Collectors.toList()), ",");
    System.out.println(str);

}

}
