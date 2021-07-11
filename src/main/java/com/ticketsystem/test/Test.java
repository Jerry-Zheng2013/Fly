package com.ticketsystem.test;

import com.alibaba.fastjson.JSONObject;

public class Test {
	
	public static void main(String[] args) {
		try {
			FlightPackager flightPackager = new FlightPackager();
			
			JSONObject queryPost = GetPostTest2.queryGet(
	        		"http://www.flycua.com/app/searchFlights/queryFlight",
	        		"_="+String.valueOf(Math.random()).substring(2, 15)+"&org=CITY_BJS_CN&des=CitCnSHANGHA364&type=oneway&depd=2021-07-31&cals=false&adt=1&chd=0&gm=0&jc=0"
	        		);
			String session = queryPost.getString("session");
			String flightStr = queryPost.getString("flightData");
			JSONObject flightData = JSONObject.parseObject(flightStr);
			flightPackager.setBookData(flightData, "5737", "W");
			
			//第一次登录
			JSONObject logInPost = GetPostTest2.firstLoginPost(
					"https://higo.flycua.com/ffp/member/login", 
					"NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCG+rNXIDFj/CquYsimyqEmBdF9Wp6yYfyth9eqBA1jHECZxFcebg/Xx2cJyLaqj9Y30aCrOS9m2n/hdiBsuhyBx"
					);
			//第一次登录成功后，获取tokenId和tokenUUID
			String tokenId = logInPost.getString("tokenId");
			String tokenUUID = logInPost.getString("tokenUUID");
			String checkCookie = TestData2.getCheckCookie(tokenId, tokenUUID);
			
			//第一次检查账户登录状态
			JSONObject checkPost = GetPostTest2.checkStatusGet(
	        		"http://www.flycua.com/app/login/queryUserStatus",
	        		"_=0.6013036610715838",
	        		checkCookie
	        		);
			
			session = queryPost.getString("session");
			session = "s~d4b625bf-f96a-45e7-8909-e375dca14559.3082de1f722ccfd62b48dd8d58cb83e6";
			String loginCookie = TestData2.getLoginCookie(tokenId, tokenUUID, session);
			
			//选择航班
			JSONObject chooseFlightData = GetPostTest2.chooseFlightPost(
	        		"http://www.flycua.com/app/searchFlights/chooseFlight?_="+String.valueOf(Math.random()).substring(2, 15),
	        		"farefamilycode="+flightPackager.farefamily+"&seqNum="+flightPackager.priceInfoSeq+"&type=1&airline="+flightPackager.airline+"&validate="+flightPackager.priceInfoSeq+"#"+flightPackager.priceInfoSeq2+"#"+flightPackager.airline+"#1#"+flightPackager.farefamily+"#"+flightPackager.cabinClass+"#"+flightPackager.flightNumber+"#ONE_WAY#"+flightPackager.baseFare+"&memberType=COMMON&pricetype=COMMON",
	        		loginCookie
	        		);
			String uuid = chooseFlightData.getString("uuid");
			System.out.println(uuid);
			
			//加入购物车
			JSONObject addToCartData = GetPostTest2.addToCartPost(
	        		"http://www.flycua.com/app/booking/addToCart?_="+String.valueOf(Math.random()).substring(2, 15),
	        		"",
	        		loginCookie
	        		);
			String uuid2 = addToCartData.getString("uuid");
			System.out.println(uuid2);
			
			//预定
			String bookData = flightPackager.getBookData(uuid2);
			System.out.println("bookData==="+bookData);
			System.out.println("loginCookie==="+loginCookie);
			System.out.println("============");
			
			JSONObject bookPost2 = GetPostTest2.bookPost(
					"http://www.flycua.com/app/booking/book?_="+Math.random(), 
					bookData,
					loginCookie
					);
			
			
			/**
			//第二次登录
			JSONObject logInPost2 = GetPostTest2.secondLoginPost(
					"https://higo.flycua.com/ffp/member/login", 
					"NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCHb/yyON/GL4rvJVQxNcX1J9Ui22MPCTWHxcaNCrUiOmdhfbhpKhRZPxIegcZRKs7OCN1CCNH1QMiZZxgP5bdFD",
					loginCookie
					);
			//第二次登录成功后，获取tokenId和tokenUUID
			String tokenId2 = logInPost2.getString("tokenId");
			String tokenUUID2 = logInPost2.getString("tokenUUID");
			
			
			String session = "s~ad913a17-8fc8-4698-973d-91b8156cd8a0.7be7b1d697ea0ec27ecd1b20f555e83b";
			//第二次检查账户登录状态
			String checkCookie2 = TestData2.getLoginCookie(tokenId2, tokenUUID2, session);
			JSONObject queryPost2 = GetPostTest2.checkStatusGet2(
	        		"http://www.flycua.com/app/login/queryUserStatus",
	        		"_=0.6013036610715838",
	        		"session="+session+"; "
	        		);
			String session3 = queryPost2.getString("session");
			System.out.println(session3);
			
			
			String session = "s~8367814a-306d-4e53-8c62-a07ceeb0fa34.a02524dc4cfca25b159b77e2b5796728";
			String chooseFlightCookie = TestData2.getChooseFlightCookie(tokenId, tokenUUID, session);
			//查询航班
			/*JSONObject queryFlightData = GetPostTest2.queryFlightPost(
	        		"http://www.flycua.com/app/searchFlights/queryPointsInfo?_=1625889496117",
	        		"number=5737&cabin=D&org=PKX&dst=SHA&basecabinfareamount=568&flightdate=2021-07-30T23:15:00.000+0000&segment=PKX|SHA,1,CNY,1630",
	        		chooseFlightCookie
	        		);
			
			//"number=5737&cabin=C&org=PKX&dst=SHA&basecabinfareamount=2598&flightdate=2021-07-31T23%3A15%3A00.000%2B0000&segment=PKX%7CSHA%2C1%2CCNY%2C1630",
			//"number=5737&cabin=C&org=PKX&dst=SHA&basecabinfareamount=2598&flightdate=2021-07-31T23%3A15%3A00.000%2B0000&segment=PKX%7CSHA%2C1%2CCNY%2C1630",
			
			
			//String uuid = chooseFlightData.getString("uuid");
			//System.out.println(uuid);
			
			//选择航班
			JSONObject chooseFlightData = GetPostTest2.chooseFlightPost(
	        		"http://www.flycua.com/app/searchFlights/chooseFlight?_=1625887358476",
	        		"farefamilycode=SXF&seqNum=2&type=1&airline=KN&validate=2#2#KN#1#SXF#W#5737#ONE_WAY#1628&memberType=COMMON&pricetype=COMMON",
	        		chooseFlightCookie
	        		);
			String uuid = chooseFlightData.getString("uuid");
			System.out.println(uuid);
			
			
			//加入购物车
			JSONObject addToCartData = GetPostTest2.addToCartPost(
	        		"http://www.flycua.com/app/booking/addToCart?_=1625760886345",
	        		"",
	        		chooseFlightCookie
	        		);
			String uuid2 = addToCartData.getString("uuid");
			System.out.println(uuid2);
			
			
			//预定
			String bookPostData = TestData2.getBookPostData2(uuid2);
			//String bookCookie = TestData2.getBookCookieData2(session, tokenId, tokenUUID);
			JSONObject bookPost2 = GetPostTest2.bookPost(
					"http://www.flycua.com/app/booking/book?_=1625384445626", 
					bookPostData,
					chooseFlightCookie
					);
			
			/**
			 
			*/
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
