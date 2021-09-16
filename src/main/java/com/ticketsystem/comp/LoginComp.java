package com.ticketsystem.comp;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	Logger log = LogManager.getLogger(LoginComp.class);
	
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
			log.info("登录接口---url["+loginUrl+"]---param["+encryptStr+"]");
			JSONObject logInPost = postSender.loginPost(loginUrl, encryptStr);
			if (logInPost.get("responseHead") != null) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)logInPost.get("responseHead");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									loginResult.put("JSESSIONID", xlbValue);
								} else if (entryValue.toLowerCase().contains("session")) {
									String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
									loginResult.put("session", sessionValue);
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
									loginResult.put("tokenUUID", sessionValue);
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
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
			log.info("确认航班---url["+pointsUrl+"]---param["+pointsDataStr+"]---["+bookCookie+"]");
			postSender.pointsPost(pointsUrl, pointsDataStr, bookCookie);
			
			//选择航班
			String chooseUrl = DemoData.chooseUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String chooseDataStr = "farefamilycode="+flightPackager2.farefamily+"&seqNum="+flightPackager2.priceInfoSeq+"&type=1&airline="+flightPackager2.airline+"&validate="+flightPackager2.priceInfoSeq+"#"+flightPackager2.priceInfoSeq2+"#"+flightPackager2.airline+"#1#"+flightPackager2.farefamily+"#"+flightPackager2.cabinClass+"#"+flightPackager2.flightNumber+"#ONE_WAY#"+flightPackager2.baseFare+"&memberType=COMMON&pricetype=COMMON";
			log.info("选择航班---url["+chooseUrl+"]---param["+chooseDataStr+"]---["+bookCookie+"]");
			postSender.choosePost(chooseUrl, chooseDataStr, bookCookie);
			
			//加入购物车
			String addUrl = DemoData.add2CartUrl + "?_="+String.valueOf(Math.random()).substring(2, 15);
			String postData = "";
			log.info("加入购物车---url["+addUrl+"]---param["+postData+"]---["+bookCookie+"]");
			JSONObject addToCartPost = postSender.sendHttpPost(addUrl, postData, bookCookie);
			String uuid2 = "";
			if (addToCartPost.size()>0) {
				String addBody = addToCartPost.getString("responseBody");
				uuid2 = addBody.substring(addBody.indexOf("\"uuid\":\"")+8, addBody.indexOf("\"}"));
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
			log.info("登录接口---url["+loginUrl+"]---param["+encryptStr+"]");
			JSONObject logInPost = postSender.loginPost(loginUrl, encryptStr);
			if (logInPost.get("responseHead") != null) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)logInPost.get("responseHead");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									loginResult.put("JSESSIONID", xlbValue);
								} else if (entryValue.toLowerCase().contains("session")) {
									String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
									loginResult.put("session", sessionValue);
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
									loginResult.put("tokenUUID", sessionValue);
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
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
    	String changeFlightFrom = addData.getString("changeFlightFrom");
		
		try {
			//TODO 调用接口----------登录接口
			PostSender postSender = new PostSender();
			String tokenUUID = loginResult0.getString("tokenUUID");
			String tokenId = loginResult0.getString("tokenId");
			String session = loginResult0.getString("session");
			if(session==null || session.length()<2) {return null;}
			String bookCookie = CookieUtil.getBookCookie(tokenId, tokenUUID, session);
			
			//再查询一次
	    	String currStandBy = addData.getString("currStandBy");
	    	KnSqlManager sqlmanager = new KnSqlManager();
	    	String fromCityCode = sqlmanager.getCityInfo(fromCity).getString("cityCode");
	    	if("1".equals(changeFlightFrom)){
	    		fromCityCode = "AirCnBEIJING888";//此处为特殊处理
	    	}
	    	String toCityCode = sqlmanager.getCityInfo(toCity).getString("cityCode");
	    	
	    	String queryUrl4 = DemoData.queryUrl3;
	    	String param4 = "_="+String.valueOf(Math.random()).substring(2, 15)+"&org="+fromCityCode+"&des="+toCityCode+"&type=oneway&depd="+fromDate+"&cals=false&adt="+currStandBy+"&chd=0&gm=0&jc=0";
	    	String queryCookie4 = "session="+session;
			log.info("登录完之后再查询一次---url["+queryUrl4+"]---param["+param4+"]---cookie["+queryCookie4+"]");
	    	new GetSender().queryGet3(queryUrl4, param4, queryCookie4);
			
			
			//确认航班
			String pointsUrl = DemoData.pointsInfoUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String pointsDataStr = "number="+fightNo.substring(2, fightNo.length())
					+"&cabin="+cabinCode+"&org="+fromCity+"&dst="+toCity
					+"&basecabinfareamount="+tripParam.getString("baseFare")
					+ "&flightdate="+fromDate+"T23%3A15%3A00.000%2B0000"
					+ "&segment="+fromCity+"%7C"+toCity+"%2C1%2CCNY%2C1630";

			log.info("确认航班---url["+pointsUrl+"]---param["+pointsDataStr+"]---cookie["+bookCookie+"]");
			postSender.pointsPost(pointsUrl, pointsDataStr, bookCookie);
			
			//选择航班
			String chooseUrl = DemoData.chooseUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String chooseDataStr = "farefamilycode="+tripParam.getString("fareFamily")
				+"&seqNum="+tripParam.getString("priceInfoSeq")
				+"&type=1"
				+"&airline="+tripParam.getString("airline")
				+"&validate="+tripParam.getString("priceInfoSeq")+"%23"+tripParam.getString("priceInfoSeq2")+"%23"+tripParam.getString("airline")+"%231%23"+tripParam.getString("fareFamily")+"%23"+cabinCode+"%23"+tripParam.getString("flightNumber")+"%23ONE_WAY%23"+tripParam.getString("baseFare")
				+"&memberType=COMMON&pricetype=COMMON&suppliercode=";
			String chooseCookie = "session="+session;
			log.info("选择航班---url["+chooseUrl+"]---param["+chooseDataStr+"]---cookie["+chooseCookie+"]");
			postSender.choosePost(chooseUrl, chooseDataStr, chooseCookie);
			
			//加入购物车
			String addUrl = DemoData.add2CartUrl + "?_="+String.valueOf(Math.random()).substring(2, 15);
			String postData = "";
			log.info("加入购物车---url["+addUrl+"]---param["+postData+"]---cookie["+bookCookie+"]");
			JSONObject addToCartPost = postSender.sendHttpPost(addUrl, postData, bookCookie);
			String uuid2 = "";
			if (addToCartPost.size()>0) {
				String addBody = addToCartPost.getString("responseBody");
				if(addBody.contains("\"uuid\":\"")) {
					uuid2 = addBody.substring(addBody.indexOf("\"uuid\":\"")+8, addBody.indexOf("\"}"));
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
			log.info("1、重新进入主页---url["+indexUrl+"]");
			GetPostTest5.indexGet(indexUrl);
			
			//2、检查
			String checkUrl1 = "http://www.flycua.com/app/login/queryUserStatus";
			String checkparam1 = "_="+String.valueOf(Math.random()).substring(2, 15);
			log.info("2、检查登录状态---url["+checkUrl1+"]---param["+checkparam1+"]");
			JSONObject checkRes1 = GetPostTest5.checkGet1(checkUrl1, checkparam1);
			//获取session
			String checkSession1 = "";
			if(checkRes1!=null) {
				String checkHeaders = checkRes1.getString("headers");
				if(checkHeaders!=null&&checkHeaders.contains("set-cookie=[session=s~")) {
					int startIndex = checkHeaders.indexOf("set-cookie=[session=s~")+20;
					int endIndex = checkHeaders.indexOf("; Path=/; Expires=");
					checkSession1 = checkHeaders.substring(startIndex, endIndex);
				}
			}
			//3、跳转至登录页
			String loginUrl1 = "http://www.flycua.com/login/";
			String loginParam1 = "";
			String loginCookie1 = "_ga=GA1.2.1848051245.1626545196; "
					+ "flycua_user_cookie=true; "
					+ "_gid=GA1.2.2119893899.1627116780; "
					+ "session="+checkSession1+"";

			log.info("3、跳转至登录页---url["+loginUrl1+"]---param["+loginParam1+"]---cookie["+loginCookie1+"]");
			GetPostTest5.loginGet1(loginUrl1, loginParam1, loginCookie1);
			
			
			//4、获取UUID
			String getUuidUrl = "http://www.flycua.com/app/loginRegister/generateUUID";
			String getUuidParam = "_="+String.valueOf(Math.random()).substring(2, 15);
			String getUuidCookie = "_ga=GA1.2.1848051245.1626545196; "
					+ "flycua_user_cookie=true; "
					+ "_gid=GA1.2.2119893899.1627116780; "
					+ "X-LB=2.729.6fd82452.50; "
					+ "_gat=1; "
					+ "session="+checkSession1+"";

			log.info("4、获取UUID---url["+getUuidUrl+"]---param["+getUuidParam+"]---cookie["+getUuidCookie+"]");
			JSONObject getUuidRes = GetPostTest5.getUuidGet1(getUuidUrl, getUuidParam, getUuidCookie);
			String getUuidBody = getUuidRes.getString("result");
			//String loginUUID = "";
			if(getUuidBody!=null && getUuidBody.contains("\"UUID\":\"")) {
				//int startIndex = getUuidBody.indexOf("\"UUID\":\"")+8;
				//int endIndex = getUuidBody.indexOf("\"}");
				//loginUUID = getUuidBody.substring(startIndex, endIndex);
			}
			
			//5、刷新随机码
			try {
				int waitTime = 1;
				log.info("5、等待"+waitTime+"秒，等待刷新随机码！");
				int sleepTime = 0;
				while(true) {
					if(sleepTime==waitTime) {break;}
					log.info(sleepTime+"s");
					Thread.sleep(1000);
					sleepTime++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//6、登陆准备-获取随机码
			String validateUrl1 = "http://www.flycua.com/app/login/validate";
			String validateParam1 = "_="+String.valueOf(Math.random()).substring(2, 15);
			String validateCookie1 = "_ga=GA1.2.1848051245.1626545196; "
					+ "flycua_user_cookie=true; "
					+ "_gid=GA1.2.2119893899.1627116780; "
					+ "session="+checkSession1+"; "
					+ "X-LB=2.729.6fd82452.50; _gat=1";

			log.info("6、登录准备-获取随机码---url["+validateUrl1+"]---param["+validateParam1+"]---cookie["+validateCookie1+"]");
			JSONObject validateRes1 = GetPostTest5.validateGet1(validateUrl1, validateParam1, validateCookie1);
			String fileName = validateRes1.getString("fileName");
			
			//7、识别随机码
			String fieFullName = "C:/img/"+fileName;
			String accurate = Accurate5.accurate(fieFullName);
			JSONObject accurateJson = new JSONObject();
			String validateCode = "";
			if (accurate==null || accurate.length()<7) {
				log.error("百度识别随机码失败！");
				return loginJson;
			}
			accurateJson = JSONObject.parseObject(accurate);
			JSONArray accArr = accurateJson.getJSONArray("words_result");
			if(accArr==null || accArr.size()<1) {
				log.error("百度识别随机码失败！");
				return loginJson;
			}
			String ss = accArr.getJSONObject(0).getString("words");
			validateCode = ss.trim().replaceAll(" ", "");
			log.info("7、百度识别结果为:"+fileName+"--"+validateCode+"");
			
			//8、登录-触发登录
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

			log.info("8、登录-触发登录---url["+userLoginUrl1+"]---param["+userLoginParam+"]---cookie["+userLoginCookie+"]");
			JSONObject userLoginRes1 = GetPostTest5.userLoginPost1(userLoginUrl1, userLoginParam, userLoginCookie);
			String userLoginResult = userLoginRes1.getString("result");
			String session = "";
			String tokenUUID = "";
			String tokenId = "";
			if (userLoginResult!=null && userLoginResult.contains("\"code\":\"success\"")) {
				log.info("登录成功");
				String userLoginHeader = userLoginRes1.getString("headers");
				if (userLoginHeader!= null &&userLoginHeader.length()>5) {
					Map<String, List<String>> headMap = (Map<String, List<String>>)userLoginRes1.get("headMap");
					for (Entry<String, List<String>> entry : headMap.entrySet()) {
						String headKey = entry.getKey();
						if ("set-cookie".equalsIgnoreCase(headKey)) {
							for (String entryValue: entry.getValue()) {
								if (entryValue != null) {
									if (entryValue.toLowerCase().contains("jsessionid")) {
										//String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
										//JsessionId=xlbValue;
									} else if (entryValue.toLowerCase().contains("session")) {
										String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
										session = xlbValue;
									} else if (entryValue.toLowerCase().contains("tokenuuid")) {
										String xlbValue = entryValue.substring(10, entryValue.indexOf(";"));
										tokenUUID = xlbValue;
									}else if (entryValue.toLowerCase().contains("tokenid")) {
										String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
										tokenId = xlbValue;
									}
								}
							}
						}
					}
				}
				loginJson.put("session", session);
				loginJson.put("tokenId", tokenId);
				loginJson.put("tokenUUID", tokenUUID);
				
			} else {
				log.error("登录失败");
				return loginJson;
			}
			
			/*
			*/
		} catch (Exception e) {
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
