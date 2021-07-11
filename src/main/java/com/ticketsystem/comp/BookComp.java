package com.ticketsystem.comp;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.net.PostSender;
import com.ticketsystem.util.DemoData;

public class BookComp {
	
	public JSONObject bookTicket(String bookDataStr, String bookCookie) {
		JSONObject bookResult = new JSONObject();
		String bookUrl = DemoData.bookUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
		PostSender.bookPost(bookUrl, bookDataStr, bookCookie);
		
		//获取支付详情
		String paymentDetailUrl = DemoData.paymentDetailUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
		JSONObject paymentResult = new GetSender().sendHttpGet(paymentDetailUrl, "", bookCookie);
		String paymentResultBody = paymentResult.getString("responseBody");
		if (paymentResultBody.length() > 0) {
			JSONObject paymentResultJson = JSONObject.parseObject(paymentResultBody);
			String pnrId = paymentResultJson.getJSONObject("res").getJSONObject("Booking").getString("SuperPNR_ID");
			bookResult.put("orderNo", pnrId);
		}
		return bookResult;
	}
}
