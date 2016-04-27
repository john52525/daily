package com.kidoo.daily.service.Iml;

import com.kidoo.daily.service.AbstractBaseRedisService;
import com.kidoo.daily.service.RedisVisitorService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisVisitorServiceImpl extends AbstractBaseRedisService<String, String> implements RedisVisitorService {

    @Override
    public boolean addVisitorRegisterTime(final String ip) {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = getRedisSerializer();
                byte[] key = redisSerializer.serialize(ip + "VisitorRegisterTime");
                byte[] value = redisSerializer.serialize(1 + "");
                return redisConnection.setNX(key, value);
            }
        });
        return result;
    }

    @Override
    public Integer getVisitorRegisterTime(final String ip) {
        Integer result = redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = getRedisSerializer();
                byte[] key = redisSerializer.serialize(ip + "VisitorRegisterTime");
                byte[] value = redisConnection.get(key);
                if (value == null) {
                    return null;
                }
                return Integer.parseInt(new String(value));
            }
        });
        return result;
    }

}
