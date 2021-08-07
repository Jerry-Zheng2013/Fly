package com.ticketsystem.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import com.alibaba.fastjson.JSONObject;

public class GetPostTest5 {

	public static JSONObject indexGet(String indexUrl) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = indexUrl;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            //System.out.println("result:"+result);

			//获取头信息
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			resultJson.put("headers", headerFields.toString());
			//System.out.println("headerFields:"+headerFields.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject checkGet1(String checkUrl1, String checkparam1) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = checkUrl1 + "?" + checkparam1;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            System.out.println("result:"+result);

			//获取头信息
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			System.out.println("headerFields:"+headerFields.toString());
			resultJson.put("headers", headerFields.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject loginGet1(String loginUrl1, String loginParam1, String loginCookie1) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = loginUrl1;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", loginCookie1);
            
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            //System.out.println("result:"+result);

			//获取头信息
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			resultJson.put("headers", headerFields.toString());
			//System.out.println("headerFields:"+headerFields.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject getUuidGet1(String getUuidUrl, String getUuidParam, String getUuidCookie) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = getUuidUrl+"?"+getUuidParam;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", getUuidCookie);
            
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            System.out.println("result:"+result);
			resultJson.put("result", result);

			//获取头信息
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			resultJson.put("headers", headerFields.toString());
			System.out.println("headerFields:"+headerFields.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject validateGet1(String validateUrl1, String validateParam1, String validateCookie1) {
		JSONObject resultJson = new JSONObject();
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		URLConnection conn = null;
        String fileName = "";
        String urlName = validateUrl1 + "?" + validateParam1;
        try {
            URL realURL = new URL(urlName);
            
            conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", validateCookie1);
            
            conn.connect();
            
            bis = new BufferedInputStream(conn.getInputStream());
            
            fileName = String.valueOf(Math.random()).substring(2, 15)+".png";
            fos = new FileOutputStream("C:/img/"+fileName);
            byte[] buf = new byte[1024];
    		int size = 0;
            while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
			System.out.println("fileName=="+fileName);
			resultJson.put("fileName", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			try {
				fos.close();
				bis.close();
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
        return resultJson;
	}

	public static JSONObject userLoginPost1(String userLoginUrl1, String userLoginParam, String userLoginCookie)  throws Exception {
		JSONObject resultJson = new JSONObject();
        String result = "";
		Map<String, List<String>> headMap = new TreeMap<String, List<String>>();
        try {
            URL realUrl = new URL(userLoginUrl1);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            //conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            //conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
			conn.setRequestProperty("Content-Length", "49");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			conn.setRequestProperty("Cookie", userLoginCookie);
			conn.setRequestProperty("Host", "www.flycua.com");
			conn.setRequestProperty("Origin", "http://www.flycua.com");
			conn.setRequestProperty("Referer", "http://www.flycua.com/login/");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			//conn.setRequestProperty("X-Referrer", "http://www.flycua.com/");
			//conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(userLoginParam);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +="\n" + line;
            }
            System.out.println("result:"+result);
			resultJson.put("result", result);

            //获取loginPost
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			System.out.println("headerFields:"+headerFields.toString());
			resultJson.put("headers", headerFields.toString());
			headMap = conn.getHeaderFields();
			resultJson.put("headMap", headMap);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject checkGet2(String checkUrl2, String checkParam2, String checkCookie2) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = checkUrl2 + "?" + checkParam2;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", checkCookie2);
            
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            System.out.println("result:"+result);
			resultJson.put("result", result);

			//获取头信息
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			System.out.println("headerFields:"+headerFields.toString());
			resultJson.put("headers", headerFields.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

}
