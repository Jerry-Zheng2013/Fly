package com.ticketsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.async.ApplicationContextProvider;
import com.ticketsystem.async.AsyncService;
import com.ticketsystem.model.DemoOrder;
import com.ticketsystem.net.DemoNet;
import com.ticketsystem.util.DemoData;

@Service
public class DemoService {

	@Autowired
	private AsyncService asyncService;
	
	
	/**
     * 预定
     *
     * @param 预定信息
     * @return
     */
    public void add(JSONObject addData) {
    	String fromCity = addData.getString("tripStr").substring(5, 8);
    	String toCity = addData.getString("tripStr").substring(8, 11);
    	String goTime = addData.getString("tripStr").substring(12, 20);
    	String fightNo = addData.getString("fightNo");
    	String cabinCode = addData.getString("cabinCode");
    	
    	JSONObject queryData = addData;
    	//JSONObject standbyData = new DemoNet().queryTicket(queryData);
    	//int standbyCount = standbyData.getIntValue("standbyCount");
    	int standbyCount = 1;
    	ArrayList<Object> customerArr = new ArrayList<Object>();
    	for (int i=0; i<1; i++) {
    		JSONObject customerData = DemoData.getCustomerData();
    		customerArr.add(customerData);
    	}
    	JSONObject bookData = new JSONObject(new HashMap<String, Object>());
    	
    	// 预定
    	this.bookLoop(bookData);
    }
    
    
    /**
     * 查询
     *
     * @param user
     * @return
     */
    public void bookLoop(JSONObject bookData) {
    	
    	Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("appKey", "f46a96420331ea3be28eaf1036af4252");
        dataMap.put("customerOrderNo", "17656175477");
        dataMap.put("flightRangeType", "0");
        dataMap.put("InsuranceCodes", null);
        dataMap.put("isApplyReimbursement", false);
        dataMap.put("contactName", "李伟");
        dataMap.put("contactMobile", "17656175477");
        dataMap.put("callBackUrl", "");
        
        ArrayList<Object> flightArr = new ArrayList<Object>();
        Map<String, Object> flightMap1 = new HashMap<String, Object>();
        flightMap1.put("flightNo", "KN5218");
        flightMap1.put("fromAirportCode", "CTU");
        flightMap1.put("toAirportCode", "PKX");
        flightMap1.put("fromDateTime", "2021-07-24 19:20");
        flightMap1.put("toDateTime", "2021-07-24 23:50");
        flightMap1.put("fromTerminal", "T2");
        flightMap1.put("toTerminal", "");
        flightMap1.put("isShareFlight", false);
        flightMap1.put("shareFlightNo", null);
        flightMap1.put("cabinCode", "V");
        flightMap1.put("cabinBookParms", "BF216356179765BE8FF5064BFCBA9DBD41A5D1357837CA9048AA4D986CF8D868279424D90C3324CBF9BA1FFD2B7923D77C64836E84037DAE203E807BD7BF5184128DA9A74913A9529A8FF9A36E7AFDEE5578C98E2BCFF6E18FD2B86B4AA7F3B0B43EFB7B55D2C0C6C84FDE2B30CB08BBC2AF251E226C7E648E924D5F53C61B8AECC1AD4AE72558BBD013C1FF771E22FE02C5050078023C1E4E015E6BB51FCAB5AECA2DFAF56DEC8ABAE6A9703F3422AC4DC93CE58E1A7B6C67747BA4BAE903148D99DAE3227B461A99D26AEA7C3B38FB4101AE7001EFCED1C7F02A6C94A1741136B469E659636B43177BD890212253F47FDFF3BE6678733F72DD4193F8EE5A023C37587D0E012C73385BD64D12A9E9E4D4F4B5E13492BC344F35BC5FE67DD29AE2F22B358106FED8D533A4BFE612C2D440829C0CE0A8F3CD46C0F21431DE242D11BE70BEFEF0B0F033F980ACBF4AF06EAEEA1FD47C960EB5BB154A5F1B98B5A2EB64BC5C6FEC16E99F347058C0EE80543CFC7357ACD32EE705DEF3DF371EB3AA5BB7AC813D0E7BA9C50DBDEF46E261EAA1BD62D67689892670A7F3DCC244ECBB9ACC2EE72BA192868768EA7C276847BA");
        flightArr.add(flightMap1);
        
        dataMap.put("flights", flightArr);

        ArrayList<Object> customerArr = new ArrayList<Object>();
        Map<String, Object> customerMap1 = new HashMap<String, Object>();
        customerMap1.put("name", "石竟革");
        customerMap1.put("cardType", 1);
        customerMap1.put("cardNo", "511823198401101378");
        customerMap1.put("mobile", "17656175477");
        customerMap1.put("birthday", "1995-10-02");
        customerMap1.put("ticketType", 1);
        customerArr.add(customerMap1);
        
        dataMap.put("passengers", customerArr);

        JSONObject dataJson = new JSONObject(dataMap);
    	
        bookData = dataJson;
        
    	// 调用订票接口
    	JSONObject bookResultTicket = new DemoNet().bookTicket(bookData);
    	String orderNo = bookResultTicket.getJSONObject("data").getString("orderNo");
    	DemoOrder demoOrder = new DemoOrder(orderNo, "true");
    	DemoData.OrderBeanMap.put(orderNo, demoOrder);
    	
    	// 预定
    	try {
    		this.asyncService = ApplicationContextProvider.getBean(AsyncService.class);
			asyncService.bookAsync(bookData, bookResultTicket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    }
    
   
    
    
    
    
}
