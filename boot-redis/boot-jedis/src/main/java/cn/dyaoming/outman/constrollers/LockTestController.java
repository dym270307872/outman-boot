package cn.dyaoming.outman.constrollers;

import cn.dyaoming.outman.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lockTest")
public class LockTestController {


    @Autowired
    private LockService lockService;

    @RequestMapping("/random")
    public String random(String name) throws Exception {
        return lockService.testLock(name);
    }
}
