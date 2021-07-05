package com.ticketsystem.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.test.QueryHttpClient.miTM;
 
/**
 * Hello world!
 */
class GetPostTest {
    public static String sendGet(String url, String param) {
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realURL = new URL(urlName);
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String s : map.keySet()) {
                System.out.println(s + "-->" + map.get(s));
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
 

    public static JSONObject sendGet2(String url, String param, String cookieData) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realURL = new URL(urlName);
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", cookieData);
            
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String s : map.keySet()) {
                System.out.println(s + "-->" + map.get(s));
                if("set-cookie".equalsIgnoreCase(s)) {
                	List<String> list = map.get(s);
                	for (String ss: list) {
                		if (ss.contains("session=")) {
                			String sessionId = ss.substring(ss.indexOf("session=")+8);
                			System.out.println("session1111="+sessionId);
                		}
                	}
                }
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            System.out.println("result====="+result);

			//获取
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			String originStr = headerFields.toString();
			String session = originStr.substring(originStr.indexOf("session=")+8, originStr.indexOf("; Path=/; Expires=Mon"));
			resultJson.put("session", session);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }
    
    public static JSONObject sendPost(String url,String param) throws Exception{
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);

			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new QueryHttpClient().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			//conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +="\n" + line;
            }

			//获取
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			String originStr = headerFields.toString();
			String uuid = originStr.substring(originStr.indexOf("tokenUUID=")+10, originStr.indexOf("; Domain=.flycua.com; Path=/, tokenId="));
			String JSessionId = originStr.substring(originStr.indexOf("JSESSIONID=")+11, originStr.indexOf("; Path=/; HttpOnly"));
			String tokenId = originStr.substring(originStr.indexOf("tokenId=")+8, originStr.indexOf("Domain=.flycua.com; Path=/, JSESSIONID"));
			
            resultJson.put("tokenUUID", uuid);
			resultJson.put("JSESSIONID", JSessionId);
			resultJson.put("tokenId", tokenId);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    public static JSONObject sendPost2(String url,String param, String cookieData) throws Exception{
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);

			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new QueryHttpClient().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Cookie", cookieData);
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +="\n" + line;
            }

			//获取
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			String originStr = headerFields.toString();
			String uuid = originStr.substring(originStr.indexOf("tokenUUID=")+10, originStr.indexOf("; Domain=.flycua.com; Path=/, tokenId="));
			String JSessionId = originStr.substring(originStr.indexOf("JSESSIONID=")+11, originStr.indexOf("; Path=/; HttpOnly"));
			String tokenId = originStr.substring(originStr.indexOf("tokenId=")+8, originStr.indexOf("Domain=.flycua.com; Path=/, JSESSIONID"));
			
            resultJson.put("tokenUUID", uuid);
			resultJson.put("JSESSIONID", JSessionId);
			resultJson.put("tokenId", tokenId);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }
    
	
	HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                               + session.getPeerHost());
            return true;
        }
    };
	
	private static void trustAllHttpsCertificates() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
				.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
				.getSocketFactory());
	}
 
	static class miTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
 
		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}
 
		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}
 
		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
 
		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}
    
}
