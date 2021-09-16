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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;


public class GetSender {
	
	static Logger log = LogManager.getLogger(PostSender.class);
	
	public JSONObject sendHttpGet(String url, String param) {
		return sendHttpGet(url, param, "null");
	}

	public JSONObject hotCityGet(String hotCityUrl, String param, String cookieStr) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
		InputStream is = null;
		OutputStreamWriter out = null;
		HttpURLConnection conn = null;
		BufferedReader br = null;
		String result = "";
		try {
			URL url = new URL(hotCityUrl + "?" +param);
			// 打开和url之间的连接
			conn = (HttpURLConnection) url.openConnection();
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

			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数即数据
			out.write("");
			// flush输出流的缓冲
			out.flush();

			// 获取URLConnection对象对应的输入流
			is = conn.getInputStream();
			// 构造一个字符流缓存
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				result += str;
			}
			//获取响应体
    		log.info("responseBody:"+result);
			log.info("responseBody:"+result);
			//获取头信息
			responseHead = conn.getHeaderFields();
    		log.info("responseHead:"+responseHead.toString());
			log.info("responseHead:"+responseHead.toString());

			responseBody.append(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null){
					is.close();					
				}
				if(conn!=null){
					conn.disconnect();					
				}
				if(out != null){
					out.close();
				}
				if(br != null){
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
            conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            conn.setRequestProperty("Connection", "keep-alive");
            //conn.setRequestProperty("Host", "www.flycua.com");
            //conn.setRequestProperty("Referer", "http://www.flycua.com/booking/search.html?flightType=oneway&Origin=CITY_BJS_CN&Destination=CitCnSHANGHA364&departDate=2021-07-28&adults=4&children=0&militaryDisability=0&policeRemnants=0");
            //conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
            //conn.setRequestProperty("X-Referrer", "http://www.flycua.com/booking/search.html?flightType=oneway&radio-1-set=on&Origin=CITY_BJS_CN&Destination=CitCnSHANGHA364&departDate=2021-07-28&adults=4&children=0&militaryDisability=0&policeRemnants=0");
            //conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
			//获取响应体
    		log.info("responseBody:"+result);
			log.info("responseBody:"+result);
			//获取头信息
			responseHead = conn.getHeaderFields();
    		log.info("responseHead:"+responseHead.toString());
			log.info("responseHead:"+responseHead.toString());
			
			responseBody.append(result);
            
        } catch (IOException e) {
            e.printStackTrace();
            //if (this.queryGet2<3) {
            	try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
				}
				this.queryGet2++;
            	return queryGet2(url, param);
            //}
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
			//获取响应体
    		log.info("responseBody:"+result);
			log.info("responseBody:"+result);
			//获取头信息
			responseHead = conn.getHeaderFields();
    		log.info("responseHead:"+responseHead.toString());
			log.info("responseHead:"+responseHead.toString());
			
			responseBody.append(result);
            
        } catch (IOException e) {
            e.printStackTrace();
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
			//获取响应体
    		log.info("responseBody:"+result);
			log.info("responseBody:"+result);
			//获取头信息
			responseHead = conn.getHeaderFields();
    		log.info("responseHead:"+responseHead.toString());
			log.info("responseHead:"+responseHead.toString());
			
			responseBody.append(result);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
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

	int queryGet3 = 0;
	public JSONObject queryGet3(String queryUrl, String param, String queryCookie3) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        String urlName = queryUrl + "?" + param;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Host", "www.flycua.com");
            conn.setRequestProperty("Referer", "http://www.flycua.com/booking/search.html?flightType=oneway&Origin=CITY_BJS_CN&Destination=CitCnSHANGHA364&departDate=2021-07-28&adults=4&children=0&militaryDisability=0&policeRemnants=0");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
            conn.setRequestProperty("X-Referrer", "http://www.flycua.com/booking/search.html?flightType=oneway&radio-1-set=on&Origin=CITY_BJS_CN&Destination=CitCnSHANGHA364&departDate=2021-07-28&adults=4&children=0&militaryDisability=0&policeRemnants=0");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("Cookie", queryCookie3);
            
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
			//获取响应体
    		log.info("responseBody:"+result);
			log.info("responseBody:"+result);
			//获取头信息
			responseHead = conn.getHeaderFields();
    		log.info("responseHead:"+responseHead.toString());
			log.info("responseHead:"+responseHead.toString());
			
			responseBody.append(result);
            
        } catch (IOException e) {
            e.printStackTrace();
            //if (this.queryGet2<2) {
            	try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
				}
				this.queryGet3++;
            	return queryGet3(queryUrl, param, queryCookie3);
            //}
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}

	int queryGet4 = 0;
	public JSONObject queryGet4(String queryUrl4, String param4, String queryCookie) {
		JSONObject resultJson = new JSONObject();
		StringBuffer responseBody = new StringBuffer();
		Map<String, List<String>> responseHead = new TreeMap<String, List<String>>();
		
        String result = "";
        String urlName = queryUrl4 + "?" + param4;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Host", "www.flycua.com");
            conn.setRequestProperty("Referer", "http://www.flycua.com/booking/search.html?flightType=oneway&Origin=CITY_BJS_CN&Destination=CitCnSHANGHA364&departDate=2021-07-28&adults=4&children=0&militaryDisability=0&policeRemnants=0");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36");
            conn.setRequestProperty("X-Referrer", "http://www.flycua.com/booking/search.html?flightType=oneway&radio-1-set=on&Origin=CITY_BJS_CN&Destination=CitCnSHANGHA364&departDate=2021-07-28&adults=4&children=0&militaryDisability=0&policeRemnants=0");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("Cookie", queryCookie);
            
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
			//获取响应体
    		log.info("responseBody:"+result);
			log.info("responseBody:"+result);
			//获取头信息
			responseHead = conn.getHeaderFields();
    		log.info("responseHead:"+responseHead.toString());
			log.info("responseHead:"+responseHead.toString());
			
			responseBody.append(result);
            
        } catch (IOException e) {
            e.printStackTrace();
            //if (this.queryGet2<2) {
            	try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
				}
				this.queryGet4++;
            	return queryGet4(queryUrl4, param4, queryCookie);
            //}
        }
        resultJson.put("responseBody", responseBody);
        resultJson.put("responseHead", responseHead);
        return resultJson;
	}
}
