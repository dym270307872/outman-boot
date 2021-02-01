package cn.dyaoming.outman.service;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dyaoming.errors.AppMessageException;
import cn.dyaoming.outman.entity.TempDO;
import cn.dyaoming.outman.mapper.TempMapper;


/**
 * <p>
 * 演示业务类
 * </p>
 * 
 * @author DYAOMING
 * @since 2020-09-13
 * @version V1.0
 */
@Service
@Transactional
public class DemoService {

    @Autowired
    private TempMapper tempMapper;



    public String noException() {
        TempDO record = new TempDO();
        record.setCode(String.format("%04d", new Random().nextInt(1000)));
        record.setMsg("shuoming");
        tempMapper.insertDO(record);
        try {
            Thread.sleep(10);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "success";
    }



    public String hasException() {
        String result = noException();
        throw AppMessageException.create("asdf");
    }



    public String hasExceptionButCatch() {
        try {
            String result = noException();
            throw new AppMessageException("asdf");
        } catch (Exception e) {

        }
        return "success";
    }
}
