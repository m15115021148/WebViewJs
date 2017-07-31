package com.sitemap.locationgather.config;

/**
 * @author zhangfan
 * @ClassName: WebHostConfig.java
 * @Description: 网络ip、port配置文件
 * @Date 2017-1-11
 */

public class WebHostConfig {

//    private static final String HOST_ADDRESS = "http://192.168.2.136:8080/";//内网
    private static final String HOST_ADDRESS = "http://218.202.235.66:1597/";//外网
//    private static final String HOST_ADDRESS = "http://121.40.212.11:20015/";//外网
    private static final String HOST_NAME = HOST_ADDRESS + "RailwayQZApp/";

    public static String getHostAddress() {
        return HOST_ADDRESS;
    }

    public static String getHostName() {
        return HOST_NAME;
    }


}
