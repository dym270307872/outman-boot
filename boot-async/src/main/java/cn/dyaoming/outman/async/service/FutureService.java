package cn.dyaoming.outman.async.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class FutureService {

    @Async
    public Future<String> tryFuture(String name){
        return new AsyncResult<>(name);
    }
    
    
}
