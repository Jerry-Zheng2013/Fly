package com.ticketsystem.comp;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.CookieUtil;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.net.PostSender;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.KnSqlManager;

public class CancelComp {

	public void cancelTicket(String orderNo, String accountNo) throws Exception {
		
		KnSqlManager sqlManager = new KnSqlManager();
		JSONObject account = sqlManager.getAccount(accountNo);
		String session = account.getString("session");
		
		String orderDetailUrl = DemoData.orderDetailUrl ;
		String paramStr = "_="+String.valueOf(Math.random()).substring(2, 15) 
				+ "&orderId="+orderNo;
		String orderInfoCookie = CookieUtil.getOrderInfoCookie(session);
		new GetSender().sendHttpGet(orderDetailUrl, paramStr, orderInfoCookie);
		
		try {
			String cancelUrl = DemoData.cancelUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String paramStr2 = "{\"pNm\":\"1002\",\"mProductNm\":\"0\"}";
			new PostSender().cancelPost(cancelUrl, paramStr2, orderInfoCookie);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
