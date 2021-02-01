package cn.dyaoming.outman.async.service;

import static org.junit.Assert.*;

import java.util.concurrent.Future;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FutureServiceTest extends BaseJunit{

    @Autowired
    private FutureService futureService;
    
    @Test
    public void tryGetFuture() {
        String name = "hello java";
        
        Future<String> future = futureService.tryFuture(name);
        
        try {
            String result = future.get();
            assertEquals(name,result);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void reTryGetFuture() {
        String name = "hello java";
        
        Future<String> future = futureService.tryFuture(name);
        
        try {
            String result = future.get();
            assertEquals(name,result);
            
            String result2 = future.get();
            assertEquals(name,result2);
            
            String result3 = future.get();
            assertEquals(name,result3);
            
            String result4 = future.get();
            assertEquals(name,result4);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
