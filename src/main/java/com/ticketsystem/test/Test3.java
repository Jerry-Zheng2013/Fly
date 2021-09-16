package com.ticketsystem.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.util.SqlManager;

public class Test3 {
	
	static Logger log = LogManager.getLogger(Test3.class);
	
	private static String sendStatus = "";
	
	public static void main(String[] args) {
		String projectId = "84153";
		String getToken = "http://api.do889.com:81/api/logins";
		String paramStr = "username=18568550369&password=741214ab";
		//JSONObject queryPost = GetPostTest3.getTokenGet(getToken, paramStr);
		String token = "vmNn1KiIdhms9f5fSw7SFOlADOG+TggeT7rVuYvTTH4Yoij/X6JNNwjbt5gMY6/DBFtLsEoTaSfkXQ4tz71lzAdfckz77OU/x4LtXnl0x9nDPu0eyLUpnKgoF80wcuVkXj0mK/4lLFMFDcJfR/NmRXlrPjeb+KeFpZdu/Mf0z88=";
		
		String getMobileUrl = "http://api.do889.com:81/api/get_mobile"; 
		String mobileParam = "token="+token+"&project_id="+projectId+"";
		String phoneNo = "";
		
		JSONObject queryPost2 = GetPostTest3.getTokenGet(getMobileUrl, mobileParam);
		String mobileResult = queryPost2.getString("result");
		if(mobileResult ==null||mobileResult.length()<10) {
			log.info("获取手机号失败！");
			return;
		}
		JSONObject mobileJson = JSONObject.parseObject(mobileResult);
		String message1 = mobileJson.getString("message");
		phoneNo = mobileJson.getString("mobile");
		String cardNumber = mobileJson.getString("1分钟内剩余取卡数:");
		if(message1 == null || !"ok".equalsIgnoreCase(message1) || phoneNo.length()!=11 || Integer.valueOf(cardNumber)<20) {return;}
		
		//phoneNo = "18653610713";
		
		char ssName = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
		char ssName2 = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
		char ssName3 = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
		log.info(String.valueOf(ssName)+String.valueOf(ssName2)+String.valueOf(ssName3));
		String firstName = "张";
		String lastName = "三";
		try {
			firstName = URLEncoder.encode(String.valueOf(ssName), "UTF-8");
			lastName = URLEncoder.encode(String.valueOf(ssName)+String.valueOf(ssName), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		//http://www.flycua.com/app/login/queryUserStatus?_=0.8810463728463174
		String checkUrl = "http://www.flycua.com/app/login/queryUserStatus";
		String checkParam = "_="+String.valueOf(Math.random()).substring(2, 15);
		JSONObject queryPost3 = GetPostTest3.getSessionGet(checkUrl, checkParam);
		String headers = queryPost3.getString("headers");
		String sessionStr = "";
		if (headers.length()>10 && headers.contains("set-cookie=[session=")) {
			int firstIndex = headers.indexOf("set-cookie=[session=");
			int secondIndex = headers.indexOf("; Path=/; Expires=");
			sessionStr = headers.substring(firstIndex+20, secondIndex);
		}
		if (sessionStr.length()<40) {
			log.info("获取官网session失败！");
			return;
		}
		
		//获取随机码
		getValiateCount = 0;
		String valiateUrl = "http://www.flycua.com/app/login/validate";
		String valiateParam = "_="+String.valueOf(Math.random()).substring(2, 15);
		String valiateCookie = "session="+sessionStr;
		JSONObject queryPost4 = GetPostTest3.getImageGet(valiateUrl, valiateParam, valiateCookie);
		String fileName = queryPost4.getString("fileName");
		log.info(fileName);
		
		//百度识别
		String fieFullName = "C:/img/"+fileName;
		String accurate = Accurate2.accurate(fieFullName);
		log.info(accurate);
		JSONObject accurateJson = new JSONObject();
		String validateCode = "";
		if (accurate.length()<7) {
			log.info("百度识别随机码失败！");
			return;
		}
		accurateJson = JSONObject.parseObject(accurate);
		String ss = accurateJson.getJSONArray("words_result").getJSONObject(0).getString("words");
		validateCode = ss.trim().replaceAll(" ", "");
		log.info("validateCode===="+validateCode);
		
		//获取手机验证码
		//http://www.flycua.com/app/shortMessage/sendMsg?_=1627131529922&phoneNumber=16742051417&verifi=f3posw
		String phoneUrl = "http://www.flycua.com/app/shortMessage/sendMsg";
		String phoneParam = "_="+String.valueOf(Math.random()).substring(2, 15)
				+ "&phoneNumber="+phoneNo
				+ "&verifi="+validateCode;
		String phoneCookie = "session="+sessionStr;
		JSONObject queryPost5 = GetPostTest3.sendMsg(phoneUrl, phoneParam, phoneCookie);
		String sendMsgResult = queryPost5.getString("result");
		JSONObject sendJson = JSONObject.parseObject(sendMsgResult);
		sendStatus = sendJson.getString("resultStatus");
		if (sendStatus == null || sendStatus.length()<1) {
			log.info("发送手机验证码失败！");
			return;
		}
		if("failed".equalsIgnoreCase(sendStatus)) {
			sendValiate(sessionStr, phoneNo);
		}
		if(!"success".equals(sendStatus)) {
			log.info("十次识别随机码都失败，程序结束");
			return;
		}
		
		//等待手机接收验证码
		try {
			log.info("等待20秒，等待手机接收到验证码！");
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String getMessageUrl2 = "http://api.do889.com:81/api/get_message";
		String messageParam2 = "token="+token+"&project_id="+projectId+"&phone_num="+phoneNo+"";
		JSONObject queryPost6 = GetPostTest3.getTokenGet(getMessageUrl2, messageParam2);
		String massageStr = queryPost6.getString("result");
		JSONObject messageJson = JSONObject.parseObject(massageStr);
		String massgeStatus = messageJson.getString("message");
		String massgeCode = "";
		if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
			log.info("获取手机验证码失败！");
			return;
		}
		massgeCode = messageJson.getString("code");
		if(massgeCode == null || massgeCode.length()<3) {return;}
		log.info("获取手机验证码成功："+massgeCode);

		
		//http://www.flycua.com/app/loginRegister/userRegAndAct?_=1627132645375&channel=B2C&email=&loginName=&mobile=18321342934&lastName=%E5%BC%A0&firstName=%E4%B8%89&password=ASDF1234&verifCode=435028
		//{"resultStatus":"failed","resultMessage":"该手机号(18321342934)已被注册过","result":""}
		String userRegAndActUrl = "http://www.flycua.com/app/loginRegister/userRegAndAct";
		String addParam = "_="+String.valueOf(Math.random()).substring(2, 15)
				+ "&channel=B2C"
				+ "&email="
				+ "&loginName="
				+ "&mobile="+phoneNo+""
				+ "&lastName="+lastName+""
				+ "&firstName="+firstName+""
				+ "&password=ASDF1234"
				+ "&verifCode="+massgeCode+""
				+ "";
		String addCookie = "session="+sessionStr;
		JSONObject queryPost7 = GetPostTest3.userRegAndAct(userRegAndActUrl, addParam, addCookie);
		String addResult = queryPost7.getString("result");
		if(addResult==null || addResult.length()<10) {return;}
		JSONObject addJson = JSONObject.parseObject(addResult);
		String addStatus = addJson.getString("resultStatus");
		if(!"success".equalsIgnoreCase(addStatus)) {
			log.info("账号注册失败！");
			return;
		}
		
		//记录官网账号
		JSONObject addKnJson = new JSONObject();
		addKnJson.put("accountNo", phoneNo);
		addKnJson.put("name", String.valueOf(ssName)+String.valueOf(ssName2)+String.valueOf(ssName3));
		addKnJson.put("accountPas", "ASDF1234");
		addKnJson.put("mobile", phoneNo);
		addKnJson.put("useTime", "");
		addKnJson.put("encryptStr", "");
		addKnJson.put("session", "");
		new SqlManager().insertKN(addKnJson);
		
		
		/*
		*/
	}
	
	private static int getValiateCount = 0;
	
	public static void sendValiate(String sessionStr, String phoneNo) {
		//获取随机码
		String valiateUrl = "http://www.flycua.com/app/login/validate";
		String valiateParam = "_="+String.valueOf(Math.random()).substring(2, 15);
		String valiateCookie = "session="+sessionStr;
		JSONObject queryPost4 = GetPostTest3.getImageGet(valiateUrl, valiateParam, valiateCookie);
		String fileName = queryPost4.getString("fileName");
		log.info(fileName);
		
		//百度识别
		String fieFullName = "C:/img/"+fileName;
		String accurate = Accurate2.accurate(fieFullName);
		log.info(accurate);
		JSONObject accurateJson = new JSONObject();
		String validateCode = "";
		if (accurate.length()<7) {
			log.info("百度识别随机码失败！");
			return;
		}
		accurateJson = JSONObject.parseObject(accurate);
		String ss = accurateJson.getJSONArray("words_result").getJSONObject(0).getString("words");
		validateCode = ss.trim().replaceAll(" ", "");
		log.info("validateCode===="+validateCode);
		
		//获取手机验证码
		//http://www.flycua.com/app/shortMessage/sendMsg?_=1627131529922&phoneNumber=16742051417&verifi=f3posw
		String phoneUrl = "http://www.flycua.com/app/shortMessage/sendMsg";
		String phoneParam = "_="+String.valueOf(Math.random()).substring(2, 15)
				+ "&phoneNumber="+phoneNo
				+ "&verifi="+validateCode;
		String phoneCookie = "session="+sessionStr;
		JSONObject queryPost5 = GetPostTest3.sendMsg(phoneUrl, phoneParam, phoneCookie);
		String sendMsgResult = queryPost5.getString("result");
		JSONObject sendJson = JSONObject.parseObject(sendMsgResult);
		sendStatus = sendJson.getString("resultStatus");
		if (sendStatus == null || sendStatus.length()<1) {
			log.info("发送手机验证码失败！");
			return;
		}
		if("failed".equalsIgnoreCase(sendStatus)) {
			if (getValiateCount>10) {
				log.info("获取随机码已经获取超过10次！");
				return;
			}
			//准备再来一次
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//开始再来一次
			sendValiate(sessionStr, phoneNo);
		}
	}
	
}
