package com.ticketsystem.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSONObject;


public class GetSender {
	
	public JSONObject sendHttpGet(String url, String param) {
		return sendHttpGet(url, param, "null");
	}
	
	private int getCount=0;
	
	public JSONObject sendHttpGet(String url, String param, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            if (!"null".equals(cookieStr)) {
            	conn.setRequestProperty("Cookie", cookieStr);
            }
            
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            responseBody.append(result);
			System.out.println("responseBody:"+result);

			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
            if (this.getCount<10) {
            	return sendHttpGet(url, param, cookieStr);
            }
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	
	public JSONObject sendHttpsGet(String url, String param) throws Exception {
		return sendHttpsGet(url, param, "null");
	}
	
	public JSONObject sendHttpsGet(String url, String param, String cookieStr) throws Exception {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realURL = new URL(urlName);
            
            //https的可信声明
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new GetSender().hv);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            if (!"null".equals(cookieStr)) {
            	conn.setRequestProperty("Cookie", cookieStr);
            }
            
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
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
