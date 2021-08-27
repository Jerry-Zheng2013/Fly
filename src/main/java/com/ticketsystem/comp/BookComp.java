package com.ticketsystem.comp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.net.PostSender;
import com.ticketsystem.util.DemoData;

public class BookComp {

	Logger log = LogManager.getLogger(BookComp.class);
	
	public JSONObject bookTicket(String bookDataStr, String bookCookie) {
		JSONObject bookResult = new JSONObject();
		String bookUrl = DemoData.bookUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);

		log.info("---机票预定URL="+bookUrl+"---PARAM="+bookDataStr+"---COOKIE="+bookCookie);
		System.err.println("---机票预定URL="+bookUrl+"---PARAM="+bookDataStr+"---COOKIE="+bookCookie);
		PostSender.bookPost(bookUrl, bookDataStr, bookCookie);
		
		//获取支付详情
		String paymentDetailUrl = DemoData.paymentDetailUrl;
		String param ="_="+String.valueOf(Math.random()).substring(2, 15);
		JSONObject paymentResult = new GetSender().sendHttpGet(paymentDetailUrl, param, bookCookie);
		String paymentResultBody = paymentResult.getString("responseBody");
		if (paymentResultBody.length() > 0) {
			JSONObject paymentResultJson = JSONObject.parseObject(paymentResultBody);
			String pnrId = paymentResultJson.getJSONObject("res").getJSONObject("PaymentDetails").getJSONObject("Booking").getString("SuperPNR_ID");
			bookResult.put("orderNo", pnrId);
		}
		return bookResult;
	}
}
