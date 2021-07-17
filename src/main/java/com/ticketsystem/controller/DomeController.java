package com.ticketsystem.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.model.User;
import com.ticketsystem.service.DemoService;
import com.ticketsystem.service.FlightService2;
import com.ticketsystem.util.CommUtils;
import com.ticketsystem.util.DemoData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "网络接口案例", tags = "网络接口")
@RestController
@MapperScan("com.ticketsystem.dao")
@RequestMapping("/demo")
public class DomeController {

	@ApiOperation(value = "预定", notes = "新增订单")
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public void add(@RequestParam(value = "trip_no",required = true) String tripStr,
                      @RequestParam(value = "flght_no",required = true) String flghtNo,
                      @RequestParam(value = "cabin_code",required = true) String cabinCode, HttpServletResponse response) throws Exception {
        JSONObject addData = new JSONObject();
        //AVH/PKXSHA/21JUL/D/KN
        addData.put("fromCityCode", tripStr.substring(4, 7));
        addData.put("toCityCode", tripStr.substring(7, 10));
        String fromDate = tripStr.substring(11, 16);
        String desDate = DemoData.CURR_YEAR + DemoData.CALENDAR_MAP.get(fromDate.substring(2, 5)) + fromDate.substring(0, 2);
        String desDate2 = CommUtils.stringDateFormate(desDate);
        addData.put("fromDate", desDate2);
        addData.put("fightNo", flghtNo);
        addData.put("cabinCode", cabinCode);
        addData.put("tripCode", tripStr);
        //new DemoService().addTicket(addData);
        new FlightService2().addTicket(addData);

        response.sendRedirect("/flight/allFlightList");
    }
	
	@ApiOperation(value = "取消", notes = "取消订单")
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
        
        JSONObject cancelData = new JSONObject();
        cancelData.put("oiId", "xxx");
        cancelData.put("orderNo", "FO2106290510525");
        cancelData.put("accountNo", "15083142384");
        //new DemoService().cancelTicket(cancelData);
        new FlightService2().cancelTicket(cancelData);
        
        return "success";
    }
	
	@ApiOperation(value = "启动", notes = "重新预定")
    @RequestMapping("/readd")
    @ResponseBody
    public String readd(@RequestBody Map<String, String> param, HttpServletRequest request) {
		//默认已从前端获取到了数据
        JSONObject inputData = DemoData.getData3();
        JSONObject addData = new JSONObject();
        addData.put("fromCityCode", inputData.getString("tripStr").substring(4, 7));
        addData.put("toCityCode", inputData.getString("tripStr").substring(7, 10));
        String fromDate = inputData.getString("tripStr").substring(11, 19);
    	StringBuffer fromTimeSB = new StringBuffer(fromDate);
    	fromTimeSB.insert(4, "-");
    	fromTimeSB.insert(7, "-");
    	fromDate = fromTimeSB.toString();
        addData.put("fromDate", fromDate);
        addData.put("fightNo", inputData.getString("fightNo"));
        addData.put("cabinCode", inputData.getString("cabinCode"));
        addData.put("tripCode", inputData.getString("tripStr"));
        addData.put("oiId", inputData.getString("oiId"));
        //new DemoService().addTicket(addData);
        new FlightService2().addTicket(addData);
        
        return "success";
    }
}
