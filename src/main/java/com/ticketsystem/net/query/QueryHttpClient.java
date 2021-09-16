package com.ticketsystem.net.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class QueryHttpClient {
	
	static Logger log = LogManager.getLogger(QueryHttpClient.class);

	/**
	 * 以post或get方式调用对方接口方法，
	 * 
	 * @param pathUrl
	 */
	public static void doPostOrGet(String urlStr, String requestType, String data) {
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String result = "";
		try {
			URL url = new URL(urlStr);
			// 打开和url之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 请求方式
			// conn.setRequestMethod("POST");
			// conn.setRequestMethod("GET");
			conn.setRequestMethod(requestType);

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			// DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数即数据
			out.write(data);
			// flush输出流的缓冲
			out.flush();
			// 获取URLConnection对象对应的输入流
			InputStream is = conn.getInputStream();
			// 构造一个字符流缓存
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				result += str;
			}
			log.info("responseBody:"+result);
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
	}

	/**
	 * 以post或get方式调用对方接口方法，
	 * 
	 * @param pathUrl
	 */
	public static JSONObject doPostOrGet2(String urlStr, String requestType, String dataStr) {
		log.info("请求地址");
		log.info(urlStr);
		log.info("请求方式");
		log.info(requestType);
		log.info("请求数据");
		log.info(dataStr);
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String result = "";
		try {
			URL url = new URL(urlStr);
			// 打开和url之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 请求方式
			// conn.setRequestMethod("POST");
			// conn.setRequestMethod("GET");
			conn.setRequestMethod(requestType);

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			// DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数即数据
			out.write(dataStr);
			// flush输出流的缓冲
			out.flush();
			// 获取URLConnection对象对应的输入流
			InputStream is = conn.getInputStream();
			// 构造一个字符流缓存
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				result += str;
			}
			log.info("responseBody:"+result);
			// 关闭流
			is.close();
			// 断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
			conn.disconnect();
			
			return JSON.parseObject(result);
			
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
		return JSON.parseObject(result);
	}
	
	public static void main(String[] args) {
		/**
		 * 手机信息查询接口：http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=手机号
		 * http://api.showji.com/Locating/www.showji.com.aspx?m=手机号&output=json&callback=querycallback
		 */
		doPostOrGet("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=13026194071", "GET", "");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 以post或get方式调用对方接口方法，
	 * 
	 * @param pathUrl
	 */
	public static JSONObject logInPost(String urlStr, String requestType, String dataStr) {
		log.info("请求地址");
		log.info(urlStr);
		log.info("请求方式");
		log.info(requestType);
		log.info("请求数据");
		log.info(dataStr);
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
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数即数据
			out.write(dataStr);
			// flush输出流的缓冲
			out.flush();
			// 获取URLConnection对象对应的输入流
			InputStream is = conn.getInputStream();
			// 构造一个字符流缓存
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				result += str;
			}
			log.info("responseBody:"+result);
			// 关闭流
			is.close();
			// 断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
			conn.disconnect();
			
			return JSON.parseObject(result);
			
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
		return JSON.parseObject(result);
	}
	
	
	HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            log.info("Warning: URL Host: " + urlHostName + " vs. "
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
