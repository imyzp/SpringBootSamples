package com.yzp.spring.springbootsamples.poi.test.controller;

import org.junit.Test;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @ProjectName springbootsamples2_3_6
 * @Description 测试CSV格式的excel读写：好处不用workbook，写数据时不用把整个excel加载到内存程序中
 * @Author yaozhenpeng
 * @Time 2021/11/22 0:19
 */
public class CSVTestController {
    @Test
    public  void readCsv() {
        try {
            File csv = new File("E:\\yzp\\BaiduNetdiskWorkspace\\mySource\\SpringBootSamples\\springbootsamples2_3_6\\poi\\src\\main\\resources\\测试csv格式的excel读写.csv"); // CSV文件

            BufferedReader br = new BufferedReader(new FileReader(csv));

            // 读取直到最后一行
            String line = "";
            while ((line = br.readLine()) != null) {
                // 把一行数据分割成多个字段
                StringTokenizer st = new StringTokenizer(line, ",");

                while (st.hasMoreTokens()) {
                    // 每一行的多个字段用TAB隔开表示
                    System.out.print(st.nextToken() + "/t");
                }
                System.out.println();
            }
            br.close();

        } catch (FileNotFoundException e) {
            // 捕获File对象生成时的异常
            e.printStackTrace();
        } catch (IOException e) {
            // 捕获BufferedReader对象关闭时的异常
            e.printStackTrace();
        }
    }
    @Test
    public void writeCsv(){
        try {
            File csv = new File("E:\\yzp\\BaiduNetdiskWorkspace\\mySource\\SpringBootSamples\\springbootsamples2_3_6\\poi\\src\\main\\resources\\测试csv格式的excel读写.csv"); // CSV文件

            // 追加模式
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            // 新增一行数据
            bw.newLine();
            bw.write("电影" + "," + "2009" + "," + "1212");
            bw.newLine();
            bw.write("电影" + "," + "2010" + "," + "1212");
            bw.close();
        } catch (FileNotFoundException e) {
            // 捕获File对象生成时的异常
            e.printStackTrace();
        } catch (IOException e) {
            // 捕获BufferedWriter对象关闭时的异常
            e.printStackTrace();
        }
    }

}
