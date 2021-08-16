package com.ticketsystem.test;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.alibaba.fastjson.JSONObject;
 
/**
 * Hello world!
 */
public class GetPostTest2 {
	
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

	public static JSONObject firstLoginPost(String url, String param) throws Exception {
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);
            //https的可信声明
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new GetPostTest2().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
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
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			

            //获取loginPost
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("jsessionid")) {
								String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
								resultJson.put("JSESSIONID", xlbValue);
							} else if (entryValue.toLowerCase().contains("session")) {
								String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("session", sessionValue);
							} else if (entryValue.toLowerCase().contains("x-lb")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("xlb", xlbValue);
							}else if (entryValue.toLowerCase().contains("tokenuuid")) {
								String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
								resultJson.put("tokenUUID", sessionValue);
							}else if (entryValue.toLowerCase().contains("tokenid")) {
								String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("tokenId", xlbValue);
							}
						}
					}
				}
			}
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject checkStatusGet(String url, String param, String checkCookie2) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", checkCookie2);
            
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String s : map.keySet()) {
                if("set-cookie".equalsIgnoreCase(s)) {
                	List<String> list = map.get(s);
                	for (String ss: list) {
                		if (ss.contains("session=")) {
                			String sessionId = ss.substring(ss.indexOf("session=")+8);
                		}
                	}
                }
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());

			//获取头信息
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("jsessionid")) {
								String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
								resultJson.put("JSESSIONID", xlbValue);
							} else if (entryValue.toLowerCase().contains("session")) {
								String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("session", sessionValue);
							} else if (entryValue.toLowerCase().contains("x-lb")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("xlb", xlbValue);
							}else if (entryValue.toLowerCase().contains("tokenuuid")) {
								String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
								resultJson.put("tokenUUID", sessionValue);
							}else if (entryValue.toLowerCase().contains("tokenid")) {
								String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("tokenId", xlbValue);
							}
						}
					}
				}
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}
	


	public static JSONObject checkGet(String url, String param) {
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
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
			resultJson.put("head", responseHead.toString());
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
	
	public static JSONObject secondLoginPost(String url, String param, String loginCookie2) throws Exception {
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);

            //https的可信声明
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new GetPostTest2().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Cookie", loginCookie2);
			
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
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());

            //获取头信息
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("jsessionid")) {
								String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
								resultJson.put("JSESSIONID", xlbValue);
							} else if (entryValue.toLowerCase().contains("session")) {
								String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("session", sessionValue);
							} else if (entryValue.toLowerCase().contains("x-lb")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("xlb", xlbValue);
							}else if (entryValue.toLowerCase().contains("tokenuuid")) {
								String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
								resultJson.put("tokenUUID", sessionValue);
							}else if (entryValue.toLowerCase().contains("tokenid")) {
								String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("tokenId", xlbValue);
							}
						}
					}
				}
			}
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject checkStatusGet2(String url, String param, String accountCheckCookie2) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", accountCheckCookie2);
            
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String s : map.keySet()) {
                if("set-cookie".equalsIgnoreCase(s)) {
                	List<String> list = map.get(s);
                	for (String ss: list) {
                		if (ss.contains("session=")) {
                			String sessionId = ss.substring(ss.indexOf("session=")+8);
                		}
                	}
                }
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());

			//获取头信息
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("jsessionid")) {
								String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
								resultJson.put("JSESSIONID", xlbValue);
							} else if (entryValue.toLowerCase().contains("session")) {
								String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("session", sessionValue);
							} else if (entryValue.toLowerCase().contains("x-lb")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("xlb", xlbValue);
							}else if (entryValue.toLowerCase().contains("tokenuuid")) {
								String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
								resultJson.put("tokenUUID", sessionValue);
							}else if (entryValue.toLowerCase().contains("tokenid")) {
								String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("tokenId", xlbValue);
							}
						}
					}
				}
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject bookPost(String url, String bookPostData, String bookCookie) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Cookie", bookCookie);
			
            //post设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(bookPostData);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while((line = in.readLine()) != null){
                result +="\n" + line;
            }
            System.out.println("responseBody:"+result);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject addToCartPost(String url, String param, String cookieStr) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Cookie", cookieStr);
			
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
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
            String uuidStr2 = result.substring(result.indexOf("\"uuid\":\"")+8, result.indexOf("\"}"));
            resultJson.put("uuid", uuidStr2);

			//获取头信息
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("uuid")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("uuid", xlbValue);
							}
						}
					}
				}
			}
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject chooseFlightPost(String url, String param, String cookieStr) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Cookie", cookieStr);
			
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
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
            String uuidStr2 = result.substring(result.indexOf("\"uuid\":\"")+8, result.indexOf("\"}"));
            resultJson.put("uuid", uuidStr2);

			//获取头信息
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("uuid")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("uuid", xlbValue);
							}
						}
					}
				}
			}
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject queryFlightPost(String url, String param, String cookieStr) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Cookie", cookieStr);
			
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
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
			//获取头信息
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("uuid")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("uuid", xlbValue);
							}
						}
					}
				}
			}
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject queryGet(String url, String param) {
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
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
            resultJson.put("flightData", result);

			//获取头信息
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("jsessionid")) {
								String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
								resultJson.put("JSESSIONID", xlbValue);
							} else if (entryValue.toLowerCase().contains("session")) {
								String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("session", sessionValue);
							} else if (entryValue.toLowerCase().contains("x-lb")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("xlb", xlbValue);
							}else if (entryValue.toLowerCase().contains("tokenuuid")) {
								String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
								resultJson.put("tokenUUID", sessionValue);
							}else if (entryValue.toLowerCase().contains("tokenid")) {
								String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("tokenId", xlbValue);
							}
						}
					}
				}
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}

	public static JSONObject getBaiduToken(String url, String param, String checkCookie2) {
		JSONObject resultJson = new JSONObject();
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realURL = new URL(urlName);
            
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.setRequestProperty("Cookie", checkCookie2);
            
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String s : map.keySet()) {
                if("set-cookie".equalsIgnoreCase(s)) {
                	List<String> list = map.get(s);
                	for (String ss: list) {
                		if (ss.contains("session=")) {
                			String sessionId = ss.substring(ss.indexOf("session=")+8);
                		}
                	}
                }
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
			//获取头信息
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("jsessionid")) {
								String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
								resultJson.put("JSESSIONID", xlbValue);
							} else if (entryValue.toLowerCase().contains("session")) {
								String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("session", sessionValue);
							} else if (entryValue.toLowerCase().contains("x-lb")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("xlb", xlbValue);
							}else if (entryValue.toLowerCase().contains("tokenuuid")) {
								String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
								resultJson.put("tokenUUID", sessionValue);
							}else if (entryValue.toLowerCase().contains("tokenid")) {
								String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("tokenId", xlbValue);
							}
						}
					}
				}
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}


	public static JSONObject loginPost3(String url, String param, String cookieStr) throws Exception {
		JSONObject resultJson = new JSONObject();
        String result = "";
        try {
            URL realUrl = new URL(url);
            //https的可信声明
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(new GetPostTest2().hv);
            
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Cookie", cookieStr);

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
			//获取响应体
			System.out.println("responseBody:"+result);
			//获取头信息
			Map<String, List<String>> responseHead = conn.getHeaderFields();
			System.out.println("responseHead:"+responseHead.toString());
			
            //获取loginPost
			for (Entry<String, List<String>> entry : responseHead.entrySet()) {
				if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
					for (String entryValue: entry.getValue()) {
						if (entryValue != null) {
							if (entryValue.toLowerCase().contains("jsessionid")) {
								String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
								resultJson.put("JSESSIONID", xlbValue);
							} else if (entryValue.toLowerCase().contains("session")) {
								String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("session", sessionValue);
							} else if (entryValue.toLowerCase().contains("x-lb")) {
								String xlbValue = entryValue.substring(5, entryValue.indexOf(";"));
								resultJson.put("xlb", xlbValue);
							}else if (entryValue.toLowerCase().contains("tokenuuid")) {
								String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
								resultJson.put("tokenUUID", sessionValue);
							}else if (entryValue.toLowerCase().contains("tokenid")) {
								String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
								resultJson.put("tokenId", xlbValue);
							}
						}
					}
				}
			}
			
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
	}
}
