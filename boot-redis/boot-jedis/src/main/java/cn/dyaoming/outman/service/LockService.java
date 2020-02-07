//package cn.dyaoming.outman.service;
//
//import cn.dyaoming.outman.dao.RedisLock;
//import cn.dyaoming.utils.RandomUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class LockService {
//
//    @Autowired
//    private RedisLock redisLock;
//
//
//    public String testLock(String key) throws Exception{
//        String seqid = UUID.randomUUID().toString();
//        try {
//
//            if (redisLock.lock(key, seqid)) {
//
//                Thread.sleep(200);
//
//                return RandomUtil.randomNumChar(16);
//            } else {
//                return "当前访问人数过多";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
////            return "当前访问人数过多";
//        } finally {
//            redisLock.unLock(key, seqid);
//        }
//
//
//    }
//
//}
