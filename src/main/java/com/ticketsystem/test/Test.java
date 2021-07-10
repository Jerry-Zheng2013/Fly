package com.ticketsystem.test;

import com.alibaba.fastjson.JSONObject;

public class Test {
	
	public static void main(String[] args) {
		try {
			
			//第一次登录
			JSONObject logInPost = GetPostTest2.firstLoginPost(
					"https://higo.flycua.com/ffp/member/login", 
					"NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCHb/yyON/GL4rvJVQxNcX1J9Ui22MPCTWHxcaNCrUiOmdhfbhpKhRZPxIegcZRKs7OCN1CCNH1QMiZZxgP5bdFD"
					);
			//第一次登录成功后，获取tokenId和tokenUUID
			String tokenId = logInPost.getString("tokenId");
			String tokenUUID = logInPost.getString("tokenUUID");
			String checkCookie = TestData2.getCheckCookie(tokenId, tokenUUID);
			//第一次检查账户登录状态
			JSONObject queryPost = GetPostTest2.checkStatusGet(
	        		"http://www.flycua.com/app/login/queryUserStatus",
	        		"_=0.6013036610715838",
	        		checkCookie
	        		);
			String session = queryPost.getString("session");
			//session = "s~b96f48e4-2cf4-4a9e-8c2b-5bf8bc070bbe.930f933bdb2711d6dd1e5a669f2cd8cc";
			String loginCookie = TestData2.getLoginCookie(tokenId, tokenUUID, session);

			//第二次登录
			JSONObject logInPost2 = GetPostTest2.secondLoginPost(
					"https://higo.flycua.com/ffp/member/login", 
					"NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCHb/yyON/GL4rvJVQxNcX1J9Ui22MPCTWHxcaNCrUiOmdhfbhpKhRZPxIegcZRKs7OCN1CCNH1QMiZZxgP5bdFD",
					loginCookie
					);
			//第二次登录成功后，获取tokenId和tokenUUID
			String tokenId2 = logInPost2.getString("tokenId");
			String tokenUUID2 = logInPost2.getString("tokenUUID");
			
			//session = "s~3163b0e1-1afb-4a70-9bd7-445a79d0933c.ce5be1be347cb9e4fbb2bf19249d8df1";
			//第二次检查账户登录状态
			String checkCookie2 = TestData2.getLoginCookie(tokenId2, tokenUUID2, session);
			JSONObject queryPost2 = GetPostTest2.checkStatusGet2(
	        		"http://www.flycua.com/app/login/queryUserStatus",
	        		"_=0.6013036610715838",
	        		"session="+session+"; "
	        		);
			String session3 = queryPost2.getString("session");
			System.out.println(session3);
			
			/**
			//加入购物车
			JSONObject addToCartData = GetPostTest2.addToCartPost(
	        		"http://www.flycua.com/app/booking/addToCart?_=1625760886345",
	        		"",
	        		checkCookie2
	        		);
			String uuid = addToCartData.getString("uuid");
			System.out.println(uuid);
			
			/**
			//预定
			String bookPostData = TestData2.getBookPostData2(uuid);
			String bookCookie = TestData2.getBookCookieData2(session, tokenId, tokenUUID);
			JSONObject bookPost2 = GetPostTest2.bookPost(
					"http://www.flycua.com/app/booking/book?_=1625384445626", 
					bookPostData,
					bookCookie
					);
			*/
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
