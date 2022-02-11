package com.yzp.spring.springbootsamples.basic.configparam;

import com.yzp.spring.springbootsamples.basic.json.JSONUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Properties;

public class PropertiesUtil {
    /**
     * 用于读取资源文件信息的类
     */
    private static Properties config = null;
    /**
     * 资源文件名称
     */
    private static final String configName = "config.properties";
    /**
     * 资源文件所在路径
     */
    private static String filePath;


    /**
     * 初始化加载配置文件信息
     */
    private static void initConfig()
    {
        if(config == null){
            InputStream in = null;
            try{
                config = new Properties();
                in = PropertiesUtil.class.getClassLoader().getResourceAsStream(configName);
                config.load(in);
            } catch (IOException e) {
                System.out.println("初始化配置信息异常");
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取配置值
     * @param key
     * @param arguments
     * @return
     */
    public static String getValue(String key,Object... arguments){
        initConfig();
        if(config.getProperty(key)!=null)
        {
            String value = "";
            try{
                value = new String(config.getProperty(key).trim().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
                if(arguments != null && arguments.length>0)
                {
                    value = MessageFormat.format(value,arguments);
                }
            }catch (Exception e)
            {
                System.out.println("获取配置信息异常");
            }
            return value;
        }else{
            return "";
        }
    }

    /**
     * 获取配置值，转化成对象
     * @param key
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getValue(String key,Class<T> c)
    {
        initConfig();
        if(config.getProperty(key) != null)
        {
            String value = "";
            try{
                value = new String(config.getProperty(key).trim().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
                return JSONUtil.parse(value,c);
            }catch (Exception e)
            {
                System.out.println("获取配置信息异常");
            }
        }
        return null;
    }
}
