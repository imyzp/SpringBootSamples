package com.yzp.spring.springbootsamples.basic.configparam;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConfigUtil {
    /**
     * 用于读取资源文件信息的类
     */
    private static Properties config = null;
    /**
     * 资源文件名称
     */
    private static final String configName = "application.yml";
    /**
     * 资源文件所在路径
     */
    private static String filePath;
    /**
     * 线程是否运行中
     */
    private static AtomicBoolean isRunFlag = new AtomicBoolean(false);

    /**
     * 初始化加载配置文件信息
     */
    private static void initConfig(){
        InputStream in = null;
        try{
            config = new Properties();
            in = ConfigUtil.class.getClassLoader().getResourceAsStream(configName);
            config.load(in);
        } catch (IOException e) {
            System.out.println("初始化配置信息异常");
            e.printStackTrace();
        }
    }
    public static String getValue(String key)
    {
        return getValue(key,new Object[]{});
    }
    public static String getValue(String key,Object... arguments){
        verify();
        if(config.getProperty(key)!=null)
        {
            String value = "";
            try{
                value = new String(config.getProperty(key).trim().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
                if(arguments != null && arguments.length>0)
                {

                }
            }
        }
    }

}
