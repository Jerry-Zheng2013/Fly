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
	private String operator = "4";
	private String accountPas = "ASDF1234";
	private String jieMaToken = "vmNn1KiIdhms9f5fSw7SFOlADOG+TggeT7rVuYvTTH4Yoij/X6JNNwjbt5gMY6/DBFtLsEoTaSfkXQ4tz71lzAdfckz77OU/x4LtXnl0x9nDPu0eyLUpnKgoF80wcuVkXj0mK/4lLFMFDcJfR/NmRXlrPjeb+KeFpZdu/Mf0z88=";
	private int getValiateCount = 0;
	public static StringBuffer sb = new StringBuffer();
	
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
		String mobileParam = "token="+jieMaToken+"&project_id="+this.projectId+"&operator="+this.operator+"";
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
		String ssName = getHanZi();
		String ssName2 = getHanZi();
		String ssName3 = getHanZi();
		System.out.println(ssName+ssName2+ssName3);
		String firstName = "张";
		String lastName = "三";
		try {
			firstName = URLEncoder.encode(ssName, "UTF-8");
			lastName = URLEncoder.encode(ssName2+ssName3, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println("==========获取客户名称成功:"+ssName+ssName2+ssName3+"==========");
		
		
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
		String messageParam2 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
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
				System.out.println("短信还未到达,第二次等待10秒，继续获取！");
				//等待手机接收验证码
				try {
					System.out.println("第二次等待10秒，等待手机接收到验证码！");
					int sleepTime = 0;
					while(true) {
						if(sleepTime==10) {break;}
						System.out.println(sleepTime+"s");
						Thread.sleep(1000);
						sleepTime++;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("==========获取手机验证码["+phoneNo+"]==========");
				String getMessageUrl22 = "http://api.do889.com:81/api/get_message";
				String messageParam22 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
				//TODO 调用接口
				JSONObject queryPost66 = RegisterGetPost.getTokenGet(getMessageUrl22, messageParam22);
				String massageStr22 = queryPost66.getString("result");
				messageJson = JSONObject.parseObject(massageStr22);
				massgeStatus = messageJson.getString("message");
				massgeCode = "";
				if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
					System.out.println("获取手机验证码失败！");
					if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
						System.out.println("短信还未到达,第三次等待10秒，继续获取！");
						//等待手机接收验证码
						try {
							System.out.println("第三次等待10秒，等待手机接收到验证码！");
							int sleepTime = 0;
							while(true) {
								if(sleepTime==10) {break;}
								System.out.println(sleepTime+"s");
								Thread.sleep(1000);
								sleepTime++;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("==========获取手机验证码["+phoneNo+"]==========");
						String getMessageUrl222 = "http://api.do889.com:81/api/get_message";
						String messageParam222 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
						//TODO 调用接口
						JSONObject queryPost222 = RegisterGetPost.getTokenGet(getMessageUrl222, messageParam222);
						String massageStr222 = queryPost222.getString("result");
						messageJson = JSONObject.parseObject(massageStr222);
						massgeStatus = messageJson.getString("message");
						massgeCode = "";
						if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
							System.out.println("获取手机验证码失败！");
							if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
								System.out.println("短信还未到达,第四次等待10秒，继续获取！");
								//等待手机接收验证码
								try {
									System.out.println("第四次等待10秒，等待手机接收到验证码！");
									int sleepTime = 0;
									while(true) {
										if(sleepTime==10) {break;}
										System.out.println(sleepTime+"s");
										Thread.sleep(1000);
										sleepTime++;
									}
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								System.out.println("==========获取手机验证码["+phoneNo+"]==========");
								String getMessageUrl2222 = "http://api.do889.com:81/api/get_message";
								String messageParam2222 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
								//TODO 调用接口
								JSONObject queryPost2222 = RegisterGetPost.getTokenGet(getMessageUrl2222, messageParam2222);
								String massageStr2222 = queryPost2222.getString("result");
								messageJson = JSONObject.parseObject(massageStr2222);
								massgeStatus = messageJson.getString("message");
								massgeCode = "";
								if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
									System.out.println("获取手机验证码失败！");
									if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
										System.out.println("短信还未到达,第五次等待10秒，继续获取！");
										//等待手机接收验证码
										try {
											System.out.println("第五次等待10秒，等待手机接收到验证码！");
											int sleepTime = 0;
											while(true) {
												if(sleepTime==10) {break;}
												System.out.println(sleepTime+"s");
												Thread.sleep(1000);
												sleepTime++;
											}
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										System.out.println("==========获取手机验证码["+phoneNo+"]==========");
										String getMessageUrl22222 = "http://api.do889.com:81/api/get_message";
										String messageParam22222 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
										//TODO 调用接口
										JSONObject queryPost22222 = RegisterGetPost.getTokenGet(getMessageUrl22222, messageParam22222);
										String massageStr22222 = queryPost22222.getString("result");
										messageJson = JSONObject.parseObject(massageStr22222);
										massgeStatus = messageJson.getString("message");
										massgeCode = "";
										if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
											System.out.println("获取手机验证码失败！");
											if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
												System.out.println("短信还未到达,第六次等待10秒，继续获取！");
												//等待手机接收验证码
												try {
													System.out.println("第六次等待10秒，等待手机接收到验证码！");
													int sleepTime = 0;
													while(true) {
														if(sleepTime==10) {break;}
														System.out.println(sleepTime+"s");
														Thread.sleep(1000);
														sleepTime++;
													}
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												System.out.println("==========获取手机验证码["+phoneNo+"]==========");
												String getMessageUrl222222 = "http://api.do889.com:81/api/get_message";
												String messageParam222222 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
												//TODO 调用接口
												JSONObject queryPost222222 = RegisterGetPost.getTokenGet(getMessageUrl222222, messageParam222222);
												String massageStr222222 = queryPost222222.getString("result");
												messageJson = JSONObject.parseObject(massageStr222222);
												massgeStatus = messageJson.getString("message");
												massgeCode = "";
												if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
													System.out.println("获取手机验证码失败！");
													if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
														System.out.println("短信还未到达,第七次等待10秒，继续获取！");
														//等待手机接收验证码
														try {
															System.out.println("第七次等待10秒，等待手机接收到验证码！");
															int sleepTime = 0;
															while(true) {
																if(sleepTime==10) {break;}
																System.out.println(sleepTime+"s");
																Thread.sleep(1000);
																sleepTime++;
															}
														} catch (InterruptedException e) {
															e.printStackTrace();
														}
														System.out.println("==========获取手机验证码["+phoneNo+"]==========");
														String getMessageUrl61 = "http://api.do889.com:81/api/get_message";
														String messageParam61 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
														//TODO 调用接口
														JSONObject queryPost61 = RegisterGetPost.getTokenGet(getMessageUrl61, messageParam61);
														String massageStr61 = queryPost61.getString("result");
														messageJson = JSONObject.parseObject(massageStr61);
														massgeStatus = messageJson.getString("message");
														massgeCode = "";
														if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
															System.out.println("获取手机验证码失败！");
															if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
																System.out.println("短信还未到达,第八次等待10秒，继续获取！");
																//等待手机接收验证码
																try {
																	System.out.println("第八次等待10秒，等待手机接收到验证码！");
																	int sleepTime = 0;
																	while(true) {
																		if(sleepTime==10) {break;}
																		System.out.println(sleepTime+"s");
																		Thread.sleep(1000);
																		sleepTime++;
																	}
																} catch (InterruptedException e) {
																	e.printStackTrace();
																}
																System.out.println("==========获取手机验证码["+phoneNo+"]==========");
																String getMessageUrl62 = "http://api.do889.com:81/api/get_message";
																String messageParam62 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
																//TODO 调用接口
																JSONObject queryPost62 = RegisterGetPost.getTokenGet(getMessageUrl62, messageParam62);
																String massageStr62 = queryPost62.getString("result");
																messageJson = JSONObject.parseObject(massageStr62);
																massgeStatus = messageJson.getString("message");
																massgeCode = "";
																if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
																	System.out.println("获取手机验证码失败！");
																	if("短信还未到达,请继续获取".equalsIgnoreCase(massgeStatus)) {
																		System.out.println("短信还未到达,第九次等待10秒，继续获取！");
																		//等待手机接收验证码
																		try {
																			System.out.println("第九次等待10秒，等待手机接收到验证码！");
																			int sleepTime = 0;
																			while(true) {
																				if(sleepTime==10) {break;}
																				System.out.println(sleepTime+"s");
																				Thread.sleep(1000);
																				sleepTime++;
																			}
																		} catch (InterruptedException e) {
																			e.printStackTrace();
																		}
																		System.out.println("==========获取手机验证码["+phoneNo+"]==========");
																		String getMessageUrl63 = "http://api.do889.com:81/api/get_message";
																		String messageParam63 = "token="+jieMaToken+"&project_id="+this.projectId+"&phone_num="+phoneNo+"";
																		//TODO 调用接口
																		JSONObject queryPost63 = RegisterGetPost.getTokenGet(getMessageUrl63, messageParam63);
																		String massageStr63 = queryPost63.getString("result");
																		messageJson = JSONObject.parseObject(massageStr63);
																		massgeStatus = messageJson.getString("message");
																		massgeCode = "";
																		if (massgeStatus == null || massgeStatus.length()<2||!"ok".equalsIgnoreCase(massgeStatus)) {
																			System.out.println("获取手机验证码失败！");
																			return;
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
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
				+ "&password="+accountPas+""
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
		addKnJson.put("accountPas", accountPas);
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
		String error_msg = accurateJson.getString("error_msg");
		if(error_msg!=null && error_msg.contains("limit reached")) {
			
		}
		String validateCode = "";
		if (validateCode==null || accurate.length()<7) {
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
				if (this.getValiateCount>4) {
					this.getValiateCount=11;
				}
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
	
	public String getHanZi() {
		String hanzi = "张";//保存结果
		try {
			int starIndex = 0;
			int endIndex = 3754;
			int x=starIndex+(int)(Math.random()*endIndex);
			hanzi = String.valueOf(sb.charAt(x));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hanzi;
	}
	static {
		sb.append("啊阿埃挨哎唉哀皑癌蔼矮艾碍爱隘鞍氨安俺按暗岸胺案肮昂盎凹敖熬翱袄傲奥懊澳芭捌扒叭吧笆八疤巴拔跋靶把耙坝霸罢爸白柏百摆佰败拜稗斑班搬扳般颁板版扮拌伴瓣半办绊邦帮梆榜膀绑棒磅蚌镑傍谤苞胞包褒剥薄雹保堡饱");
		sb.append("宝抱报暴豹鲍爆杯碑悲卑北辈背贝钡倍狈备惫焙被奔苯本笨崩绷甭泵蹦迸逼鼻比鄙笔彼碧蓖蔽毕毙毖币庇痹闭敝弊必辟壁臂避陛鞭边编贬扁便变卞辨辩辫遍标彪膘表鳖憋别瘪彬斌濒滨宾摈兵冰柄丙秉饼炳病并玻菠播拨钵波博勃");
		sb.append("搏铂箔伯帛舶脖膊渤泊驳捕卜哺补埠不布步簿部怖擦猜裁材才财睬踩采彩菜蔡餐参蚕残惭惨灿苍舱仓沧藏操糙槽曹草厕策侧册测层蹭插叉茬茶查碴搽察岔差诧拆柴豺搀掺蝉馋谗缠铲产阐颤昌猖场尝常长偿肠厂敞畅唱倡超抄钞朝");
		sb.append("嘲潮巢吵炒车扯撤掣彻澈郴臣辰尘晨忱沉陈趁衬撑称城橙成呈乘程惩澄诚承逞骋秤吃痴持匙池迟弛驰耻齿侈尺赤翅斥炽充冲虫崇宠抽酬畴踌稠愁筹仇绸瞅丑臭初出橱厨躇锄雏滁除楚础储矗搐触处揣川穿椽传船喘串疮窗幢床闯创");
		sb.append("吹炊捶锤垂春椿醇唇淳纯蠢戳绰疵茨磁雌辞慈瓷词此刺赐次聪葱囱匆从丛凑粗醋簇促蹿篡窜摧崔催脆瘁粹淬翠村存寸磋撮搓措挫错搭达答瘩打大呆歹傣戴带殆代贷袋待逮怠耽担丹单郸掸胆旦氮但惮淡诞弹蛋当挡党荡档刀捣蹈倒");
		sb.append("岛祷导到稻悼道盗德得的蹬灯登等瞪凳邓堤低滴迪敌笛狄涤翟嫡抵底地蒂第帝弟递缔颠掂滇碘点典靛垫电佃甸店惦奠淀殿碉叼雕凋刁掉吊钓调跌爹碟蝶迭谍叠丁盯叮钉顶鼎锭定订丢东冬董懂动栋侗恫冻洞兜抖斗陡豆逗痘都督毒");
		sb.append("犊独读堵睹赌杜镀肚度渡妒端短锻段断缎堆兑队对墩吨蹲敦顿囤钝盾遁掇哆多夺垛躲朵跺舵剁惰堕蛾峨鹅俄额讹娥恶厄扼遏鄂饿恩而儿耳尔饵洱二贰发罚筏伐乏阀法珐藩帆番翻樊矾钒繁凡烦反返范贩犯饭泛坊芳方肪房防妨仿访");
		sb.append("纺放菲非啡飞肥匪诽吠肺废沸费芬酚吩氛分纷坟焚汾粉奋份忿愤粪丰封枫蜂峰锋风疯烽逢冯缝讽奉凤佛否夫敷肤孵扶拂辐幅氟符伏俘服浮涪福袱弗甫抚辅俯釜斧脯腑府腐赴副覆赋复傅付阜父腹负富讣附妇缚咐噶嘎该改概钙盖溉");
		sb.append("干甘杆柑竿肝赶感秆敢赣冈刚钢缸肛纲岗港杠篙皋高膏羔糕搞镐稿告哥歌搁戈鸽胳疙割革葛格蛤阁隔铬个各给根跟耕更庚羹埂耿梗工攻功恭龚供躬公宫弓巩汞拱贡共钩勾沟苟狗垢构购够辜菇咕箍估沽孤姑鼓古蛊骨谷股故顾固雇");
		sb.append("刮瓜剐寡挂褂乖拐怪棺关官冠观管馆罐惯灌贯光广逛瑰规圭硅归龟闺轨鬼诡癸桂柜跪贵刽辊滚棍锅郭国果裹过哈骸孩海氦亥害骇酣憨邯韩含涵寒函喊罕翰撼捍旱憾悍焊汗汉夯杭航壕嚎豪毫郝好耗号浩呵喝荷菏核禾和何合盒貉阂");
		sb.append("河涸赫褐鹤贺嘿黑痕很狠恨哼亨横衡恒轰哄烘虹鸿洪宏弘红喉侯猴吼厚候后呼乎忽瑚壶葫胡蝴狐糊湖弧虎唬护互沪户花哗华猾滑画划化话槐徊怀淮坏欢环桓还缓换患唤痪豢焕涣宦幻荒慌黄磺蝗簧皇凰惶煌晃幌恍谎灰挥辉徽恢蛔");
		sb.append("回毁悔慧卉惠晦贿秽会烩汇讳诲绘荤昏婚魂浑混豁活伙火获或惑霍货祸击圾基机畸稽积箕肌饥迹激讥鸡姬绩缉吉极棘辑籍集及急疾汲即嫉级挤几脊己蓟技冀季伎祭剂悸济寄寂计记既忌际妓继纪嘉枷夹佳家加荚颊贾甲钾假稼价架");
		sb.append("驾嫁歼监坚尖笺间煎兼肩艰奸缄茧检柬碱硷拣捡简俭剪减荐槛鉴践贱见键箭件健舰剑饯渐溅涧建僵姜将浆江疆蒋桨奖讲匠酱降蕉椒礁焦胶交郊浇骄娇嚼搅铰矫侥脚狡角饺缴绞剿教酵轿较叫窖揭接皆秸街阶截劫节桔杰捷睫竭洁结");
		sb.append("解姐戒藉芥界借介疥诫届巾筋斤金今津襟紧锦仅谨进靳晋禁近烬浸尽劲荆兢茎睛晶鲸京惊精粳经井警景颈静境敬镜径痉靖竟竞净炯窘揪究纠玖韭久灸九酒厩救旧臼舅咎就疚鞠拘狙疽居驹菊局咀矩举沮聚拒据巨具距踞锯俱句惧炬");
		sb.append("剧捐鹃娟倦眷卷绢撅攫抉掘倔爵觉决诀绝均菌钧军君峻俊竣浚郡骏喀咖卡咯开揩楷凯慨刊堪勘坎砍看康慷糠扛抗亢炕考拷烤靠坷苛柯棵磕颗科壳咳可渴克刻客课肯啃垦恳坑吭空恐孔控抠口扣寇枯哭窟苦酷库裤夸垮挎跨胯块筷侩");
		sb.append("快宽款匡筐狂框矿眶旷况亏盔岿窥葵奎魁傀馈愧溃坤昆捆困括扩廓阔垃拉喇蜡腊辣啦莱来赖蓝婪栏拦篮阑兰澜谰揽览懒缆烂滥琅榔狼廊郎朗浪捞劳牢老佬姥酪烙涝勒乐雷镭蕾磊累儡垒擂肋类泪棱楞冷厘梨犁黎篱狸离漓理李里鲤");
		sb.append("礼莉荔吏栗丽厉励砾历利傈例俐痢立粒沥隶力璃哩俩联莲连镰廉怜涟帘敛脸链恋炼练粮凉梁粱良两辆量晾亮谅撩聊僚疗燎寥辽潦了撂镣廖料列裂烈劣猎琳林磷霖临邻鳞淋凛赁吝拎玲菱零龄铃伶羚凌灵陵岭领另令溜琉榴硫馏留刘");
		sb.append("瘤流柳六龙聋咙笼窿隆垄拢陇楼娄搂篓漏陋芦卢颅庐炉掳卤虏鲁麓碌露路赂鹿潞禄录陆戮驴吕铝侣旅履屡缕虑氯律率滤绿峦挛孪滦卵乱掠略抡轮伦仑沦纶论萝螺罗逻锣箩骡裸落洛骆络妈麻玛码蚂马骂嘛吗埋买麦卖迈脉瞒馒蛮满");
		sb.append("蔓曼慢漫谩芒茫盲氓忙莽猫茅锚毛矛铆卯茂冒帽貌贸么玫枚梅酶霉煤没眉媒镁每美昧寐妹媚门闷们萌蒙檬盟锰猛梦孟眯醚靡糜迷谜弥米秘觅泌蜜密幂棉眠绵冕免勉娩缅面苗描瞄藐秒渺庙妙蔑灭民抿皿敏悯闽明螟鸣铭名命谬摸摹");
		sb.append("蘑模膜磨摩魔抹末莫墨默沫漠寞陌谋牟某拇牡亩姆母墓暮幕募慕木目睦牧穆拿哪呐钠那娜纳氖乃奶耐奈南男难囊挠脑恼闹淖呢馁内嫩能妮霓倪泥尼拟你匿腻逆溺蔫拈年碾撵捻念娘酿鸟尿捏聂孽啮镊镍涅您柠狞凝宁拧泞牛扭钮纽");
		sb.append("脓浓农弄奴努怒女暖虐疟挪懦糯诺哦欧鸥殴藕呕偶沤啪趴爬帕怕琶拍排牌徘湃派攀潘盘磐盼畔判叛乓庞旁耪胖抛咆刨炮袍跑泡呸胚培裴赔陪配佩沛喷盆砰抨烹澎彭蓬棚硼篷膨朋鹏捧碰坯砒霹批披劈琵毗啤脾疲皮匹痞僻屁譬篇偏");
		sb.append("片骗飘漂瓢票撇瞥拼频贫品聘乒坪苹萍平凭瓶评屏坡泼颇婆破魄迫粕剖扑铺仆莆葡菩蒲埔朴圃普浦谱曝瀑期欺栖戚妻七凄漆柒沏其棋奇歧畦崎脐齐旗祈祁骑起岂乞企启契砌器气迄弃汽泣讫掐恰洽牵扦钎铅千迁签仟谦乾黔钱钳前");
		sb.append("潜遣浅谴堑嵌欠歉枪呛腔羌墙蔷强抢橇锹敲悄桥瞧乔侨巧鞘撬翘峭俏窍切茄且怯窃钦侵亲秦琴勤芹擒禽寝沁青轻氢倾卿清擎晴氰情顷请庆琼穷秋丘邱球求囚酋泅趋区蛆曲躯屈驱渠取娶龋趣去圈颧权醛泉全痊拳犬券劝缺炔瘸却鹊");
		sb.append("榷确雀裙群然燃冉染瓤壤攘嚷让饶扰绕惹热壬仁人忍韧任认刃妊纫扔仍日戎茸蓉荣融熔溶容绒冗揉柔肉茹蠕儒孺如辱乳汝入褥软阮蕊瑞锐闰润若弱撒洒萨腮鳃塞赛三叁伞散桑嗓丧搔骚扫嫂瑟色涩森僧莎砂杀刹沙纱傻啥煞筛晒珊");
		sb.append("苫杉山删煽衫闪陕擅赡膳善汕扇缮墒伤商赏晌上尚裳梢捎稍烧芍勺韶少哨邵绍奢赊蛇舌舍赦摄射慑涉社设砷申呻伸身深娠绅神沈审婶甚肾慎渗声生甥牲升绳省盛剩胜圣师失狮施湿诗尸虱十石拾时什食蚀实识史矢使屎驶始式示士");
		sb.append("世柿事拭誓逝势是嗜噬适仕侍释饰氏市恃室视试收手首守寿授售受瘦兽蔬枢梳殊抒输叔舒淑疏书赎孰熟薯暑曙署蜀黍鼠属术述树束戍竖墅庶数漱恕刷耍摔衰甩帅栓拴霜双爽谁水睡税吮瞬顺舜说硕朔烁斯撕嘶思私司丝死肆寺嗣四");
		sb.append("伺似饲巳松耸怂颂送宋讼诵搜艘擞嗽苏酥俗素速粟僳塑溯宿诉肃酸蒜算虽隋随绥髓碎岁穗遂隧祟孙损笋蓑梭唆缩琐索锁所塌他它她塔獭挞蹋踏胎苔抬台泰酞太态汰坍摊贪瘫滩坛檀痰潭谭谈坦毯袒碳探叹炭汤塘搪堂棠膛唐糖倘躺");
		sb.append("淌趟烫掏涛滔绦萄桃逃淘陶讨套特藤腾疼誊梯剔踢锑提题蹄啼体替嚏惕涕剃屉天添填田甜恬舔腆挑条迢眺跳贴铁帖厅听烃汀廷停亭庭挺艇通桐酮瞳同铜彤童桶捅筒统痛偷投头透凸秃突图徒途涂屠土吐兔湍团推颓腿蜕褪退吞屯臀");
		sb.append("拖托脱鸵陀驮驼椭妥拓唾挖哇蛙洼娃瓦袜歪外豌弯湾玩顽丸烷完碗挽晚皖惋宛婉万腕汪王亡枉网往旺望忘妄威巍微危韦违桅围唯惟为潍维苇萎委伟伪尾纬未蔚味畏胃喂魏位渭谓尉慰卫瘟温蚊文闻纹吻稳紊问嗡翁瓮挝蜗涡窝我斡");
		sb.append("卧握沃巫呜钨乌污诬屋无芜梧吾吴毋武五捂午舞伍侮坞戊雾晤物勿务悟误昔熙析西硒矽晰嘻吸锡牺稀息希悉膝夕惜熄烯溪汐犀檄袭席习媳喜铣洗系隙戏细瞎虾匣霞辖暇峡侠狭下厦夏吓掀锨先仙鲜纤咸贤衔舷闲涎弦嫌显险现献县");
		sb.append("腺馅羡宪陷限线相厢镶香箱襄湘乡翔祥详想响享项巷橡像向象萧硝霄削哮嚣销消宵淆晓小孝校肖啸笑效楔些歇蝎鞋协挟携邪斜胁谐写械卸蟹懈泄泻谢屑薪芯锌欣辛新忻心信衅星腥猩惺兴刑型形邢行醒幸杏性姓兄凶胸匈汹雄熊休");
		sb.append("修羞朽嗅锈秀袖绣墟戌需虚嘘须徐许蓄酗叙旭序畜恤絮婿绪续轩喧宣悬旋玄选癣眩绚靴薛学穴雪血勋熏循旬询寻驯巡殉汛训讯逊迅压押鸦鸭呀丫芽牙蚜崖衙涯雅哑亚讶焉咽阉烟淹盐严研蜒岩延言颜阎炎沿奄掩眼衍演艳堰燕厌砚");
		sb.append("雁唁彦焰宴谚验殃央鸯秧杨扬佯疡羊洋阳氧仰痒养样漾邀腰妖瑶摇尧遥窑谣姚咬舀药要耀椰噎耶爷野冶也页掖业叶曳腋夜液一壹医揖铱依伊衣颐夷遗移仪胰疑沂宜姨彝椅蚁倚已乙矣以艺抑易邑屹亿役臆逸肄疫亦裔意毅忆义益溢");
		sb.append("诣议谊译异翼翌绎茵荫因殷音阴姻吟银淫寅饮尹引隐印英樱婴鹰应缨莹萤营荧蝇迎赢盈影颖硬映哟拥佣臃痈庸雍踊蛹咏泳涌永恿勇用幽优悠忧尤由邮铀犹油游酉有友右佑釉诱又幼迂淤于盂榆虞愚舆余俞逾鱼愉渝渔隅予娱雨与屿");
		sb.append("禹宇语羽玉域芋郁吁遇喻峪御愈欲狱育誉浴寓裕预豫驭鸳渊冤元垣袁原援辕园员圆猿源缘远苑愿怨院曰约越跃钥岳粤月悦阅耘云郧匀陨允运蕴酝晕韵孕匝砸杂栽哉灾宰载再在咱攒暂赞赃脏葬遭糟凿藻枣早澡蚤躁噪造皂灶燥责择");
		sb.append("则泽贼怎增憎曾赠扎喳渣札轧铡闸眨栅榨咋乍炸诈摘斋宅窄债寨瞻毡詹粘沾盏斩辗崭展蘸栈占战站湛绽樟章彰漳张掌涨杖丈帐账仗胀瘴障招昭找沼赵照罩兆肇召遮折哲蛰辙者锗蔗这浙珍斟真甄砧臻贞针侦枕疹诊震振镇阵蒸挣睁");
		sb.append("征狰争怔整拯正政帧症郑证芝枝支吱蜘知肢脂汁之织职直植殖执值侄址指止趾只旨纸志挚掷至致置帜峙制智秩稚质炙痔滞治窒中盅忠钟衷终种肿重仲众舟周州洲诌粥轴肘帚咒皱宙昼骤珠株蛛朱猪诸诛逐竹烛煮拄瞩嘱主著柱助蛀");
		sb.append("贮铸筑住注祝驻抓爪拽专砖转撰赚篆桩庄装妆撞壮状椎锥追赘坠缀谆准捉拙卓桌琢茁酌啄着灼浊兹咨资姿滋淄孜紫仔籽滓子自渍字鬃棕踪宗综总纵邹走奏揍租足卒族祖诅阻组钻纂嘴醉最罪尊遵昨左佐柞做作坐座");
	}
	
}
