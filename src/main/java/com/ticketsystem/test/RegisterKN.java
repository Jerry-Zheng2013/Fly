package com.ticketsystem.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.util.SqlManager;

public class RegisterKN {
	
	private String sendStatus = "";
	private String projectId = "84153";
	private String phonePas = "ASDF1234";
	private String token = "vmNn1KiIdhms9f5fSw7SFOlADOG+TggeT7rVuYvTTH4Yoij/X6JNNwjbt5gMY6/DBFtLsEoTaSfkXQ4tz71lzAdfckz77OU/x4LtXnl0x9nDPu0eyLUpnKgoF80wcuVkXj0mK/4lLFMFDcJfR/NmRXlrPjeb+KeFpZdu/Mf0z88=";
	private int getValiateCount = 0;
	
	public static void main(String[] args) {
		try {
			long hh = 24; //小时数
			long mm = 60; //分钟数
			long timerMillis = 1000*60*mm*hh;
			int registerCount = 0;
			RegisterKN registerkn = new RegisterKN();
			long startTimeMillis = System.currentTimeMillis();
			while(true) {
				try {
					long currTimeMillis = System.currentTimeMillis();
					if(currTimeMillis-startTimeMillis>timerMillis) {
						return;
					} else {
						//在规定时间内，开始注册
						registerkn.start();
					}
				}catch(Exception e2) {
					Date nowDate = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
					System.out.println("==========["+format.format(nowDate)+"]循环内，系统运行出错==========");
					e2.printStackTrace();
				} finally {
					registerCount++;
					Date nowDate = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
					System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
					System.out.println("！！！！！！！！！！["+format.format(nowDate)+"]程序循环了"+registerCount+"次！！！！！！！！！！");
					System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
				}
			}
		}catch(Exception e) {
			Date nowDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			System.out.println("==========["+format.format(nowDate)+"]系统运行出错==========");
			e.printStackTrace();
		}
	}
	
