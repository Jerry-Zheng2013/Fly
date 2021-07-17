package com.ticketsystem.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IpUtils {
    /**
     * 调用cmd命令
     *
     * @param cmd windows命令
     * @return 执行结果
     * @throws Exception
     */
    public static String executeCmd (String cmd) throws Exception {
        Process process = Runtime.getRuntime ().exec ("cmd /c " + cmd);
        StringBuilder executeResult = new StringBuilder ();
        BufferedReader br = new BufferedReader (new InputStreamReader (process.getInputStream ()));
        String line;
        while ((line = br.readLine ()) != null) {
            executeResult.append (line + "\n");
        }
        return executeResult.toString ();
    }

    /**
     * 连接ADSL
     *
     * @param adslTitle 名称
     * @param adslName  账号名称
     * @param adlsPwd   密码
     * @return 是否成功
     * @throws Exception
     */
    public static boolean connAdsl (String adslTitle, String adslName, String adlsPwd) throws Exception {
        String adslCmd = "rasdial " + adslTitle + " " + adslName + " " + adlsPwd;
        return executeCmd (adslCmd).indexOf ("已连接") > 0 ? true : false;
    }

    /**
     * 断开ADSL
     *
     * @param adslTitle 名称
     * @return 是否成功
     * @throws Exception
     */
    public static boolean cutAdsl (String adslTitle) throws Exception {
        String adslCmd = "rasdial " + adslTitle + " /disconnect";
        return executeCmd (adslCmd).indexOf ("没有连接") != -1 ? false : true;
    }

}
