package com.wentong.ladder.http;

import okhttp3.ConnectionPool;


// okhttp 连接池，暂时先不用，因为性能要求还没有那么高，如果参数配置不当导致连接异常断开，会对业务产生影响

public class HttpConnectionPool {

    private final ConnectionPool connectionPool = new ConnectionPool();

//    public static void main(String[] args) {
//        HttpConnectionPool httpConnectionPool = new HttpConnectionPool();
//        RealConnectionPool delegate$okhttp = httpConnectionPool.connectionPool.getDelegate$okhttp().;
//        httpConnectionPool.connectionPool.evictAll();
//    }

}
