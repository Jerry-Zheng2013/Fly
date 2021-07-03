package com.ticketsystem.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class QueryHttpClient {
	
	public static void main(String[] args) {
		
		String ss = "aaa 234";
		System.out.println(ss);
		System.out.println(ss.trim().replaceAll(" ", ""));
		
		String urlStr = "https://higo.flycua.com/ffp/member/login";
		String requestType = "POST";
		String dataStr = "NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCEm3SxfgMZ8I/hpPPwEJ24WdF9Wp6yYfyth9eqBA1jHECZxFcebg/Xx2cJyLaqj9Y30aCrOS9m2n/hdiBsuhyBx";
		JSONObject logInPost = QueryHttpClient.logInPost(urlStr, requestType, dataStr);
		
		// { mode: "memberLogin", memberId: $("#memberId").val(), password: $("#password").val(), openId: o }
		String urlStr2 = "http://www.flycua.com/app/booking/book?_=1625292330229";
		String requestType2 = "POST";
		String bookPostData = Test2.getBookPostData(logInPost.getString("tokenUUID"));
		System.out.println(bookPostData);
		JSONObject jj = new JSONObject().parseObject(bookPostData);
		System.out.println(jj);
		String bookCookie = Test2.getBookCookieData("1625291979", logInPost.getString("session"), logInPost.getString("tokenId"), logInPost.getString("tokenUUID"));
		QueryHttpClient.bookPost(urlStr2, requestType2, bookPostData, bookCookie);
		
	}
	

	/**
	 * 以post或get方式调用对方接口方法，
	 * 
	 * @param pathUrl
	 */
	public static JSONObject logInPost(String urlStr, String requestType, String dataStr) {
		JSONObject resultJson = new JSONObject();
		System.out.println("请求地址");
		System.out.println(urlStr);
		System.out.println("请求方式");
		System.out.println(requestType);
		System.out.println("请求数据");
		System.out.println(dataStr);
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String result = "";
		try {
			URL url = new URL(urlStr);
			// 打开和url之间的连接
			
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new QueryHttpClient().hv);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 请求方式
			// conn.setRequestMethod("POST");
			// conn.setRequestMethod("GET");
			conn.setRequestMethod(requestType);

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "close");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			
			// DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			conn.setDoOutput(true);
			conn.setDoInput(true);

			/**
			 * 下面的三句代码，就是调用第三方http接口
			 */
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数即数据
			out.write(dataStr);
			// flush输出流的缓冲
			out.flush();

			/**
			 * 下面的代码相当于，获取调用第三方http接口后返回的结果
			 */
			// 获取URLConnection对象对应的输入流
			InputStream is = conn.getInputStream();
			// 构造一个字符流缓存
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				result += str;
			}
			System.out.println(result);
			
			//获取
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			String originStr = headerFields.toString();
			String uuid = originStr.substring(originStr.indexOf("tokenUUID=")+10, originStr.indexOf("; Domain=.flycua.com; Path=/, tokenId="));
			String session = originStr.substring(originStr.indexOf("JSESSIONID=")+11, originStr.indexOf("; Path=/; HttpOnly"));
			String tokenId = originStr.substring(originStr.indexOf("tokenId=")+8, originStr.indexOf("Domain=.flycua.com; Path=/, JSESSIONID"));
			
			// 关闭流
			is.close();
			// 断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
			conn.disconnect();
			
			resultJson.put("body", JSON.parseObject(result));
			resultJson.put("tokenUUID", uuid);
			resultJson.put("session", session);
			resultJson.put("tokenId", tokenId);
			return resultJson;
			
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
		return resultJson;
	}
	
	public static JSONObject bookPost(String urlStr, String requestType, String dataStr, String bookCookie) {
		JSONObject resultJson = new JSONObject();
		System.out.println("请求地址");
		System.out.println(urlStr);
		System.out.println("请求方式");
		System.out.println(requestType);
		System.out.println("请求数据");
		System.out.println(dataStr);
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String result = "";
		try {
			URL url = new URL(urlStr);
			// 打开和url之间的连接
			
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new QueryHttpClient().hv);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 请求方式
			// conn.setRequestMethod("POST");
			// conn.setRequestMethod("GET");
			conn.setRequestMethod(requestType);

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "close");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Cookie Data", bookCookie);
			
			
			// DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			conn.setDoOutput(true);
			conn.setDoInput(true);

			/**
			 * 下面的三句代码，就是调用第三方http接口
			 */
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数即数据
			out.write(dataStr);
			// flush输出流的缓冲
			out.flush();

			/**
			 * 下面的代码相当于，获取调用第三方http接口后返回的结果
			 */
			// 获取URLConnection对象对应的输入流
			InputStream is = conn.getInputStream();
			// 构造一个字符流缓存
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				result += str;
			}
			System.out.println(result);
			
			//获取
			Map<String, List<String>> headerFields = conn.getHeaderFields();
			String originStr = headerFields.toString();
			String uuid = originStr.substring(originStr.indexOf("tokenUUID=")+10, originStr.indexOf("; Domain"));
			
			// 关闭流
			is.close();
			// 断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
			conn.disconnect();
			
			resultJson.put("body", JSON.parseObject(result));
			resultJson.put("tokenUUID", uuid);
			
			
			return resultJson;
			
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
