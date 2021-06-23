package com.ticketsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.model.User;
import com.ticketsystem.net.book.BookHttpClient;
import com.ticketsystem.net.query.QueryHttpClient;
import com.ticketsystem.workflow.WorkFlowAction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "网络接口案例", tags = "网络接口")
@RestController
@MapperScan("com.ticketsystem.dao")
@RequestMapping("/netdemo")
public class NetDomeController {
	
	@Autowired
    private static String orderNo="";
	
	@ApiOperation(value = "查询", notes = "查询接口")
    @RequestMapping("/query")
    @ResponseBody
    public String query(@RequestBody Map<String, String> param, HttpServletRequest request) {
        String username = param.get("username");
        String password = param.get("password");
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        System.out.println(username);
        System.out.println(password);
        String urlStr = "http://api.panhe.net/flight/query?appKey=f46a96420331ea3be28eaf1036af4252&fromCityCode=PKX&toCityCode=CTU&fromDate=2021-07-23&sign=243c69ffa789b404b1041d728f7e6bf4";
        String requestType = "GET";
        String dataStr = "";
        //QueryHttpClient.doPostOrGet(urlStr, requestType, dataStr);
        
        new WorkFlowAction().add();
        
        return "success";
    }
	
	@ApiOperation(value = "预定", notes = "预定接口")
    @RequestMapping("/book")
    @ResponseBody
    public String book(@RequestBody Map<String, String> param, HttpServletRequest request) {
        String username = param.get("username");
        String password = param.get("password");
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        System.out.println(username);
        System.out.println(password);
        String urlStr = "http://api.panhe.net/flight/createOrder";
        String requestType = "POST";
        
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
        flightMap1.put("flightNo", "KN5215");
        flightMap1.put("fromAirportCode", "PKX");
        flightMap1.put("toAirportCode", "CTU");
        flightMap1.put("fromDateTime", "2021-07-23 07:00");
        flightMap1.put("toDateTime", "2021-07-23 09:55");
        flightMap1.put("fromTerminal", "");
        flightMap1.put("toTerminal", "T2");
        flightMap1.put("isShareFlight", false);
        flightMap1.put("shareFlightNo", null);
        flightMap1.put("cabinCode", "V");
        flightMap1.put("cabinBookParms", "BF216356179765BE8FF5064BFCBA9DBD41A5D1357837CA9048AA4D986CF8D868279424D90C3324CBF9BA1FFD2B7923D77C64836E84037DAE203E807BD7BF5184128DA9A74913A9529A8FF9A36E7AFDEE5578C98E2BCFF6E18FD2B86B4AA7F3B0B43EFB7B55D2C0C6C84FDE2B30CB08BBC2AF251E226C7E648E924D5F53C61B8AECC1AD4AE72558BBD013C1FF771E22FE02C5050078023C1E4E015E6BB51FCAB5D0D07D916AE1BEE9CDFBEEDC4D82792BA53C94AF12FB5FB007BA3B763F528C45684178D5183AD5126300B01CDF1B2E1642C370C0A9944F2A4E050FD78F263D95AFB7D46B2FDC23434D785A08DC979A87AB8C57D89C96ED8B28E3A41D0EC30E2EB7B5723C93180E25682F4C56CA3415336B18643266A4CE3CE291201F78B941F0403385064B61005A388C862045EB50933EB0C39F9BC3651E6A15A1B62A00D96B677734B2FBC9BD7859E0FACCEC1A2585AC8E1313FFA3DA7D1F41DBF0F5D74E8F42C2F66A21F29BC540C5E087985E958B7B034949607081D875BA4DCBB3C454EACCD4A2F031ACC90028442339E6A6D524449C5A355C494AB55E397A85E673E75555BF2010751AB01F");
        flightArr.add(flightMap1);
        
        dataMap.put("flights", flightArr);

        ArrayList<Object> customerArr = new ArrayList<Object>();
        Map<String, Object> customerMap1 = new HashMap<String, Object>();
        customerMap1.put("name", "郑继青");
        customerMap1.put("cardType", 1);
        customerMap1.put("cardNo", "410725199510022430");
        customerMap1.put("mobile", "17656175477");
        customerMap1.put("birthday", "1995-10-02");
        customerMap1.put("ticketType", 1);
        customerArr.add(customerMap1);
        
        dataMap.put("passengers", customerArr);

        JSONObject dataJson = new JSONObject(dataMap);
        
        orderNo = BookHttpClient.doPostOrGet(urlStr, requestType, dataJson);
        
        return "success";
    }
	
	@ApiOperation(value = "取消", notes = "取消接口")
    @RequestMapping("/cancel")
    @ResponseBody
    public String cancel(@RequestBody Map<String, String> param, HttpServletRequest request) {
        String username = param.get("username");
        String password = param.get("password");
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        System.out.println(username);
        System.out.println(password);
        String urlStr = "http://api.panhe.net/flight/cancelOrder?appKey=f46a96420331ea3be28eaf1036af4252&orderNo="+orderNo;
        String requestType = "GET";
        String dataStr = "";
        QueryHttpClient.doPostOrGet(urlStr, requestType, dataStr);
        
        return "success";
    }
}
