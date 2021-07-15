package com.ticketsystem.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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

	public JSONObject hotCityGet(String hotCityUrl, String param, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String result = "";
		try {
			URL url = new URL(hotCityUrl + "?" +param);
			// 打开和url之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 请求方式
			conn.setRequestMethod("GET");

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
			if (!"null".equals(cookieStr)) {
            	//conn.setRequestProperty("Cookie", cookieStr);
            }

			// DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			conn.setDoOutput(true);
			conn.setDoInput(true);

			/**
			 * 下面的三句代码，就是调用第三方http接口
			 */
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数即数据
			out.write("");
			// flush输出流的缓冲
			out.flush();

			/**
			 * 下面的代码相当于，获取调用第三方http接口后返回的结果
			 */
			// 获取URLConnection对象对应的输入流
			//conn.connect();
			InputStream is = conn.getInputStream();
			// 构造一个字符流缓存
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				result += str;
			}
            responseBody.append(result);
			System.out.println("responseBody:"+result);
			//获取头信息
			responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
			// 关闭流
			is.close();
			// 断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
	int queryGet2 = 0;
	
	public JSONObject queryGet2(String url, String param) {
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
            if (this.queryGet2<10) {
				this.queryGet2++;
            	return queryGet2(url, param);
            }
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
	
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
				this.getCount++;
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
