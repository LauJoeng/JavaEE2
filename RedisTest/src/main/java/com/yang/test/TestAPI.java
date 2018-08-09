package com.yang.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Set;

public class TestAPI {
    public  static void main(String args[]){
        Jedis jedis = new Jedis("192.168.22.129",6379);
//        jedis.set("k1","v1");
//        jedis.set("k2","v2");
//        jedis.set("k3","v2");
//
//        System.out.println(jedis.get("k1"));
//        Set<String> keys = jedis.keys("*");
//        System.out.println(keys.size());
//        jedis.mset("k1","v1","k2","v2");
//        List<String> mylist = jedis.lrange("mylist", 0, -1);
//        jedis.ttl("k1");
        Transaction transaction = jedis.multi();
        transaction.set("k4","v4");
        transaction.set("k5","v5");
        transaction.set("k6","v6");
        transaction.exec();

    }
}
