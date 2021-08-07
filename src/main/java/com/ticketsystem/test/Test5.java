package com.ticketsystem.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test5 {
	
	public static void main(String[] args) {
		try {
			
			//1、主页
			//http://www.flycua.com
			String indexUrl = "http://www.flycua.com";
			String indexParam = "";
			//String indexParam = "_="+String.valueOf(Math.random()).substring(2, 15);
			JSONObject indexRes = GetPostTest5.indexGet(indexUrl);
			
			//2、检查
			String checkUrl1 = "http://www.flycua.com/app/login/queryUserStatus";
			String checkparam1 = "_="+String.valueOf(Math.random()).substring(2, 15);
			JSONObject checkRes1 = GetPostTest5.checkGet1(checkUrl1, checkparam1);
			//获取session
			String checkSession1 = "";
			if(checkRes1!=null) {
				String checkHeaders = checkRes1.getString("headers");
				if(checkHeaders!=null&&checkHeaders.contains("set-cookie=[session=s~")) {
					int startIndex = checkHeaders.indexOf("set-cookie=[session=s~")+20;
					int endIndex = checkHeaders.indexOf("; Path=/; Expires=");
					checkSession1 = checkHeaders.substring(startIndex, endIndex);
					System.out.println("checkSession=========="+checkSession1);
				}
			}
			//3、登录-跳转至登录页
			//http://www.flycua.com/login/
			//_ga=GA1.2.1848051245.1626545196; flycua_user_cookie=true; _gid=GA1.2.2119893899.1627116780; session=s~9c17a9be-2480-4e94-9a04-6c620cfa8a75.a66357ceaef947a9aa58ef6c4399f6a7
			String loginUrl1 = "http://www.flycua.com/login/";
			String loginParam1 = "";
			String loginCookie1 = "_ga=GA1.2.1848051245.1626545196; flycua_user_cookie=true; _gid=GA1.2.2119893899.1627116780; session="+checkSession1+"";
			JSONObject loginRes1 = GetPostTest5.loginGet1(loginUrl1, loginParam1, loginCookie1);
			
			
			//4、获取UUID
			String getUuidUrl = "http://www.flycua.com/app/loginRegister/generateUUID";
			String getUuidParam = "_="+String.valueOf(Math.random()).substring(2, 15);
			String getUuidCookie = "_ga=GA1.2.1848051245.1626545196; flycua_user_cookie=true; _gid=GA1.2.2119893899.1627116780; X-LB=2.729.6fd82452.50; _gat=1; session="+checkSession1+"";
			JSONObject getUuidRes = GetPostTest5.getUuidGet1(getUuidUrl, getUuidParam, getUuidCookie);
			String getUuidBody = getUuidRes.getString("result");
			String loginUUID = "";
			if(getUuidBody!=null && getUuidBody.contains("\"UUID\":\"")) {
				int startIndex = getUuidBody.indexOf("\"UUID\":\"")+8;
				int endIndex = getUuidBody.indexOf("\"}");
				loginUUID = getUuidBody.substring(startIndex, endIndex);
			}
			System.out.println("loginUUID=========="+loginUUID);
			
			//5、刷新随机码
			try {
				int waitTime = 1;
				System.out.println("等待"+waitTime+"秒，等待刷新随机码！");
				int sleepTime = 0;
				while(true) {
					if(sleepTime==waitTime) {break;}
					System.out.println(sleepTime+"s");
					Thread.sleep(1000);
					sleepTime++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//6、登录-获取随机码
			//http://www.flycua.com/app/login/validate?_=0.3302511608257426
			//_ga=GA1.2.1848051245.1626545196; flycua_user_cookie=true; _gid=GA1.2.2119893899.1627116780; session=s~9c17a9be-2480-4e94-9a04-6c620cfa8a75.a66357ceaef947a9aa58ef6c4399f6a7; X-LB=2.729.6fd82452.50; _gat=1
			String validateUrl1 = "http://www.flycua.com/app/login/validate";
			String validateParam1 = "_="+String.valueOf(Math.random()).substring(2, 15);
			String validateCookie1 = "_ga=GA1.2.1848051245.1626545196; flycua_user_cookie=true; _gid=GA1.2.2119893899.1627116780; session="+checkSession1+"; X-LB=2.729.6fd82452.50; _gat=1";
			JSONObject validateRes1 = GetPostTest5.validateGet1(validateUrl1, validateParam1, validateCookie1);
			String fileName = validateRes1.getString("fileName");
			
			//7、登录-识别随机码
			String fieFullName = "C:/img/"+fileName;
			String accurate = Accurate5.accurate(fieFullName);
			System.out.println(accurate);
			JSONObject accurateJson = new JSONObject();
			String validateCode = "";
			if (accurate==null || accurate.length()<7) {
				System.out.println("百度识别随机码失败！");
				return;
			}
			accurateJson = JSONObject.parseObject(accurate);
			JSONArray accArr = accurateJson.getJSONArray("words_result");
			if(accArr==null || accArr.size()<1) {
				System.out.println("百度识别随机码失败！");
				return;
			}
			String ss = accArr.getJSONObject(0).getString("words");
			validateCode = ss.trim().replaceAll(" ", "");
			System.out.println("session="+checkSession1);
			System.out.println("==========百度识别成功:"+fileName+"--"+validateCode+"==========");
			System.out.println("17656175477");
			System.out.println("z1310305");
			
			//8、登录-触发登录
			//http://www.flycua.com/app/login/userLogin?_=1627204027546
			String userLoginUrl1 = "http://www.flycua.com/app/login/userLogin?_="+String.valueOf(Math.random()).substring(2, 15);
			String userLoginParam = "type=P&id=17656175477&pass=z1310305&verifi="+validateCode+"";
			String userLoginCookie = "_ga=GA1.2.1848051245.1626545196; "
					+ "flycua_user_cookie=true; "
					+ "_gid=GA1.2.2119893899.1627116780; "
					+ "X-LB=2.729.6fd82452.50; "
					+ "_gat=1; "
					+ "session="+checkSession1+"";
			JSONObject userLoginRes1 = GetPostTest5.userLoginPost1(userLoginUrl1, userLoginParam, userLoginCookie);
			String userLoginHeader = userLoginRes1.getString("headers");
			String JsessionId = "";
			String session = "";
			String tokenUUID = "";
			String tokenId = "";
			if (userLoginHeader!= null &&userLoginHeader.length()>5) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)userLoginRes1.get("headMap");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					String headKey = entry.getKey();
					//System.out.println(headKey);
					//System.out.println(entry.getValue());	
					if ("set-cookie".equalsIgnoreCase(headKey)) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									System.out.println("JSESSIONID="+xlbValue);
									JsessionId=xlbValue;
								} else if (entryValue.toLowerCase().contains("session")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("sessionValue="+xlbValue);
									session = xlbValue;
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String xlbValue = entryValue.substring(10, entryValue.indexOf(";"));
									System.out.println("tokenUUID="+xlbValue);
									tokenUUID = xlbValue;
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("tokenId="+xlbValue);
									tokenId = xlbValue;
								}
							}
						}
					}
				}
			}
			
			
			//9、检查
			String checkUrl2 = "http://www.flycua.com/app/login/queryUserStatus";
			String checkParam2 = "_="+String.valueOf(Math.random()).substring(2, 15);
			String checkCookie2 = "_ga=GA1.2.1848051245.1626545196; "
					+ "flycua_user_cookie=true; "
					+ "_gid=GA1.2.2119893899.1627116780; "
					+ "X-LB=2.729.6fd82452.50; "
					+ "_gat=1; "
					+ "session="+session+"; "
					+ "tokenId="+tokenId+"; "
					+ "tokenUUID="+tokenUUID+"";
			JSONObject checkRes2 = GetPostTest5.checkGet2(checkUrl2, checkParam2, checkCookie2);
			
			
			
			
			
			/*
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
