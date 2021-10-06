package com.ticketsystem.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class KnSqlManager {
	
	Logger log = LogManager.getLogger(KnSqlManager.class);
	
	/**
	 * 获取单个账户详情<br/>
	 * 传入accountNo<br/>
	 * @param orderInfoData
	 */
	public JSONObject getAccount(String accountNo) {
		// TODO 获取账户详情 
		String sqlStr = "select account_no,name,contact_mobile,password,use_time,encryptStr,session "
				+ " from account_kn where account_no='"+accountNo+"' ";
		DBUtil db = new DBUtil();
		JSONObject accountInfo = new JSONObject();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()){
				accountInfo.put("accountNo", rs.getString(1));
				accountInfo.put("name", rs.getString(2));
				accountInfo.put("contactMobile", rs.getString(3));
				accountInfo.put("accountPas", rs.getString(4));
				accountInfo.put("useTime", rs.getString(5));
				accountInfo.put("encryptStr", rs.getString(6));
				accountInfo.put("session", rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return accountInfo;
	}
	
	/**
	 * 获取账户列表<br/>
	 * 输入useTime<br/>
	 * @param orderInfoData
	 */
	public synchronized ArrayList<JSONObject> getAccountList(JSONObject filterData) {
		String useTime = filterData.getString("useTime");
		
		String sqlStr = "select account_no,name,contact_mobile,password,use_time,encryptStr,session,tokenUUID,tokenId "
				+ " from account_kn where (use_time < '"+useTime+"' or use_time is null) order by account_no asc ";
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
				accountData.put("accountPas", rs.getString(4));
				accountData.put("useTime", rs.getString(5));
				accountData.put("encryptStr", rs.getString(6));
				accountData.put("session", rs.getString(7));
				accountData.put("tokenUUID", rs.getString(8));
				accountData.put("tokenId", rs.getString(9));
				accountList.add(accountData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		String session=accountData.getString("session");
		String sqlStr = "update account_kn set "
				+ " use_time='"+useTime+"',session='"+session+"' "
				+ " where account_no='"+accountNo+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * 更新乘机客户的状态<br/>
	 * 传入customerId，customerStatus，orderNo<br/>
	 * @param orderInfoData
	 */
	public synchronized void updateCustomerStatus(JSONObject customerData) {
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
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			log.info("更新数量："+updateCount);
			orderReturnData.put("oiId", oiId);
			orderReturnData.put("orderNo", orderNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized JSONObject getOrderInfo(String oiId) {
		JSONObject orderInfoData = new JSONObject();
		//字段可自行扩展
		String sqlStr = "select oi_id,order_status,round,input_time,remark from order_info where oi_id='"+oiId+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()) {
				orderInfoData.put("oiId", rs.getString(1));
				orderInfoData.put("orderStatus", rs.getString(2));
				orderInfoData.put("round", rs.getString(3));
				orderInfoData.put("inputTime", rs.getString(4));
				orderInfoData.put("remark", rs.getString(5));
				//可扩展
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateOrderStatus2(String oiId, String orderStatus, String remark) {
		String sqlStr = "update order_info set "
				+ " order_status='"+orderStatus+"',remark='"+remark+"' "
				+ " where oi_id='"+oiId+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cityInfo;
	}
	
	/**
	 * 新增KN账号<br/>
	 * 传入accountNo,orderNo,tripCcode,flightNo,cabinCode,price,<br/>
	 * standbyCount,orderStatus,round,inputTime,updateTime,inputUser<br/>
	 * @param orderInfoData
	 * @return 订单ID
	 */
	public JSONObject insertKN(JSONObject addAccounttKN) {
		String accountNo=addAccounttKN.getString("accountNo");
		String name=addAccounttKN.getString("name");
		String accountPas=addAccounttKN.getString("accountPas");
		String mobile=addAccounttKN.getString("mobile");
		String useTime=addAccounttKN.getString("useTime");
		String encryptStr=addAccounttKN.getString("encryptStr");
		String session=addAccounttKN.getString("session");
		//自动生成主键oiId
		String sqlStr = "insert into account_kn (account_no,name,password,contact_mobile,use_time,encryptStr,session) "
				+ " values('"+accountNo+"','"+name+"','"+accountPas+"','"+mobile+"','"+useTime+"','"+encryptStr+"','"+session+"' ) "; 
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return addAccounttKN;
	}
	
	/**
	 * 获取联航账号信息<br/>
	 * 传入accountNo<br/>
	 * @param accountNo
	 */
	public JSONObject getKnAccount(String accountNo) {
		//根据ID获取联航账号信息
		String sqlStr = "select account_no,name,password,contact_mobile,use_time,encryptStr,session "
				+ " from account_kn where account_no='"+accountNo+"' ";
		DBUtil db = new DBUtil();
		JSONObject knAccountInfo = new JSONObject();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()){
				knAccountInfo.put("accountNo", rs.getString(1));
				knAccountInfo.put("name", rs.getString(2));
				knAccountInfo.put("accountPas", rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return knAccountInfo;
	}

	public synchronized void updateAccountSession(JSONObject updateSessionData) {
		String accountNo=updateSessionData.getString("accountNo");
		String useTime=updateSessionData.getString("useTime");
		String tokenUUID=updateSessionData.getString("tokenUUID");
		String tokenId=updateSessionData.getString("tokenId");
		String session=updateSessionData.getString("session");
		String sqlStr = "update account_kn set "
				+ " use_time='"+useTime+"',session='"+session+"',tokenUUID='"+tokenUUID+"',tokenId='"+tokenId+"' "
				+ " where account_no='"+accountNo+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除订单<br/>
	 * @param oiId 订单流水号
	 */
	public void deleteOrder(String oiId) {
		String sqlStr = "delete from order_info "
				+ " where oi_id='"+oiId+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteLost() {
		String sqlStr = "delete from order_lost "
				+ " where 1=1 ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public JSONObject getLost() {
		//根据ID获取联航账号信息
		String sqlStr = "select account_no,trip_code as olCount "
				+ " from order_lost where 1=1 ";
		DBUtil db = new DBUtil();
		JSONObject lostJson = new JSONObject();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()){
				lostJson.put("resultStr", rs.getString(1)+"K"+rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lostJson;
	}

	public void insertLost(String accountNo, String tripCode) {
		String orderNo="9999";
		//自动生成主键oiId
		String oiId = System.currentTimeMillis()+"X"+Math.round(Math.random()*100000);
		String sqlStr = "insert into order_lost (oi_id,account_no,order_no,trip_code ) "
				+ " values('"+oiId+"','"+accountNo+"','"+orderNo+"','"+tripCode+"' ) "; 
		DBUtil db = new DBUtil();
		try {
			db.open();
			db.executeUpdate(sqlStr);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void updateOrderInputTime(String oiId, String secondTimeStr, String remark) {
		String sqlStr = "update order_info set "
				+ " input_time='"+secondTimeStr+"',remark='"+remark+"' "
				+ " where oi_id='"+oiId+"' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized JSONObject getBaiduTokenId() {
		JSONObject baiduTokenData = new JSONObject();
		//字段可自行扩展
		String sqlStr = "select code_no,item_no,item_value,remark from code_library where code_no='System' and item_no='BaiduTokenId' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			ResultSet rs = db.executeQuery(sqlStr);
			if(rs.next()) {
				baiduTokenData.put("codeNo", rs.getString(1));
				baiduTokenData.put("itemNo", rs.getString(2));
				baiduTokenData.put("itemValue", rs.getString(3));
				baiduTokenData.put("remark", rs.getString(4));
				//可扩展
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return baiduTokenData;
	}

	public void updateBaiduTokenId(String tokenId) {
		String sqlStr = "update code_library set "
				+ " item_value='"+tokenId+"' "
				+ " where code_no='System' and item_no='BaiduTokenId' ";
		DBUtil db = new DBUtil();
		try {
			db.open();
			int updateCount = db.executeUpdate(sqlStr);
			log.info("更新数量："+updateCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
