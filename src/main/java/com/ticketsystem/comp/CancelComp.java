package com.ticketsystem.comp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.CookieUtil;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.net.PostSender;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.KnSqlManager;

public class CancelComp {
	
	Logger log = LogManager.getLogger(CancelComp.class);

	public void cancelTicket(String orderNo, String accountNo) throws Exception {
		
		KnSqlManager sqlManager = new KnSqlManager();
		JSONObject account = sqlManager.getAccount(accountNo);
		String session = account.getString("session");
		
		String orderDetailUrl = DemoData.orderDetailUrl ;
		String paramStr = "_="+String.valueOf(Math.random()).substring(2, 15) 
				+ "&orderId="+orderNo;
		String orderInfoCookie = CookieUtil.getOrderInfoCookie(session);
		log.info("===取消前进入订单详情：url["+orderDetailUrl+"],param["+paramStr+"],cookie["+orderInfoCookie+"]=====");
		System.out.println("===取消前进入订单详情：url["+orderDetailUrl+"],param["+paramStr+"],cookie["+orderInfoCookie+"]=====");
		new GetSender().sendHttpGet(orderDetailUrl, paramStr, orderInfoCookie);
		
		try {
			String cancelUrl = DemoData.cancelUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String paramStr2 = "{\"pNm\":\"1002\",\"mProductNm\":\"0\"}";
			log.info("===取消订单：url["+cancelUrl+"],param["+paramStr2+"],cookie["+orderInfoCookie+"]=====");
			System.out.println("===取消订单：url["+cancelUrl+"],param["+paramStr2+"],cookie["+orderInfoCookie+"]=====");
			new PostSender().cancelPost(cancelUrl, paramStr2, orderInfoCookie);
		} catch (Exception e) {
			log.info("===取消订单失败=====");
			System.out.println("===取消订单失败=====");
			e.printStackTrace();
			throw e;
		}
	}

}
