package com.ticketsystem.comp;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.CookieUtil;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.net.PostSender;
import com.ticketsystem.test.Accurate5;
import com.ticketsystem.test.FlightPackager2;
import com.ticketsystem.test.GetPostTest5;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.KnSqlManager;

public class LoginComp {
	public JSONObject accountLogin(JSONObject addData, String encryptStr, String session) {
		JSONObject loginResult = new JSONObject();
		String fromCity = addData.getString("fromCityCode");
    	String toCity = addData.getString("toCityCode");
    	String fromDate = addData.getString("fromDate");
    	String fightNo = addData.getString("fightNo");
    	String cabinCode = addData.getString("cabinCode");
    	FlightPackager2 flightPackager2 = new FlightPackager2();
		
		//NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCHb/yyON/GL4rvJVQxNcX1J9Ui22MPCTWHxcaNCrUiOmdhfbhpKhRZPxIegcZRKs7OCN1CCNH1QMiZZxgP5bdFD
		
		try {
			//TODO 调用接口----------查询订单接口
			JSONObject queryPost2 = new QueryComp().queryTicket2(addData);
			//组装flight
			String flightStr = queryPost2.getString("responseBody");
			JSONObject flightData = JSONObject.parseObject(flightStr);
			flightPackager2.setBookData(flightData, fightNo, cabinCode);
			
			//TODO 调用接口----------登录接口
			PostSender postSender = new PostSender();
			String loginUrl = DemoData.loginUrl;
			JSONObject logInPost = postSender.loginPost(loginUrl, encryptStr);
			if (logInPost.get("responseHead") != null) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)logInPost.get("responseHead");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());	
					if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									System.out.println("JSESSIONID="+xlbValue);
									loginResult.put("JSESSIONID", xlbValue);
								} else if (entryValue.toLowerCase().contains("session")) {
									String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("sessionValue="+sessionValue);
									loginResult.put("session", sessionValue);
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
									System.out.println("tokenUUID="+sessionValue);
									loginResult.put("tokenUUID", sessionValue);
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("tokenId="+xlbValue);
									loginResult.put("tokenId", xlbValue);
								}
							}
						}
					}
				}
			}
			
			String tokenUUID = loginResult.getString("tokenUUID");
			String tokenId = loginResult.getString("tokenId");
			String bookCookie = CookieUtil.getBookCookie(tokenId, tokenUUID, session);
			
			//确认航班
			String pointsUrl = DemoData.pointsInfoUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String pointsDataStr = "number="+fightNo.substring(2, fightNo.length())+"&cabin="+cabinCode+"&org="+fromCity+"&dst="+toCity+"&basecabinfareamount="+flightPackager2.baseFare
					+ "&flightdate="+fromDate+"T23%3A15%3A00.000%2B0000"
					+ "&segment="+fromCity+"%7C"+toCity+"%2C1%2CCNY%2C1630";
			JSONObject pointsPost = postSender.pointsPost(pointsUrl, pointsDataStr, bookCookie);
			if (pointsPost.size()>0) {
				String chooseBody = pointsPost.getString("responseBody");
				if (chooseBody.contains("uuid")) {
					String uuidStr1 = chooseBody.substring(chooseBody.indexOf("\"uuid\":\"")+8, chooseBody.indexOf("\"}"));
					System.out.println(uuidStr1);					
				}
			}
			
			//选择航班
			String chooseUrl = DemoData.chooseUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String chooseDataStr = "farefamilycode="+flightPackager2.farefamily+"&seqNum="+flightPackager2.priceInfoSeq+"&type=1&airline="+flightPackager2.airline+"&validate="+flightPackager2.priceInfoSeq+"#"+flightPackager2.priceInfoSeq2+"#"+flightPackager2.airline+"#1#"+flightPackager2.farefamily+"#"+flightPackager2.cabinClass+"#"+flightPackager2.flightNumber+"#ONE_WAY#"+flightPackager2.baseFare+"&memberType=COMMON&pricetype=COMMON";
			JSONObject choosePost = postSender.choosePost(chooseUrl, chooseDataStr, bookCookie);
			if (choosePost.size()>0) {
				String chooseBody = choosePost.getString("responseBody");
				if (chooseBody.contains("uuid")) {
					String uuidStr1 = chooseBody.substring(chooseBody.indexOf("\"uuid\":\"")+8, chooseBody.indexOf("\"}"));
					System.out.println(uuidStr1);					
				}
			}
			
			//加入购物车
			String addUrl = DemoData.add2CartUrl + "?_="+String.valueOf(Math.random()).substring(2, 15);
			String postData = "";
			JSONObject addToCartPost = postSender.sendHttpPost(addUrl, postData, bookCookie);
			String uuid2 = "";
			if (choosePost.size()>0) {
				String addBody = addToCartPost.getString("responseBody");
				uuid2 = addBody.substring(addBody.indexOf("\"uuid\":\"")+8, addBody.indexOf("\"}"));
				System.out.println(uuid2);
			}
			loginResult.put("uuid", uuid2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginResult;
	}
	
	
	public JSONObject accountLogin2(String encryptStr, String session) {
		JSONObject loginResult = new JSONObject();
		try {
			//TODO 调用接口----------登录接口
			PostSender postSender = new PostSender();
			String loginUrl = DemoData.loginUrl;
			JSONObject logInPost = postSender.loginPost(loginUrl, encryptStr);
			if (logInPost.get("responseHead") != null) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)logInPost.get("responseHead");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());	
					if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									System.out.println("JSESSIONID="+xlbValue);
									loginResult.put("JSESSIONID", xlbValue);
								} else if (entryValue.toLowerCase().contains("session")) {
									String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("sessionValue="+sessionValue);
									loginResult.put("session", sessionValue);
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
									System.out.println("tokenUUID="+sessionValue);
									loginResult.put("tokenUUID", sessionValue);
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("tokenId="+xlbValue);
									loginResult.put("tokenId", xlbValue);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginResult;
	}
	
	public JSONObject accountLogin3(JSONObject addData, String accountNo, String accountPas, String encryptStr, JSONObject tripParam, JSONObject loginResult0) {
		//JSONObject logInResult = new JSONObject();
		String fromCity = addData.getString("fromCityCode");
    	String toCity = addData.getString("toCityCode");
    	String fromDate = addData.getString("fromDate");
    	String fightNo = addData.getString("fightNo");
    	String cabinCode = addData.getString("cabinCode");
		
		try {
			//TODO 调用接口----------登录接口
			PostSender postSender = new PostSender();
			/*
			String loginUrl = DemoData.loginUrl;
			JSONObject logInPost = postSender.loginPost(loginUrl, encryptStr);
			if (logInPost.get("responseHead") != null) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)logInPost.get("responseHead");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());	
					if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									System.out.println("JSESSIONID="+xlbValue);
									logInResult.put("JSESSIONID", xlbValue);
								} else if (entryValue.toLowerCase().contains("session")) {
									String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("sessionValue="+sessionValue);
									logInResult.put("session", sessionValue);
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
									System.out.println("tokenUUID="+sessionValue);
									logInResult.put("tokenUUID", sessionValue);
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("tokenId="+xlbValue);
									logInResult.put("tokenId", xlbValue);
								}
							}
						}
					}
				}
			}
			*/
			
			
			String tokenUUID = loginResult0.getString("tokenUUID");
			String tokenId = loginResult0.getString("tokenId");
			String session = loginResult0.getString("session");
			if(session==null || session.length()<2) {return null;}
			String bookCookie = CookieUtil.getBookCookie(tokenId, tokenUUID, session);
			
			//再查询一次
	    	String currStandBy = addData.getString("currStandBy");
	    	KnSqlManager sqlmanager = new KnSqlManager();
	    	String fromCityCode = sqlmanager.getCityInfo(fromCity).getString("cityCode");
	    	String toCityCode = sqlmanager.getCityInfo(toCity).getString("cityCode");
	    	
	    	String queryUrl3 = DemoData.queryUrl2;
	    	//flightType=oneway&Origin=CITY_BJS_CN&Destination=CitCnSHANGHA364&departDate=2021-07-21&adults=1&children=0&militaryDisability=0&policeRemnants=0
	    	String param3 = "flightType=oneway&Origin="+fromCityCode+"&Destination="+toCityCode+"&departDate="+fromDate+"&adults="+currStandBy+"&children=0&militaryDisability=0&policeRemnants=0";
	    	String queryCookie3 = "session="+session;
	    	new GetSender().queryGet3(queryUrl3, param3, queryCookie3);
	    	
	    	String queryUrl4 = DemoData.queryUrl3;
	    	String param4 = "_="+String.valueOf(Math.random()).substring(2, 15)+"&org="+fromCityCode+"&des="+toCityCode+"&type=oneway&depd="+fromDate+"&cals=false&adt="+currStandBy+"&chd=0&gm=0&jc=0";
	    	String queryCookie4 = "session="+session;
	    	new GetSender().queryGet3(queryUrl4, param4, queryCookie4);
			
			
			//确认航班
			String pointsUrl = DemoData.pointsInfoUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String pointsDataStr = "number="+fightNo.substring(2, fightNo.length())
					+"&cabin="+cabinCode+"&org="+fromCity+"&dst="+toCity
					+"&basecabinfareamount="+tripParam.getString("baseFare")
					+ "&flightdate="+fromDate+"T23%3A15%3A00.000%2B0000"
					+ "&segment="+fromCity+"%7C"+toCity+"%2C1%2CCNY%2C1630";
			JSONObject pointsPost = postSender.pointsPost(pointsUrl, pointsDataStr, bookCookie);
			if (pointsPost.size()>0) {
				String chooseBody = pointsPost.getString("responseBody");
				System.out.println(chooseBody);
			}
			
			//选择航班
			String chooseUrl = DemoData.chooseUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String chooseDataStr = "farefamilycode="+tripParam.getString("fareFamily")
				+"&seqNum="+tripParam.getString("priceInfoSeq")
				+"&type=1"
				+"&airline="+tripParam.getString("airline")
				+"&validate="+tripParam.getString("priceInfoSeq")+"%23"+tripParam.getString("priceInfoSeq2")+"%23"+tripParam.getString("airline")+"%231%23"+tripParam.getString("fareFamily")+"%23"+cabinCode+"%23"+tripParam.getString("flightNumber")+"%23ONE_WAY%23"+tripParam.getString("baseFare")
				+"&memberType=COMMON&pricetype=COMMON&suppliercode=";
			String chooseCookie = "session="+session;
			JSONObject choosePost = postSender.choosePost(chooseUrl, chooseDataStr, chooseCookie);
			if (choosePost.size()>0) {
				String chooseBody = choosePost.getString("responseBody");
				System.out.println(chooseBody);
			}
			
			//加入购物车
			String addUrl = DemoData.add2CartUrl + "?_="+String.valueOf(Math.random()).substring(2, 15);
			String postData = "";
			JSONObject addToCartPost = postSender.sendHttpPost(addUrl, postData, bookCookie);
			String uuid2 = "";
			if (choosePost.size()>0) {
				String addBody = addToCartPost.getString("responseBody");
				if(addBody.contains("\"uuid\":\"")) {
					uuid2 = addBody.substring(addBody.indexOf("\"uuid\":\"")+8, addBody.indexOf("\"}"));
					System.out.println(uuid2);
				} else {
					return null;
				}
			}
			loginResult0.put("uuid", uuid2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginResult0;
	}
	
	public JSONObject loginLoop(String accountNo, String accountPas) {
		JSONObject loginJson = new JSONObject();
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
			String loginCookie1 = "_ga=GA1.2.1848051245.1626545196; "
					+ "flycua_user_cookie=true; "
					+ "_gid=GA1.2.2119893899.1627116780; "
					+ "session="+checkSession1+"";
			JSONObject loginRes1 = GetPostTest5.loginGet1(loginUrl1, loginParam1, loginCookie1);
			
			
			//4、获取UUID
			String getUuidUrl = "http://www.flycua.com/app/loginRegister/generateUUID";
			String getUuidParam = "_="+String.valueOf(Math.random()).substring(2, 15);
			String getUuidCookie = "_ga=GA1.2.1848051245.1626545196; "
					+ "flycua_user_cookie=true; "
					+ "_gid=GA1.2.2119893899.1627116780; "
					+ "X-LB=2.729.6fd82452.50; "
					+ "_gat=1; "
					+ "session="+checkSession1+"";
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
			String validateCookie1 = "_ga=GA1.2.1848051245.1626545196; "
					+ "flycua_user_cookie=true; "
					+ "_gid=GA1.2.2119893899.1627116780; "
					+ "session="+checkSession1+"; "
					+ "X-LB=2.729.6fd82452.50; _gat=1";
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
				return loginJson;
			}
			accurateJson = JSONObject.parseObject(accurate);
			JSONArray accArr = accurateJson.getJSONArray("words_result");
			if(accArr==null || accArr.size()<1) {
				System.out.println("百度识别随机码失败！");
				return loginJson;
			}
			String ss = accArr.getJSONObject(0).getString("words");
			validateCode = ss.trim().replaceAll(" ", "");
			System.out.println("==========百度识别结果为:"+fileName+"--"+validateCode+"==========");
			
			//8、登录-触发登录
			//http://www.flycua.com/app/login/userLogin?_=1627204027546
			String userLoginUrl1 = "http://www.flycua.com/app/login/userLogin?_="+String.valueOf(Math.random()).substring(2, 15);
			String userLoginParam = "type=P"
					+ "&id="+accountNo+""
					+ "&pass="+accountPas+""
					+ "&verifi="+validateCode+"";
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
			String checkResult2 = checkRes2.getString("result");
			if(checkResult2!=null && checkResult2.length()<1) {
				System.out.println("==========登录失败==========");
				return loginJson;
			}
			if (checkResult2.contains("\"isLogin\":true")) {
				System.out.println("==========登录成功==========");
				loginJson.put("session", session);
				loginJson.put("tokenId", tokenId);
				loginJson.put("tokenUUID", tokenUUID);
			}
			
			/*
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginJson;
	}
	
	public synchronized JSONObject login2(String accountNo, String accountPas) {
		JSONObject loginResult = new JSONObject();
		for (int i=0;i<50;i++) {
			JSONObject loginRes = loginLoop(accountNo, accountPas);
			if(loginRes!=null) {
				String loginSession = loginRes.getString("session");
				String loginTokenId = loginRes.getString("tokenId");
				String loginTokenUUID = loginRes.getString("tokenUUID");
				if(loginSession!=null && loginSession.length()>0
						&&loginTokenId!=null && loginTokenId.length()>0
						&&loginTokenUUID!=null && loginTokenUUID.length()>0) {
					loginResult.put("session", loginSession);
					loginResult.put("tokenId", loginTokenId);
					loginResult.put("tokenUUID", loginTokenUUID);
					break;
				}
			}
		}
		return loginResult;
	}
}
