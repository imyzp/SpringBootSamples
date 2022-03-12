package com.yzp.commonsIo.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FileTest {
    public static void main(String[] args) throws IOException {
        // 保存url的图片
        FileUtils.copyURLToFile(new URL("https://www.baidu.com/img/PCfb_5bf082d29588c07f842ccde3f97243ea.png"),new File("1.jpg"));
    }
}
