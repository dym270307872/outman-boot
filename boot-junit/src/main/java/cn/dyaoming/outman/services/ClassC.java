package cn.dyaoming.outman.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ClassC {

    public String run(){
        return "C"+ new Random().nextInt(10);
    }
}
