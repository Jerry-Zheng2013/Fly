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
	public JSONObject getAccount(String accountNo) {
		// TODO 获取账户详情 
		String sqlStr = "select account_no,name,contact_mobile,password,use_time,encryptStr,session "
				+ " from account_info where account_no='"+accountNo+"' ";
		DBUtil db = new DBUtil();
		JSONObject accountInfo = new JSONObject();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()){
				accountInfo.put("accountNo", rs.getString(1));
				accountInfo.put("name", rs.getString(2));
				accountInfo.put("contactMobile", rs.getString(3));
				accountInfo.put("password", rs.getString(4));
				accountInfo.put("useTime", rs.getString(5));
				accountInfo.put("encryptStr", rs.getString(6));
				accountInfo.put("session", rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return accountInfo;
	}
	
	/**
	 * 获取账户列表<br/>
	 * 输入useTime<br/>
	 * @param orderInfoData
	 */
	public ArrayList<JSONObject> getAccountList(JSONObject filterData) {
		String useTime = filterData.getString("useTime");
		
		String sqlStr = "select account_no,name,contact_mobile,password,use_time,encryptStr,session "
				+ " from account_info where (use_time < '"+useTime+"' or use_time is null) order by account_no asc ";
		DBUtil db = new DBUtil();
		ArrayList<JSONObject> accountList = new ArrayList<JSONObject>();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			while(rs.next()){
				JSONObject accountData = new JSONObject();
				accountData.put("accountNo", rs.getString(1));
				accountData.put("name", rs.getString(2));
				accountData.put("contactMobile", rs.getString(3));
				accountData.put("password", rs.getString(4));
				accountData.put("useTime", rs.getString(5));
				accountData.put("encryptStr", rs.getString(6));
				accountData.put("session", rs.getString(7));
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
		
		String sqlStr = "select customer_id,name,card_type,card_no,mobile,birthday,ticket_type,customer_status,account_no "
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
				customerData.put("customerStatus", rs.getString(8));
				customerData.put("accountNo", rs.getString(9));
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
	 * 传入customerId，customerStatus，orderNo<br/>
	 * @param orderInfoData
	 */
	public void updateCustomerStatus(JSONObject customerData) {
		String customerId=customerData.getString("customerId");
		String customerStatus=customerData.getString("customerStatus");
		String accountNo=customerData.getString("accountNo");
		String sqlStr = "update customer_info set "
				+ " customer_status='"+customerStatus+"',account_no='"+accountNo+"' "
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
		String oiId = System.currentTimeMillis()+"X"+Math.round(Math.random()*100000);
		String sqlStr = "insert into order_info (oi_id,account_no,order_no,trip_code,flight_no,cabin_code,price,standby_count,order_status,round,input_time,update_time,input_user) "
				+ " values('"+oiId+"','"+accountNo+"','"+orderNo+"','"+tripCode+"','"+flightNo+"','"+cabinCode+"','"+price+"','"+standbyCount+"','"+orderStatus+"','"+round+"','"+inputTime+"','"+updateTime+"','"+inputUser+"' ) "; 
		DBUtil db = new DBUtil();
		JSONObject orderReturnData = new JSONObject();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			System.out.println("更新数量："+updateCount);
			orderReturnData.put("oiId", oiId);
			orderReturnData.put("orderNo", orderNo);
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
		String sqlStr = "update order_info set "
				+ " account_no='"+accountNo+"',order_no='"+orderNo+"',standby_count='"+standbyCount+"',order_status='"+orderStatus+"',update_time='"+updateTime+"',round='"+round+"' "
				+ " where oi_id='"+oiId+"' ";
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
		String sqlStr = "select oi_id,order_status,round from order_info where oi_id='"+oiId+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()) {
				orderInfoData.put("oiId", rs.getString(1));
				orderInfoData.put("orderStatus", rs.getString(2));
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
	
	public void updateOrderStatus(JSONObject orderData) {
		String oiId=orderData.getString("oiId");
		String orderStatus=orderData.getString("orderStatus");
		String sqlStr = "update order_info set "
				+ " order_status='"+orderStatus+"' "
				+ " where oi_id='"+oiId+"' ";
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
	
	public void updateOrderStatus2(String oiId, String orderStatus) {
		String sqlStr = "update order_info set "
				+ " order_status='"+orderStatus+"' "
				+ " where oi_id='"+oiId+"' ";
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
	
	public void updateCustomerByOrder(JSONObject cancelData) {
		String accountNo=cancelData.getString("accountNo");
		String customerStatus=cancelData.getString("customerStatus");
		String sqlStr = "update customer_info set "
				+ " customer_status='"+customerStatus+"' "
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
	
	public void updateCustomerByOrder2(String accountNo, String customerStatus) {
		String sqlStr = "update customer_info set "
				+ " customer_status='"+customerStatus+"' "
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
	 * 获取单个账户详情<br/>
	 * 传入accountNo<br/>
	 * @param orderInfoData
	 */
	public JSONObject getCityInfo(String cityNo) {
		//根据ID获取城市详情
		String sqlStr = "select city_no,city_name,city_code,remark "
				+ " from city_info where city_no='"+cityNo+"' ";
		DBUtil db = new DBUtil();
		JSONObject cityInfo = new JSONObject();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()){
				cityInfo.put("cityNo", rs.getString(1));
				cityInfo.put("cityName", rs.getString(2));
				cityInfo.put("cityCode", rs.getString(3));
				cityInfo.put("remark", rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return cityInfo;
	}
}
