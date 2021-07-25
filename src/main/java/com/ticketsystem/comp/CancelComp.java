package com.ticketsystem.comp;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.CookieUtil;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.net.PostSender;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.KnSqlManager;

public class CancelComp {

	public synchronized void cancelTicket(String orderNo, String accountNo) {
		KnSqlManager sqlManager = new KnSqlManager();
		JSONObject account = sqlManager.getAccount(accountNo);
		String session = account.getString("session");
		
		String orderDetailUrl = DemoData.orderDetailUrl ;
		String paramStr = "_="+String.valueOf(Math.random()).substring(2, 15) 
				+ "&orderId="+orderNo;
		String orderInfoCookie = CookieUtil.getOrderInfoCookie(session);
		JSONObject orderInfoResult = new GetSender().sendHttpGet(orderDetailUrl, paramStr, orderInfoCookie);
		
		String cancelUrl = DemoData.cancelUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
		String paramStr2 = "{\"pNm\":\"1002\",\"mProductNm\":\"0\"}";
		JSONObject sendHttpPost = new PostSender().sendHttpPost(cancelUrl, paramStr2, orderInfoCookie);
		System.out.println(sendHttpPost.getString("responseBody"));
	}

}
