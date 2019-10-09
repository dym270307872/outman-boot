package cn.dyaoming.outman.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ClassA {

    @Autowired
    private ClassC classC;

    public String run(){
        return "A" + classC.run();
    }

}
