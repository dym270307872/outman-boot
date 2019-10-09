package cn.dyaoming.outman.test;


import cn.dyaoming.outman.services.ClassA;
import cn.dyaoming.outman.services.ClassB;
import cn.dyaoming.outman.services.ClassC;
import junit.BaseJunit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestMock extends BaseJunit {

    @Autowired
    @Mock
    private ClassC classC;

    @Autowired
    @InjectMocks
    private ClassA classA;

    @Autowired
    private ClassB classB;

    @Before
    public void beforeC() {
        when(classC.run()).thenReturn(String.valueOf(new Random().nextInt(10) * 10));
    }


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        //这一步的作用是将Mocks注解和InjectMocks注解的对象自动装配

    }


      /*  @Test
    public void mainA() {
        System.out.println(classA.run());
    }*/

    @Test
    public void mainB() {
        System.out.println(classB.run());
    }

    @Test
    public void mainAMock() {
        System.out.println(classA.run());
    }

    /*@Test
    public void mainBMock() {
        System.out.println(classB.run());
    }*/

    @Test
    public void mainCMock() {
        System.out.println(classC.run());
    }


}