	public void start() {
		/*
		//获取接码平台的token
		String getToken = "http://api.do889.com:81/api/logins";
		String paramStr = "username=18568550369&password=741214ab";
		//JSONObject queryPost = RegisterGetPost.getTokenGet(getToken, paramStr);
		*/
		
		//获取手机号
		System.out.println("==========获取手机号==========");
		String getMobileUrl = "http://api.do889.com:81/api/get_mobile"; 
		String mobileParam = "token="+token+"&project_id="+this.projectId+"";
		//TODO 调用接口
		JSONObject mobileRes = RegisterGetPost.getMobile(getMobileUrl, mobileParam);
		String phoneNo = "";
		String mobileResult = mobileRes.getString("result");
		if(mobileResult ==null||mobileResult.length()<10) {
			System.out.println("获取手机号失败！");
			return;
		}
		JSONObject mobileJson = JSONObject.parseObject(mobileResult);
		String message1 = mobileJson.getString("message");
		phoneNo = mobileJson.getString("mobile");
		String cardNumber = mobileJson.getString("1分钟内剩余取卡数:");
		if(message1 == null || !"ok".equalsIgnoreCase(message1) || phoneNo.length()!=11 || Integer.valueOf(cardNumber)<20) {return;}
		System.out.println("==========获取手机号成功:"+phoneNo+"==========");
		
		
		//检查手机号是否已经注册
		System.out.println("==========检查手机号是否已经注册==========");
		SqlManager sqlManager = new SqlManager();
		JSONObject knAccountJson = sqlManager.getKnAccount(phoneNo);
		String accountNoRes = knAccountJson.getString("accountNo");
		if(accountNoRes!=null && accountNoRes.length()>3) {
			System.out.println("==========手机号已经注册过==========");
			return;
		}
		
		
		//获取客户名称
		System.out.println("==========获取客户名称==========");
		char ssName = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
		char ssName2 = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
		char ssName3 = (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
		System.out.println(String.valueOf(ssName)+String.valueOf(ssName2)+String.valueOf(ssName3));
		String firstName = "张";
		String lastName = "三";
		try {
			firstName = URLEncoder.encode(String.valueOf(ssName), "UTF-8");
			lastName = URLEncoder.encode(String.valueOf(ssName)+String.valueOf(ssName), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println("==========获取客户名称成功:"+String.valueOf(ssName)+String.valueOf(ssName2)+String.valueOf(ssName3)+"==========");
		
		
		String sessionStr = "";
		try {
			//获取官网session
			System.out.println("==========获取官网session==========");
			String checkUrl = "http://www.flycua.com/app/login/queryUserStatus";
			String checkParam = "_="+String.valueOf(Math.random()).substring(2, 15);
			//TODO 调用接口
			JSONObject queryPost3 = RegisterGetPost.getSessionGet(checkUrl, checkParam);
			String headers = queryPost3.getString("headers");
			if (headers.length()>10 && headers.contains("set-cookie=[session=")) {
				int firstIndex = headers.indexOf("set-cookie=[session=");
				int secondIndex = headers.indexOf("; Path=/; Expires=");
				sessionStr = headers.substring(firstIndex+20, secondIndex);
			}
			if (sessionStr.length()<40) {
				System.out.println("获取官网session失败！");
				return;
			}
			System.out.println("==========获取官网session成功:"+sessionStr+"==========");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		//获取随机码
		System.out.println("==========获取随机码==========");
		this.getValiateCount = 1;
		String valiateUrl = "http://www.flycua.com/app/login/validate";
		String valiateParam = "_="+String.valueOf(Math.random()).substring(2, 15);
		String valiateCookie = "session="+sessionStr;
		//TODO 调用接口
		JSONObject queryPost4 = RegisterGetPost.getImageGet(valiateUrl, valiateParam, valiateCookie);
		String fileName = queryPost4.getString("fileName");
		System.out.println("==========获取随机码成功:"+fileName+"==========");
		
		
		//百度识别
		System.out.println("==========百度识别==========");
		String fieFullName = "d:/img/"+fileName;
		//TODO 调用识别
		String accurate = Accurate2.accurate(fieFullName);
		System.out.println(accurate);
		JSONObject accurateJson = new JSONObject();
		String validateCode = "";
		if (accurate.length()<7) {
			System.out.println("百度识别随机码失败！");
			return;
		}
		accurateJson = JSONObject.parseObject(accurate);
		JSONArray accArr = accurateJson.getJSONArray("words_result");
		if(accArr.size()<1) {
			System.out.println("百度识别随机码失败！");
			return;
		}
		String ss = accArr.getJSONObject(0).getString("words");
		validateCode = ss.trim().replaceAll(" ", "");
		System.out.println("==========百度识别成功:"+validateCode+"==========");
		
		
		//发送手机验证码
		System.out.println("==========发送手机验证码==========");
		String phoneUrl = "http://www.flycua.com/app/shortMessage/sendMsg";
		String phoneParam = "_="+String.valueOf(Math.random()).substring(2, 15)
				+ "&phoneNumber="+phoneNo
				+ "&verifi="+validateCode;
		String phoneCookie = "session="+sessionStr;
		//TODO 调用接口
		JSONObject queryPost5 = RegisterGetPost.sendMsg(phoneUrl, phoneParam, phoneCookie);
		String sendMsgResult = queryPost5.getString("result");
		JSONObject sendJson = JSONObject.parseObject(sendMsgResult);
		this.sendStatus = sendJson.getString("resultStatus");
		if (this.sendStatus == null || this.sendStatus.length()<1) {
			System.out.println("发送手机验证码失败！");
			return;
		}
		if("failed".equalsIgnoreCase(this.sendStatus)) {
			String resultMessage = sendJson.getString("resultMessage");
			System.out.println("==========失败原因:"+resultMessage+"==========");
			//发送手机验证码失败后，可能是因为百度识别错误，重新调用获取随机码，百度识别，再次发送手机验证码校验
			sendValiate(sessionStr, phoneNo);
		}
		if(!"success".equals(this.sendStatus)) {
			System.out.println("十次识别随机码都失败，程序结束");
			return;
		}
		System.out.println("==========发送手机验证码成功！==========");
		
		
		//等待手机接收验证码
		try {
			System.out.println("等待20秒，等待手机接收到验证码！");
			int sleepTime = 0;
			while(true) {
				if(sleepTime==20) {break;}
				System.out.println(sleepTime+"s");
				Thread.sleep(1000);
				sleepTime++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//获取手机验证码
		System.out.println("==========获取手机验证码==========");
		String getMessageUrl2 = "http://api.do889.com:81/api/get_message";
		String messageParam2 = "token="+token+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
		//TODO 调用接口
		JSONObject queryPost6 = RegisterGetPost.getTokenGet(getMessageUrl2, messageParam2);
		String massageStr = queryPost6.getString("result");
		if(massageStr!=null && massageStr.contains("维护")) {
			System.out.println("==========系统维护，等待一个小时==========");
			try {
				Thread.sleep(1000*60*60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		JSONObject messageJson = JSONObject.parseObject(massageStr);
		String massgeStatus = messageJson.getString("message");
		String massgeCode = "";
		if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
			System.out.println("获取手机验证码失败！");
			if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
				System.out.println("短信还未到达,第二次等待20秒，继续获取！");
				//等待手机接收验证码
				try {
					System.out.println("第二次等待20秒，等待手机接收到验证码！");
					int sleepTime = 0;
					while(true) {
						if(sleepTime==20) {break;}
						System.out.println(sleepTime+"s");
						Thread.sleep(1000);
						sleepTime++;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("==========获取手机验证码==========");
				String getMessageUrl22 = "http://api.do889.com:81/api/get_message";
				String messageParam22 = "token="+token+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
				//TODO 调用接口
				JSONObject queryPost66 = RegisterGetPost.getTokenGet(getMessageUrl22, messageParam22);
				String massageStr22 = queryPost66.getString("result");
				messageJson = JSONObject.parseObject(massageStr22);
				massgeStatus = messageJson.getString("message");
				massgeCode = "";
				if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
					System.out.println("获取手机验证码失败！");
					if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
						System.out.println("短信还未到达,第三次等待20秒，继续获取！");
						//等待手机接收验证码
						try {
							System.out.println("第三次等待20秒，等待手机接收到验证码！");
							int sleepTime = 0;
							while(true) {
								if(sleepTime==20) {break;}
								System.out.println(sleepTime+"s");
								Thread.sleep(1000);
								sleepTime++;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("==========获取手机验证码==========");
						String getMessageUrl222 = "http://api.do889.com:81/api/get_message";
						String messageParam222 = "token="+token+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
						//TODO 调用接口
						JSONObject queryPost666 = RegisterGetPost.getTokenGet(getMessageUrl222, messageParam222);
						String massageStr222 = queryPost666.getString("result");
						messageJson = JSONObject.parseObject(massageStr222);
						massgeStatus = messageJson.getString("message");
						massgeCode = "";
						if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
							System.out.println("获取手机验证码失败！");
							return;
						}
					}
					return;
				}
				massgeCode = messageJson.getString("code");
				if(massgeCode == null || massgeCode.length()<3) {
					System.out.println("获取手机验证码失败！");
					return;
				}
			}
			return;
		}
		massgeCode = messageJson.getString("code");
		if(massgeCode == null || massgeCode.length()<3) {
			System.out.println("获取手机验证码失败！");
			return;
		}
		System.out.println("==========获取手机验证码:"+massgeCode+"==========");

		
		//调用官网注册
		System.out.println("==========调用官网注册==========");
		String userRegAndActUrl = "http://www.flycua.com/app/loginRegister/userRegAndAct";
		String addParam = "_="+String.valueOf(Math.random()).substring(2, 15)
				+ "&channel=B2C"
				+ "&email="
				+ "&loginName="
				+ "&mobile="+phoneNo+""
				+ "&lastName="+lastName+""
				+ "&firstName="+firstName+""
				+ "&password="+phonePas+""
				+ "&verifCode="+massgeCode+""
				+ "";
		String addCookie = "session="+sessionStr;
		//TODO 调用接口
		JSONObject queryPost7 = RegisterGetPost.userRegAndAct(userRegAndActUrl, addParam, addCookie);
		String addResult = queryPost7.getString("result");
		if(addResult==null || addResult.length()<10) {return;}
		JSONObject addJson = JSONObject.parseObject(addResult);
		String addStatus = addJson.getString("resultStatus");
		if(!"success".equalsIgnoreCase(addStatus)) {
			System.out.println("账号注册失败！");
			return;
		}
		System.out.println("==========调用官网注册成功！==========");
		
		
		//记录官网账号
		System.out.println("==========记录官网账号==========");
		JSONObject addKnJson = new JSONObject();
		addKnJson.put("accountNo", phoneNo);
		addKnJson.put("name", String.valueOf(ssName)+String.valueOf(ssName2)+String.valueOf(ssName3));
		addKnJson.put("password", phonePas);
		addKnJson.put("mobile", phoneNo);
		addKnJson.put("useTime", "");
		addKnJson.put("encryptStr", "");
		addKnJson.put("session", "");
		sqlManager.insertKN(addKnJson);
		System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		System.out.println("！！！！！！！！！！记录官网账号成功！！！！！！！！！！");
		System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
	}
	
	
	public void sendValiate(String sessionStr, String phoneNo) {
		//获取随机码
		System.out.println("==========获取随机码==========");
		this.getValiateCount++;
		String valiateUrl = "http://www.flycua.com/app/login/validate";
		String valiateParam = "_="+String.valueOf(Math.random()).substring(2, 15);
		String valiateCookie = "session="+sessionStr;
		JSONObject queryPost4 = RegisterGetPost.getImageGet(valiateUrl, valiateParam, valiateCookie);
		String fileName = queryPost4.getString("fileName");
		System.out.println("==========获取随机码成功:"+fileName+"==========");
		
		//百度识别
		System.out.println("==========百度识别==========");
		String fieFullName = "d:/img/"+fileName;
		String accurate = Accurate2.accurate(fieFullName);
		System.out.println(accurate);
		JSONObject accurateJson = new JSONObject();
		String validateCode = "";
		if (accurate.length()<7) {
			System.out.println("百度识别随机码失败！");
			return;
		}
		accurateJson = JSONObject.parseObject(accurate);
		JSONArray accArr = accurateJson.getJSONArray("words_result");
		if(accArr.size()<1) {
			System.out.println("百度识别随机码失败！");
			return;
		}
		String ss = accArr.getJSONObject(0).getString("words");
		validateCode = ss.trim().replaceAll(" ", "");
		System.out.println("==========百度识别成功:"+validateCode+"==========");
		
		//获取手机验证码
		System.out.println("==========获取手机验证码==========");
		String phoneUrl = "http://www.flycua.com/app/shortMessage/sendMsg";
		String phoneParam = "_="+String.valueOf(Math.random()).substring(2, 15)
				+ "&phoneNumber="+phoneNo
				+ "&verifi="+validateCode;
		String phoneCookie = "session="+sessionStr;
		JSONObject queryPost5 = RegisterGetPost.sendMsg(phoneUrl, phoneParam, phoneCookie);
		String sendMsgResult = queryPost5.getString("result");
		JSONObject sendJson = JSONObject.parseObject(sendMsgResult);
		this.sendStatus = sendJson.getString("resultStatus");
		if (this.sendStatus == null || this.sendStatus.length()<1) {
			System.out.println("发送手机验证码失败！");
			return;
		}
		if("failed".equalsIgnoreCase(this.sendStatus)) {
			String resultMessage = sendJson.getString("resultMessage");
			System.out.println("==========失败原因:"+resultMessage+"==========");
			if(resultMessage.contains("验证码发送过于频繁")) {
				System.out.println("==========验证码发送过于频繁==========");
				this.getValiateCount=11;
			}
			if (this.getValiateCount>10) {
				System.out.println("获取随机码已经获取超过10次！");
				return;
			}
			//准备再来一次
			try {
				System.out.println("==========第"+this.getValiateCount+"次获取验证码==========");
				System.out.println("循环获取随机码失败，等待20秒，再次获取随机码！");
				int sleepTime = 0;
				while(true) {
					if(sleepTime==20) {break;}
					System.out.println(sleepTime+"s");
					Thread.sleep(1000);
					sleepTime++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//开始再来一次
			sendValiate(sessionStr, phoneNo);
		}
	}
	
}
