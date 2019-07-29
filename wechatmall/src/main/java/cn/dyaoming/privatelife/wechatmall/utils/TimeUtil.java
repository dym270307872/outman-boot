package cn.dyaoming.privatelife.wechatmall.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static Timestamp parse(String dateTime,String regEx){
        try {
            DateFormat sdf = new SimpleDateFormat(regEx);
            Date date = sdf.parse(dateTime);
            return new Timestamp(date.getTime());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args){
        System.out.println(parse("2019-07-29","yyyy-MM-dd"));
    }
}
