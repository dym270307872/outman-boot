package cn.dyaoming.outman.rpc.junits;


import cn.dyaoming.outman.rpc.Bootstrap;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Bootstrap.class)
@WebAppConfiguration
public class BaseJunit {

}
