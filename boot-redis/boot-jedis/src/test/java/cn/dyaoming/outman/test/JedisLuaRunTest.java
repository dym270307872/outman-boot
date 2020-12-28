package cn.dyaoming.outman.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.redisson.RedissonLock;
import org.redisson.api.RFuture;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.client.protocol.RedisStrictCommand;
import org.redisson.pubsub.LockPubSub;
import org.springframework.beans.factory.annotation.Autowired;

import junit.BaseJunit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisLuaRunTest extends BaseJunit {

	@Autowired
	private JedisPool jedisPool;

	@Test
	public void tryLockTest() {
		System.out.println(tryLock("localhost1","1000000", 60));
		System.out.println(tryLock("localhost1","1000000", 60));
	}
	
	
	
	@Test
	public void releaceLockTest() {
		System.out.println(releaceLock("localhost1","1000000"));
	}
	
	private boolean tryLock(String key, String value, long time) {
		Jedis jedis = jedisPool.getResource();

		Object obj = jedis.eval(
				"if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[1], 1); redis.call('pexpire', KEYS[1], ARGV[2]); return 'OK'; end;"
				+ " if (redis.call('hexists', KEYS[1], ARGV[1]) == 1) then "
				+ "local ttl = redis.call('pttl', KEYS[1])"
				+ "redis.call('hincrby', KEYS[1], ARGV[1], 1); redis.call('pexpire', KEYS[1], ARGV[2] + ttl); return 'OK'; end;"
				+ " return 'ERROR';",
				1, key, value, String.valueOf(time * 1000));
		System.out.println(obj);
		jedis.close();
		return "OK".equals(obj);
	}
	
	private boolean releaceLock(String key, String value) {
		Jedis jedis = jedisPool.getResource();

		Object obj = jedis.eval("if (redis.call('exists', KEYS[1]) == 0) then return 'OK'; end;"
				+ "if (redis.call('hexists', KEYS[1], ARGV[1]) == 0) then return 'OK';end; "
				+ "local counter = redis.call('hincrby', KEYS[1], ARGV[1], -1); "
				+ "if (counter > 0) then return 'OK'; else redis.call('del', KEYS[1]);  return 'OK'; end;"
				+ " return 'ERROR';", 1, key, value);
		System.out.println(obj);
		jedis.close();
		return "OK".equals(obj);
	}
//	<T> RFuture<T> tryLockInnerAsync(long leaseTime, TimeUnit unit, long threadId, RedisStrictCommand<T> command) {
//        internalLockLeaseTime = unit.toMillis(leaseTime);
//
//        return commandExecutor.evalWriteAsync(getName(), LongCodec.INSTANCE, command,
//                  "if (redis.call('exists', KEYS[1]) == 0) then " +
//                      "redis.call('hset', KEYS[1], ARGV[2], 1); " +
//                      "redis.call('pexpire', KEYS[1], ARGV[1]); " +
//                      "return nil; " +
//                  "end; " +
//                  "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
//                      "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
//                      "redis.call('pexpire', KEYS[1], ARGV[1]); " +
//                      "return nil; " +
//                  "end; " +
//                  "return redis.call('pttl', KEYS[1]);",
//                    Collections.<Object>singletonList(getName()), internalLockLeaseTime, getLockName(threadId));
//    }
//	
//	protected RFuture<Boolean> unlockInnerAsync(long threadId) {
//        return commandExecutor.evalWriteAsync(getName(), LongCodec.INSTANCE, RedisCommands.EVAL_BOOLEAN,
//                "if (redis.call('exists', KEYS[1]) == 0) then " +
//                    "redis.call('publish', KEYS[2], ARGV[1]); " +
//                    "return 1; " +
//                "end;" +
//                "if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then " +
//                    "return nil;" +
//                "end; " +
//                "local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); " +
//                "if (counter > 0) then " +
//                    "redis.call('pexpire', KEYS[1], ARGV[2]); " +
//                    "return 0; " +
//                "else " +
//                    "redis.call('del', KEYS[1]); " +
//                    "redis.call('publish', KEYS[2], ARGV[1]); " +
//                    "return 1; "+
//                "end; " +
//                "return nil;",
//                Arrays.<Object>asList(getName(), getChannelName()), LockPubSub.unlockMessage, internalLockLeaseTime, getLockName(threadId));
//
//    }
}
