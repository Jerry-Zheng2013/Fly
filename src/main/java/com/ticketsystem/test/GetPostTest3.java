package com.ticketsystem.test;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
 
/**
 * Hello world!
 */
public class GetPostTest3 {
	
	static Logger log = LogManager.getLogger(GetPostTest3.class);
	
	HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
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

	public static JSONObject getTokenGet(String url, String param) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = url + "?" + param;
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
			//获取响应体
			log.info("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			log.info("responseHead:"+responseHead.toString());
			
			resultJson.put("result", result);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject getSessionGet(String checkUrl, String checkParam) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = checkUrl + "?" + checkParam;
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
			//获取响应体
			log.info("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			log.info("responseHead:"+responseHead.toString());
			
            resultJson.put("flightData", result);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject getImageGet(String url, String param, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		URLConnection conn = null;
        String fileName = "";
        String urlName = url + "?" + param;
        try {
            URL realURL = new URL(urlName);
            
            conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", cookieStr);
            
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
			log.info("fileName=="+fileName);
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

	public static JSONObject sendMsg(String phoneUrl, String phoneParam, String phoneCookie) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = phoneUrl + "?" + phoneParam;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", phoneCookie);
            
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
			//获取响应体
			log.info("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			log.info("responseHead:"+responseHead.toString());
			
			resultJson.put("headers", responseHead.toString());
			resultJson.put("result", result);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject userRegAndAct(String userRegAndActUrl, String addParam, String addCookie) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = userRegAndActUrl + "?" + addParam;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", addCookie);
            
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
			//获取响应体
			log.info("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			log.info("responseHead:"+responseHead.toString());
			
			resultJson.put("headers", responseHead.toString());
			resultJson.put("result", result);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}
	
	
}
