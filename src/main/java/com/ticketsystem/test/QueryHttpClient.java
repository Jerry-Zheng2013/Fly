package com.ticketsystem.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class QueryHttpClient {
	
	public static void main(String[] args) {
		
		//登录
		String urlStr = "https://higo.flycua.com/ffp/member/login";
		String requestType = "POST";
		String dataStr = "NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCHb/yyON/GL4rvJVQxNcX1J9Ui22MPCTWHxcaNCrUiOmdhfbhpKhRZPxIegcZRKs7OCN1CCNH1QMiZZxgP5bdFD";
		
		try {
			JSONObject logInPost = GetPostTest.sendPost(urlStr, dataStr);
			//登录成功后，获取tokenId和tokenUUID
			String tokenId = logInPost.getString("tokenId");
			String tokenUUID = logInPost.getString("tokenUUID");
			String accountCheckCookie2 = Test2.getAccountCheckCookie(tokenId, tokenUUID);
			
			
			JSONObject queryPost = GetPostTest.sendGet2(
	        		"http://www.flycua.com/app/searchFlights/queryFlight",
	        		"_=1625491297316&org=CITY_BJS_CN&des=CitCnSHANGHA364&type=oneway&depd=2021-07-30&cals=false&adt=1&chd=0&gm=0&jc=0",
	        		accountCheckCookie2
	        		);
			String session = queryPost.getString("session");
	        System.out.println("session222="+session);

			
	        String bookCookie = Test2.getBookCookieData(session, tokenId, tokenUUID);
	        
			JSONObject queryPost3 = GetPostTest.sendGet2(
	        		"http://www.flycua.com/app/login/queryUserStatus",
	        		"_=0.6013036610715838",
	        		bookCookie
	        		);
			String session3 = queryPost3.getString("session");
	        System.out.println("session333="+session3);
			
	        /*
	        String bookPostData = Test2.getBookPostData(tokenUUID);
	        GetPostTest.sendPost2(
	        		"http://www.flycua.com/app/booking/book?_=1625384445626", 
	        		bookPostData, 
	        		bookCookie);
	        */
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		JSONObject logInPost = QueryHttpClient.logInPost(urlStr, requestType, dataStr);
		//登录成功后，获取tokenId和tokenUUID
		String tokenId = logInPost.getString("tokenId");
		String tokenUUID = logInPost.getString("tokenUUID");
		
		String accountCheckCookie2 = Test2.getAccountCheckCookie(tokenId, tokenUUID);
		JSONObject logInPost2 = QueryHttpClient.queryUserStatusPost(
				"http://www.flycua.com/app/searchFlights/queryFlight?_=1625491297316&org=CITY_BJS_CN&des=CitCnSHANGHA364&type=oneway&depd=2021-07-30&cals=false&adt=1&chd=0&gm=0&jc=0", 
				"GET", "","");
		
		
		*/
		
		
		
		
		/**
		//检查账户状态
		String urlStr2 = "http://www.flycua.com/app/login/queryUserStatus?_=0.6013036610715838";
		String requestType2 = "GET";
		String dataStr2 = "";
		JSONObject userStatusPost = QueryHttpClient.queryUserStatusPost(urlStr2, requestType2, dataStr2, accountCheckCookie);
		//查询账户状态成功之后，获取到session
		String session = userStatusPost.getString("session");
		System.out.println(session);
		
		
		//预定
		String urlStr3 = "http://www.flycua.com/app/booking/book?_=1625384445626";
		String requestType3 = "POST";
		String bookPostData = Test2.getBookPostData(logInPost.getString("tokenUUID"));
		System.out.println(bookPostData);
		JSONObject jj = JSONObject.parseObject(bookPostData);
		System.out.println(jj);
		String bookCookie = Test2.getBookCookieData(session, tokenId, tokenUUID);
		QueryHttpClient.bookPost(urlStr3, requestType3, bookPostData, bookCookie);
		*/
	}
	

	/**
	* post方式连接获取cookie
	* @param url 链接
	* @param params 请求参数
	* @param headers 请求头部参数
	* @param charSet 编码
	* @return cookie
	*/
	public static String post_cookie(String url, String params, Map<String, String> headers, String charSet) {
		try {
			URL url1 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setRequestMethod("POST");
			// 输入参数
			conn.setDoOutput(true);
			// 不使用缓存
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(false);
			conn.setConnectTimeout(60 * 1000);
			for (String header : headers.keySet()) {
				conn.setRequestProperty(header, headers.get(header));
			}
			conn.connect();
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				String param = params;
				out.writeBytes(param);
				out.flush();
				out.close();
			String cookie = "";
			Map<String, List<String>> respone_headers = conn.getHeaderFields();
			for (String key : respone_headers.keySet()) {
				// System.err.println(respone_headers.get(key));
				if (key != null && key.equals("Set-Cookie")) {
					List<String> string = respone_headers.get(key);
					StringBuilder builder = new StringBuilder();
					for (String str : string) {
						System.err.println(str);
						builder.append(str).toString();
					}
					cookie = builder.toString();
				}
			}
			conn.disconnect();
			return cookie;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

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
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new QueryHttpClient().hv);
			
			// 打开和url之间的连接
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
			
			String cookie="";
			   Map<String,List<String>> respone_headers=conn.getHeaderFields();
			   for (String key : respone_headers.keySet()) {
			   //System.err.println(respone_headers.get(key));
			   if(key!=null &&key.equals("Set-Cookie")) {
			       List<String> string = respone_headers.get(key);
			       StringBuilder builder = new StringBuilder();
			       for (String s1 : string) {
			           System.err.println(s1);
			           builder.append(s1).toString();
			       }
			       cookie=builder.toString();
			   }
			   }
			System.out.println(cookie);
			//post_cookie(urlStr, dataStr, headerFields, "UTF-8");
			
			
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
	
	//查询账户状态
	private static JSONObject queryUserStatusPost(String urlStr2, String requestType2, String dataStr2,
			String accountCheckCookie) {
		JSONObject resultJson = new JSONObject();
		System.out.println("请求地址");
		System.out.println(urlStr2);
		System.out.println("请求方式");
		System.out.println(requestType2);
		System.out.println("请求数据");
		System.out.println(dataStr2);
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String result = "";
		try {
			URL url = new URL(urlStr2);
			//trustAllHttpsCertificates();
			//HttpsURLConnection.setDefaultHostnameVerifier(new QueryHttpClient().hv);
			
			// 打开和url之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 请求方式
			// conn.setRequestMethod("POST");
			// conn.setRequestMethod("GET");
			conn.setRequestMethod(requestType2);
			
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
			conn.setRequestProperty("connection", "close");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setRequestProperty("Cookie", accountCheckCookie);
			//conn.addRequestProperty(key, value);
			
			// DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			/**
			 * 下面的三句代码，就是调用第三方http接口
			 */
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数即数据
			out.write(dataStr2);
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
			String session = originStr.substring(originStr.indexOf("session=")+8, originStr.indexOf("; Path=/; Expires=Sun,"));
			
			// 关闭流
			is.close();
			// 断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
			conn.disconnect();
			
			resultJson.put("body", JSON.parseObject(result));
			resultJson.put("session", session);
			
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
	
	
	//预定
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
			//trustAllHttpsCertificates();
			//HttpsURLConnection.setDefaultHostnameVerifier(new QueryHttpClient().hv);
			
			// 打开和url之间的连接
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
