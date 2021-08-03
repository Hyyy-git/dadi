package com.ccic.component;

import com.ccic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by TaoHang on 2019/9/16.
 */
@Component
public class RedisLock extends BaseService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 加锁
     * @param targetId   targetId - 唯一标志   message 唯一标识
     * @param timeStamp  当前时间+超时时间 也就是时间戳
     * @return
     */
    public boolean lock(String targetId,String timeStamp){
        if(stringRedisTemplate.opsForValue().setIfAbsent(targetId,timeStamp)){
            // 对应setnx命令，可以成功设置,也就是key不存在
            return true;
        }

        // 判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentLock = stringRedisTemplate.opsForValue().get(targetId);
        // 如果锁过期 currentLock不为空且小于当前时间
        if(!StringUtils.isEmpty(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()){
            // 获取上一个锁的时间value 对应getset，如果lock存在
            String preLock =stringRedisTemplate.opsForValue().getAndSet(targetId,timeStamp);
            // 而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的timeStamp已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(!StringUtils.isEmpty(preLock) && preLock.equals(currentLock) ){
                // preLock不为空且preLock等于currentLock，也就是校验是不是上个对应的商品时间戳，也是防止并发
                return true;
            }
        }
        return false;
    }

}
