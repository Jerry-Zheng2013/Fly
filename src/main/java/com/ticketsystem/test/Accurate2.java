package com.ticketsystem.test;


import java.net.URLEncoder;

/**
* 通用文字识别（高精度含位置版）
*/
public class Accurate2 {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String accurate(String fieFullName) {
    	String result = "";
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";
        try {
            // 本地文件路径
            String filePath = fieFullName;
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.889ba8206844a9bfed7d0b0653506ec7.2592000.1629569738.282335-24592255";
            //24.5cb78c01ce4f8a231717aa7043d9e953.2592000.1629576258.282335-24585859
            accessToken = "24.5cb78c01ce4f8a231717aa7043d9e953.2592000.1629576258.282335-24585859";
            result = HttpUtil.post(url, accessToken, param);
            //System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        //Accurate2.accurate();
    }
}
