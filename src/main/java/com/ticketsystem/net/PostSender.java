package com.ticketsystem.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSONObject;

public class PostSender {
	
	
	
	public JSONObject sendHttpPost(String url, String postDataStr) {
		return sendHttpPost(url, postDataStr, null);
	}
	
	public JSONObject sendHttpPost(String url, String postDataStr, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	
	public JSONObject pointsPost(String url, String postDataStr, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	
	public JSONObject choosePost(String url, String postDataStr) {
		return choosePost(url, postDataStr, null);
	}
	
	private int chooseCount = 0;
	
	public JSONObject choosePost(String url, String postDataStr, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);
			if (!result.contains("uuid") && this.chooseCount <10) {
				this.chooseCount++;
				choosePost(url, postDataStr, cookieStr);
			}

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}

	public JSONObject sendHttpsPost(String url, String postDataStr) throws Exception {
		return sendHttpsPost(url, postDataStr, null);
	}
	
	public JSONObject sendHttpsPost(String url, String postDataStr, String cookieStr) throws Exception {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            //https的可信声明
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new PostSender().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +="\n" + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	

	public JSONObject loginPost(String url, String postDataStr) throws Exception {
		return loginPost(url, postDataStr, null);
	}
	
	public JSONObject loginPost(String url, String postDataStr, String cookieStr) throws Exception {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            //https的可信声明
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new PostSender().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;");
			if (!"null".equalsIgnoreCase(cookieStr)) {
				conn.setRequestProperty("Cookie", cookieStr);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +="\n" + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	public static JSONObject bookPost(String bookUrl, String bookDataStr, String bookCookie) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        try {
            URL realUrl = new URL(bookUrl);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			if (!"null".equalsIgnoreCase(bookCookie)) {
				conn.setRequestProperty("Cookie", bookCookie);
			}
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(bookDataStr);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +=" " + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
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
