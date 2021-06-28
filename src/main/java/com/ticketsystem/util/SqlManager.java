package com.ticketsystem.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

public class SqlManager {
	
	/**
	 * 获取单个账户详情<br/>
	 * 传入accountNo<br/>
	 * @param orderInfoData
	 */
	public void getAccount(JSONObject accountData) {
		// TODO 获取账户详情 
	}
	
	/**
	 * 获取账户列表<br/>
	 * 输入useTime<br/>
	 * @param orderInfoData
	 */
	public ArrayList<JSONObject> getAccountList(JSONObject filterData) {
		String useTime = filterData.getString("useTime");
		
		String sqlStr = "select account_no,name,password,contact_mobile,use_time "
				+ " from account_info where (use_time < '"+useTime+"' or use_time is null) ";
		DBUtil db = new DBUtil();
		ArrayList<JSONObject> accountList = new ArrayList<JSONObject>();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			while(rs.next()){
				JSONObject accountData = new JSONObject();
				accountData.put("customerOrderNo", rs.getString(1));
				accountData.put("contactName", rs.getString(2));
				accountData.put("contactMobile", rs.getString(4));
				accountData.put("flightRangeType", "0");
				accountData.put("InsuranceCodes", null);
				accountData.put("isApplyReimbursement", false);
				accountData.put("callBackUrl", "");
				accountList.add(accountData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return accountList;
	}
	/**
	 * 获取单个乘机客户详情<br/>
	 * 参数暂定<br/>
	 * @param orderInfoData
	 */
	public void getCustomer(JSONObject accountData) {
		// TODO 获取账户列表
	}
	/**
	 * 获取客户列表<br/>
	 * 输入customerStatus<br/>
	 * @param orderInfoData
	 */
	public ArrayList<JSONObject> getCustomerList(JSONObject filterData) {
		String customerStatus = filterData.getString("customerStatus");
		
		String sqlStr = "select customer_id,name,card_type,card_no,mobile,birthday,ticket_type,customer_status "
				+ " from customer_info where (customer_status = '"+customerStatus+"' or customer_status is null) ";
		DBUtil db = new DBUtil();
		ArrayList<JSONObject> customerList = new ArrayList<JSONObject>();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			while(rs.next()){
				JSONObject customerData = new JSONObject();
				customerData.put("customerId", rs.getString(1));
				customerData.put("name", rs.getString(2));
				customerData.put("cardType", rs.getString(3));
				customerData.put("cardNo", rs.getString(4));
				customerData.put("mobile", rs.getString(5));
				customerData.put("birthday", rs.getString(6));
				customerData.put("ticketType", rs.getString(7));
				customerList.add(customerData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return customerList;
	}
	
	/**
	 * 更新官网账户使用时间<br/>
	 * 传入accountNo，useTime<br/>
	 * @param orderInfoData
	 */
	public void updateAccountTime(JSONObject accountData) {
		String accountNo=accountData.getString("accountNo");
		String useTime=accountData.getString("useTime");
		String sqlStr = "update account_info set "
				+ " use_time='"+useTime+"' "
				+ " where account_no='"+accountNo+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			System.out.println("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
	}
	

	/**
	 * 更新乘机客户的状态<br/>
	 * 传入customerId，customerStatus<br/>
	 * @param orderInfoData
	 */
	public void updateCustomerStatus(JSONObject customerData) {
		String customerId=customerData.getString("customerId");
		String customerStatus=customerData.getString("customerStatus");
		String sqlStr = "update customer_info set "
				+ " customer_status='"+customerStatus+"' "
				+ " where customer_id='"+customerId+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			System.out.println("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
	}
	

	/**
	 * 新增订单信息<br/>
	 * 传入accountNo,orderNo,tripCcode,flightNo,cabinCode,price,<br/>
	 * standbyCount,orderStatus,round,inputTime,updateTime,inputUser<br/>
	 * @param orderInfoData
	 * @return 订单ID
	 */
	public JSONObject insertOrderInfo(JSONObject orderInfoData) {
		String accountNo=orderInfoData.getString("accountNo");
		String orderNo=orderInfoData.getString("orderNo");
		String tripCode=orderInfoData.getString("tripCode");
		String flightNo=orderInfoData.getString("flightNo");
		String cabinCode=orderInfoData.getString("cabinCode");
		String price=orderInfoData.getString("price");
		String standbyCount=orderInfoData.getString("standbyCount");
		String orderStatus=orderInfoData.getString("orderStatus");
		String round=orderInfoData.getString("round");
		String inputTime=orderInfoData.getString("inputTime");
		String updateTime=orderInfoData.getString("updateTime");
		String inputUser=orderInfoData.getString("inputUser");
		//自动生成主键oiId
		String oiId = System.currentTimeMillis()+"-"+Math.random();
		String sqlStr = "insert into order_info (oi_id,account_no,order_no,trip_code,flight_no,cabin_code,price,standby_count,order_status,round,input_time,update_time,input_user) "
				+ " values('"+oiId+"','"+accountNo+"','"+orderNo+"','"+tripCode+"','"+flightNo+"','"+cabinCode+"','"+price+"','"+standbyCount+"','"+orderStatus+"','"+round+"','"+inputTime+"','"+updateTime+"','"+inputUser+"' ) "; 
		DBUtil db = new DBUtil();
		JSONObject orderReturnData = new JSONObject();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			System.out.println("更新数量："+updateCount);
			orderReturnData.put("oiId", oiId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return orderReturnData;
	}

	/**
	 * 更新指定订单信息<br/>
	 * 传入oiId,accountNo,orderNo,standbyCount,orderStatus,updateTime,round
	 * @param oiId
	 * @return
	 */
	public void updateOrder(JSONObject orderDate) {
		String oiId=orderDate.getString("oiId");
		String accountNo=orderDate.getString("accountNo");
		String orderNo=orderDate.getString("orderNo");
		String standbyCount=orderDate.getString("standbyCount");
		String orderStatus=orderDate.getString("orderStatus");
		String updateTime=orderDate.getString("updateTime");
		String round=orderDate.getString("round");
		round = String.valueOf(Integer.valueOf(round)+1);
		String sqlStr = "update order_info set "
				+ " accountNo='"+accountNo+"',orderNo='"+orderNo+"',standbyCount='"+standbyCount+"',orderStatus='"+orderStatus+"',updateTime='"+updateTime+"',round='"+round+"' "
				+ " where oiId='"+oiId+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			System.out.println("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
	}
	
	public JSONObject getOrderInfo(String oiId) {
		JSONObject orderInfoData = new JSONObject();
		//字段可自行扩展
		String sqlStr = "select oi_id,status,round from o where oi_id='"+oiId+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()) {
				orderInfoData.put("oi_id", rs.getInt(1));
				orderInfoData.put("status", rs.getString(2));
				orderInfoData.put("round", rs.getString(3));
				//可扩展
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return orderInfoData;
	}
	
}
