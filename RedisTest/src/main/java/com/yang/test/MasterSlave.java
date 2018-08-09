package com.yang.test;

import redis.clients.jedis.Jedis;

public class MasterSlave {
    public static void main(String[] args){
        Jedis jedis_M = new Jedis("192.168.22.129",6379);
        Jedis jedis_S = new Jedis("192.168.22.129",6380);

        jedis_S.slaveof("192.168.22.129",6379);
        jedis_M.set("class","1122");
        String result = jedis_S.get("class");
        System.out.println(result);
    }
}
