package com.ticketsystem.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.async.ApplicationContextProvider;
import com.ticketsystem.async.AsyncService;
import com.ticketsystem.net.DemoNet;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.SqlManager;

@Service
public class DemoService {

	@Autowired
	private AsyncService asyncService;
	
	/**
     * 预定<br/>
     * 传入fromCityCode，toCityCode，fromDate，<br/>
     * fightNo，cabinCode
     * @param 预定信息
     * @return
     */
	public void addTicket(JSONObject addData) {
		//开始新增订票逻辑
		JSONObject bigData = this.booking(addData);
    	
    	//调用循环逻辑，开始循环预定
		int standbyCount = bigData.getIntValue("standbyCount");
		if (standbyCount > 0) {
			this.bookLoop(bigData);
		}
	}
	
	/**
     * 预定<br/>
     * @param 预定信息addData{oiId, fromCityCode，toCityCode，fromDate，fightNo，cabinCode}<br/>
     * @return <br/>
     * 		bigData{
     * 			addData,
     * 			packageArrData[
     * 				accountData,customerArrData,bookData,orderData
     * 			],
     * 			otherData
     * 		}<br/>
     */
    public JSONObject booking(JSONObject addData) {
    	JSONObject bigData = new JSONObject();
    	bigData.put("addData", addData);
    	ArrayList<JSONObject> packageArrData = new ArrayList<JSONObject>();

    	//TODO 调用接口----------查询订单接口
    	JSONObject standbyData = new DemoNet().queryTicket(addData);    	
    	int standbyCount  = standbyData.getIntValue("standbyCount");
    	
    	//standbyCount = 0;
    	
    	bigData.put("standbyCount", standbyCount);
    	
    	//预定数据组装
    	if (standbyCount>0) {
    		//向上取整，决定用多少个账号进行预定
    		double accountCount = Math.ceil(Double.valueOf(
    				new BigDecimal(String.valueOf(standbyCount))
    				.divide(
    				new BigDecimal(String.valueOf(DemoData.PERSONTICKETS))).toPlainString()));
    		SqlManager sqlManager = new SqlManager();
    		//获取官网账户信息
    		JSONObject filterData = new JSONObject();
    		Date currentDate = new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		String currentDateStr = sdf.format(currentDate);
    		filterData.put("useTime", currentDateStr);
    		ArrayList<JSONObject> accountList = sqlManager.getAccountList(filterData);
    		
    		//获取乘机客户信息
    		JSONObject filterData2 = new JSONObject();
    		filterData2.put("customerStatus", "1");
    		ArrayList<JSONObject> customerList = sqlManager.getCustomerList(filterData2);
    		
    		int f=0;
    		for (int d=0;d<accountCount;d++) {
    			JSONObject packageData = new JSONObject();
    			JSONObject accountData = new JSONObject();
    	    	ArrayList<JSONObject> customerArrData = new ArrayList<JSONObject>();
    			JSONObject bookData = new JSONObject();
    			JSONObject orderData = new JSONObject();
    			
    			if (d<accountList.size()) {
    				//预定信息
    				JSONObject bookMap = new JSONObject();
    				
    				//获取一条官网账户信息
        			accountData = accountList.get(d);
        			
        			//更新账户使用时间
            		Date currentDate2 = new Date();
            		String currentDateStr2 = sdf.format(currentDate2);
        			JSONObject updateTimeData = new JSONObject();
        			String accountNo = accountData.getString("accountNo");
        			updateTimeData.put("accountNo", accountNo);
        			updateTimeData.put("useTime", currentDateStr2);
        			sqlManager.updateAccountTime(updateTimeData);
        			
        			//预定信息-官网账户信息
        			JSONObject bookAccountData = new JSONObject();
        			bookAccountData.put("customerOrderNo", accountData.getString("accountNo"));
        			bookAccountData.put("contactName", accountData.getString("name"));
        			bookAccountData.put("contactMobile", accountData.getString("contactMobile"));
        			bookAccountData.put("flightRangeType", "0");
        			bookAccountData.put("InsuranceCodes", null);
        			bookAccountData.put("isApplyReimbursement", false);
        			bookAccountData.put("callBackUrl", "");
        			bookMap=bookAccountData;
    				bookMap.put("appKey", DemoData.getSysData().getString("appKey"));
        			
        			//预定信息-客户信息
            		ArrayList<JSONObject> bookCustomerDataArr = new ArrayList<JSONObject>();
            		ArrayList<JSONObject> customerDataArr = new ArrayList<JSONObject>();
        			for (;f<DemoData.PERSONTICKETS*(d+1);f++) {
        				if (f<standbyCount) {
            				if(f<customerList.size()) {
            					//获取一条乘客信息
            					JSONObject customerData = customerList.get(f);
            					customerDataArr.add(customerData);
            					
            					//组装需要发送的乘客信息
            					JSONObject bookCustomerData = new JSONObject();
            					bookCustomerData.put("name", customerData.getString("name"));
            					bookCustomerData.put("cardType", customerData.getString("cardType"));
            					bookCustomerData.put("cardNo", customerData.getString("cardNo"));
            					bookCustomerData.put("mobile", customerData.getString("mobile"));
            					bookCustomerData.put("birthday", customerData.getString("birthday"));
            					bookCustomerData.put("ticketType", customerData.getString("ticketType"));
            					bookCustomerDataArr.add(bookCustomerData);
            					
            					//更新客户状态-锁定客户
            					JSONObject updateStatusData = new JSONObject();
            					updateStatusData.put("customerId", customerData.getString("customerId"));
            					updateStatusData.put("customerStatus", "2");
            					updateStatusData.put("accountNo", accountData.getString("accountNo"));
            					sqlManager.updateCustomerStatus(updateStatusData);
            				} else {
            					System.out.println("乘机人信息已不够用了！！！");
            				}
            			}
            		}
        			customerArrData.addAll(customerArrData.size(), customerDataArr);
        			bookMap.put("passengers", bookCustomerDataArr);
        			
        			//预定信息-航班信息
        			ArrayList<Object> flightArr = new ArrayList<Object>();
        			flightArr.add(standbyData);
        			bookMap.put("flights", flightArr);
        			
        			bookData = new JSONObject(bookMap);
            		// 预定
        	    	//TODO 调用接口----------订票订单接口
            		orderData = new DemoNet().bookTicket(bookData);
            		
            		//新增或者更新订单信息
            		JSONObject orderInfoData = new JSONObject();
            		orderInfoData.put("accountNo", accountData.getString("accountNo"));
            		orderInfoData.put("orderNo", orderData.getJSONObject("data").getString("orderNo"));
            		orderInfoData.put("tripCode", addData.getString("tripCode"));
            		orderInfoData.put("flightNo", addData.getString("fightNo"));
            		orderInfoData.put("cabinCode", addData.getString("cabinCode"));
            		orderInfoData.put("standbyCount", standbyCount);
            		double totalAmount = orderData.getJSONObject("data").getDoubleValue("orderAmount");
            		double price = totalAmount/Double.valueOf(standbyCount);
            		orderInfoData.put("price", String.valueOf(price));
            		orderInfoData.put("orderStatus", "1");
            		orderInfoData.put("round", "1");
            		Date currentTime = new Date();
            		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            		orderInfoData.put("inputTime", sdf2.format(currentTime));
            		orderInfoData.put("updateTime", sdf2.format(currentTime));
            		orderInfoData.put("inputUser", "user");

            		String oiId = addData.getString("oiId");
            		if (oiId == null || oiId.length()<1) {
            			//新增订单信息
            			JSONObject insertOrderInfo = sqlManager.insertOrderInfo(orderInfoData);
            			orderData.put("oiId", insertOrderInfo.getString("oiId"));
            			
            		} else {
            			//更新订单信息
            			JSONObject orderInfo2 = sqlManager.getOrderInfo(oiId);
            			String round2= orderInfo2.getString("round");
            			orderInfoData.put("oiId", oiId);
            			orderInfoData.put("round", String.valueOf(Integer.valueOf(round2)+1));
            			sqlManager.updateOrder(orderInfoData);
            			orderData.put("oiId", oiId);
            		}
            		
            		
        			packageData.put("accountData", accountData);
        			packageData.put("customerArrData", customerArrData);
        			packageData.put("bookData", bookData);
        			packageData.put("orderData", orderData);
        			packageData.put("standbyCount", standbyCount);
        			packageArrData.add(packageData);
    			} else {
    				System.out.println("官网账号已不够用了！！！");
    			}
    		}
    		
    		//返回信息
    		bigData.put("packageArrData", packageArrData);
    		
    	} else {
    		System.out.println("查询航班没有余票啦！");
    	}
    	return bigData;
    }


