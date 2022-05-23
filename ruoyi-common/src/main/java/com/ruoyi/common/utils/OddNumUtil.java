package com.ruoyi.common.utils;

public class OddNumUtil {

    public static String getOddNum(String moduleCode,Long uid){
        //用户ID
        String userId = String.valueOf(uid);
        StringBuilder sb1 = new StringBuilder(userId);
        switch (userId.length()){
            case 1:
                sb1.insert(0,"00");
                break;
            case 2:
                sb1.insert(0,"0");
            default:;
        }
        //日期
        String dateTime = DateUtils.dateTimeNow().substring(2);
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(moduleCode);
        sb2.append(sb1);
        sb2.append(dateTime);

        return String.valueOf(sb2); //订单号格式：模块名首字母+用户ID(3位)+日期+时分秒，例：CG001220329185049
    }

}
