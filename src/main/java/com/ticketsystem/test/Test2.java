package com.ticketsystem.test;

import java.io.IOException;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;

public class Test2 {
	
	public static void main(String[] args) {
		Test2.testLogin();
	}
	
	public static void testLogin() {
		String checkUrl = "http://www.flycua.com/app/login/queryUserStatus";
		String param = "_="+String.valueOf(Math.random()).substring(2, 15);
		
		JSONObject checkPost = GetPostTest2.checkGet(checkUrl, param);
		String checkHead = checkPost.getString("head");
		int firstStart = checkHead.indexOf("session=s~")-1;
		int firstEnd = checkHead.indexOf("; Path=/; Expires=");
		String firstSession = checkHead.substring(firstStart, firstEnd);
		System.out.println("firstSession=="+firstSession);
		
		String valideCookie = "_ga=GA1.2.1848051245.1626545196; flycua_user_cookie=true; _gid=GA1.2.937509221.1626978245; session="
				+firstSession+"; X-LB=2.727.65daae9e.50; _gat=1";
		String valideUrl = "http://www.flycua.com/app/login/validate";
		String valideParam = "_="+String.valueOf(Math.random()).substring(2, 15);
		
		JSONObject validateGet = GetPostTest2.getImageGet(valideUrl, valideParam, valideCookie);
		String fileName = validateGet.getString("fileName");
		System.out.println(fileName);
		
		//百度识别
		String fieFullName = "C:/img/"+fileName;
		String accurate = Accurate2.accurate(fieFullName);
		System.out.println(accurate);
		JSONObject accurateJson = new JSONObject();
		if (accurate.length()>7) {
			accurateJson = JSONObject.parseObject(accurate);
			String ss = accurateJson.getJSONArray("words_result").getJSONObject(0).getString("words");
			ss = ss.trim().replaceAll(" ", "");
			System.out.println("word===="+ss);
			System.out.println("ss的长度为："+ss.length());
			
			//第一次登录
			String loginUrl = "https://higo.flycua.com/ffp/member/login";
			String loginData = "NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCG+rNXIDFj/CquYsimyqEmBdF9Wp6yYfyth9eqBA1jHECZxFcebg/Xx2cJyLaqj9Y30aCrOS9m2n/hdiBsuhyBx";
			try {
				JSONObject logInPost = GetPostTest2.loginPost3(loginUrl, loginData, valideCookie);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
}
