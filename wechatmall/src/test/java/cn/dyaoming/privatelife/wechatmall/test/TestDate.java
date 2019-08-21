package cn.dyaoming.privatelife.wechatmall.test;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestDate {


    public static void main(String[] args){

        List<String> l_date = new ArrayList<String>();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date today = new Date();
        int s = 0;
        if(today.getHours()>=17){
            s += 1;
        }
        for(int i=s;i<7;i++){
            cal.setTime(today);
            cal.add(Calendar.DAY_OF_MONTH, i);
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
            if (dayWeek == 2) {
                l_date.add(sdf.format(cal.getTime())+"(周二)");
            }
            if (dayWeek == 3) {
                l_date.add(sdf.format(cal.getTime())+"(周三)");
            }
            if (dayWeek == 5) {
                l_date.add(sdf.format(cal.getTime())+"(周五)");
            }
            if (dayWeek == 6) {
                l_date.add(sdf.format(cal.getTime())+"(周六)");
            }

        }

        System.out.println(JSON.toJSONString(l_date));
    }
}
