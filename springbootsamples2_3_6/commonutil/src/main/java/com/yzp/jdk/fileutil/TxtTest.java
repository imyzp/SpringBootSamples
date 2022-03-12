package com.yzp.jdk.fileutil;

import org.springframework.ui.ModelMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TxtTest {
    // 读取txt内容
    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    // 读取文件夹下所有文件名
    public static List getFile(File file) {
        List listLocal = new ArrayList<>();
        if (file != null) {
            File[] f = file.listFiles();
            if (f != null) {
                for (int i = 0; i < f.length; i++) {
                    getFile(f[i]);
                    listLocal.add(f[i]);
                }
            } else {
                // System.out.println(file);
            }
        }
        return listLocal;
    }
    public String getTxt(ModelMap map) throws FileNotFoundException {
        // 文件夹下每一个txt名
        String path = "D:/wode/TestReallyData/txt2/txt_all/";
        File all = new File(path);
        //将全部txt存到list中
        List allPath = getFile(all);
        // 读取txt内容 并转换成List
        for(int i = 0 ;i <allPath.size();i++){
            String dpath = String.valueOf(allPath.get(i));
            File file = new File(dpath);
            String content = txt2String(file);
        }
        // todo
        return null;
    }
}