    /**
     * 取消订单<br/>
     *<br/>
     * @param cancelData{oiId, accountNo}<br/>
     * @return void<br/>
     */
    public void cancelTicket(JSONObject cancelData) {
    	SqlManager sqlManager = new SqlManager();
    	//更新订单信息
    	JSONObject orderData = new JSONObject();
    	orderData.put("oiId", cancelData.getString("oiId"));
    	orderData.put("orderStatus", "2");
    	sqlManager.updateOrderStatus(orderData);
    	//TODO 调用接口----------取消订单接口
    	new DemoNet().cancelTicket(cancelData);
    	
    	//更新客户信息
    	cancelData.put("customerStatus", "2");
    	sqlManager.updateCustomerByOrder(cancelData);
    }
    
    
    /**
     * 调用循环订票程序
     *
     * @param user
     * @return
     */
    private void bookLoop(JSONObject bigData) {
    	// 预定(循环)
    	try {
    		JSONArray jsonArray = bigData.getJSONArray("packageArrData");
    		for (int i=0;i<jsonArray.size();i++) {
    			JSONObject loopData = new JSONObject();
    			loopData.put("addData", bigData.getJSONObject("addData"));
    			loopData.put("customerArrData", jsonArray.getJSONObject(i).getJSONArray("customerArrData"));
    			loopData.put("orderInfoData", jsonArray.getJSONObject(i).getJSONObject("orderData"));
    			//获取线程池服务
    			this.asyncService = ApplicationContextProvider.getBean(AsyncService.class);
    			//启动线程
    			asyncService.bookLoop(loopData);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
