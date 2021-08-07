package com.ticketsystem.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketsystem.dao.OrderInfoMapper;
import com.ticketsystem.model.OrderInfo;
import com.ticketsystem.model.OrderInfoExample;
import com.ticketsystem.model.OrderInfoExample.Criteria;
import com.ticketsystem.util.CommUtils;
import com.ticketsystem.util.DemoData;

@Service
public class OrderInfoService {

	@Autowired
	private OrderInfoMapper orInfoMapper;
	
	public List<OrderInfo> getOrderInfoList(){
		OrderInfoExample example= new OrderInfoExample();
		Criteria createCriteria = example.createCriteria();
		List<String> orderList = new ArrayList<>();
		orderList.add("正常");
		orderList.add("订单暂停");
		orderList.add("压票失败");
		orderList.add("取消失败");
		orderList.add("暂停失败");
		createCriteria.andOrderStatusIn(orderList);
		List<OrderInfo> orderInfoList = orInfoMapper.selectByExample(example);
		if (orderInfoList != null && orderInfoList.size() > 0) {
			
			int countTimes = DemoData.COUNTMM;
    		for(OrderInfo orderInfo : orderInfoList) {
    			String inputTime = orderInfo.getInputTime();
    			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    			try {
					Date orderBeginDate = df.parse(inputTime);
					Date currentDate = new Date();
					long diff = currentDate.getTime() - orderBeginDate.getTime();
					double between_hour = Math.floor(diff / 1000 / 60 / 60 % 24);
					double between_minus = Math.floor(diff / 1000 / 60 % 60);
					double between_sec = Math.floor(diff / 1000 % 60);
					if (between_hour <= 0 && between_minus < countTimes) {
						long have_sec = (long) (countTimes * 60 - between_minus * 60 - between_sec);
						String showTime = CommUtils.secondToTime(have_sec);
						orderInfo.setCountTime(showTime);
					} else {
						orderInfo.setCountTime("00:00:00");
					}
					
				} catch (ParseException e) {
				}
    		}
    	}
		return orderInfoList;
	}
	
	public List<OrderInfo> getHistoryList(){
		OrderInfoExample example= new OrderInfoExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andOrderStatusEqualTo("正常结束");
		List<OrderInfo> orderInfoList = orInfoMapper.selectByExample(example);
		if (orderInfoList != null && orderInfoList.size() > 0) {
			
			int countTimes = DemoData.COUNTMM;
    		for(OrderInfo orderInfo : orderInfoList) {
    			String inputTime = orderInfo.getInputTime();
    			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    			try {
					Date orderBeginDate = df.parse(inputTime);
					Date currentDate = new Date();
					long diff = currentDate.getTime() - orderBeginDate.getTime();
					double between_hour = Math.floor(diff / 1000 / 60 / 60 % 24);
					double between_minus = Math.floor(diff / 1000 / 60 % 60);
					double between_sec = Math.floor(diff / 1000 % 60);
					if (between_hour <= 0 && between_minus < countTimes) {
						long have_sec = (long) (countTimes * 60 - between_minus * 60 - between_sec);
						String showTime = CommUtils.secondToTime(have_sec);
						orderInfo.setCountTime(showTime);
					} else {
						orderInfo.setCountTime("00:00:00");
					}
					
				} catch (ParseException e) {
				}
    		}
    	}
		return orderInfoList;
	}
}
