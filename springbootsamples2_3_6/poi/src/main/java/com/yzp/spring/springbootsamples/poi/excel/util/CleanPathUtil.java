package com.yzp.spring.springbootsamples.poi.excel.util;

import java.io.File;

public class CleanPathUtil {
    public static String cleanString(String str)
    {
        if(str == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();++i)
        {
            sb.append(cleanChar(str.charAt(i)));
        }
        return sb.toString();
    }
    private static char cleanChar(char ch){
        // 0-9
        for(int i=48;i<58;++i)
        {
            if(ch == i){
                return (char)i;
            }
        }
        // 'A' - 'Z'
        for(int i=65;i<91;++i)
        {
            if(ch == i){
                return (char) i;
            }
        }
        // other valid characters
        switch (ch){
            case '\\':
                return '\\';
            case '/':
                return '/';
            case '.':
                return '.';
            case '-':
                return '-';
            case '_':
                return '_';
            case ',':
                return ',';
            case ' ':
                return ' ';
            case '!':
                return '!';
            case '@':
                return '@';
            case '#':
                return '#';
            case '$':
                return '$';
            case '%':
                return '%';
            case '^':
                return '^';
            case '&':
                return '&';
            case '*':
                return '*';
            case '(':
                return '(';
            case ')':
                return ')';
            case '+':
                return '+';
            case '=':
                return '=';
            case ':':
                return ':';
            case ';':
                return ';';
            case '?':
                return '?';
            case '"':
                return '"';
            case '<':
                return '<';
            case '>':
                return '>';
            case '`':
                return '`';
        }
        if(isChineseChar(ch)){
            return ch;
        }
        return '%';
    }
    // 根据 Unicode 编码判断中文汉字和符号
    private static boolean isChineseChar(char c){
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }
    public static void checkDir(String savePath){
        savePath = cleanString(savePath);
        File file = new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    public static void main(String[] args)
    {
        System.out.println(cleanString("d是大事肯德基啊"));
    }
}
