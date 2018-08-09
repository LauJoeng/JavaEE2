package com.yang.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestPing {

    public  static void main(String args[]){
//        Jedis jedis = new Jedis("192.168.22.129",6379);
//        System.out.println(jedis.ping());
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set("aa","bb");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPool,jedis);
        }


    }
}
