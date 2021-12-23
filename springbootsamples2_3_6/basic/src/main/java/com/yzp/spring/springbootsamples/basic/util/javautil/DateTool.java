package com.yzp.spring.springbootsamples.basic.util.javautil;

public class DateTool {
    /**
     * 返回时间的几天几小时几分钟几秒
     * @param miliSecond
     * @return
     */
    public static String tanslateMilliSecondTime(Long miliSecond){
        Long differTime = miliSecond;
        String dataTime = "";

        int dayMiliSecond = 1440*60*1000;
        int hourMiliSecond = 60*60*1000;
        int minuteMiliSecond = 60*1000;
        int secondMiliSecond = 1000;
        if(differTime>=dayMiliSecond){
            int day = differTime.intValue()/dayMiliSecond;
            int hour = (differTime.intValue() - day * dayMiliSecond)/hourMiliSecond;
            dataTime = day+"天"+hour+"小时";
        }else if(differTime>=hourMiliSecond){
            int hour = differTime.intValue()/hourMiliSecond;
            int minute = (differTime.intValue() - hour * hourMiliSecond)/minuteMiliSecond;
            dataTime = hour+"小时"+minute+"分钟";
        }else if(differTime>=minuteMiliSecond){
            int minute = differTime.intValue()/minuteMiliSecond;
            int second = (differTime.intValue() - minute * minuteMiliSecond)/secondMiliSecond;
            dataTime = minute+"分钟"+minute+"秒";
        }else{
            int second = differTime.intValue()/secondMiliSecond;
            int i = differTime.intValue()%secondMiliSecond;
            dataTime = second+"."+i+"秒";
        }
        return dataTime;
    }
}
