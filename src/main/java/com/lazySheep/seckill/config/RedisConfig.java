package com.lazySheep.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author LazySheep
 * @version V1.0
 * @Package com.lazySheep.seckill.config
 * @date 2025/6/20 19:39
 * @project seckill
 * @description
 */
//把 session 信息提取出来存到 redis 中
//主要实现序列化, 这里是以常规操作
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory
                                                              redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //设置相应 key 的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //value 序列化
        //redis 默认是 jdk 的序列化是二进制,这里使用的是通用的 json 数据,不用传具体的序化的对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //设置相应的 hash 序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //注入连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}