/*
package cn.dyaoming.outman.constrollers;

import cn.dyaoming.outman.service.ThreadService;
import cn.dyaoming.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/testEffectiveness")
public class TestEffectiveness {

    @Autowired
    private ThreadService threadService;


    @RequestMapping("/")
    public String main(){

        String key = UUID.randomUUID().toString().replace("-","");
        String value = RandomUtil.randomNumChar(Integer.valueOf(RandomUtil.randomNum(5)));
        threadService.run(key,value);

        return "success";
    }
}
*/
