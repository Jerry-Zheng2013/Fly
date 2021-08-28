package com.ticketsystem.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.service.FlightService3;
import com.ticketsystem.util.CommUtils;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.KnSqlManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "网络接口案例", tags = "网络接口")
@Controller
@MapperScan("com.ticketsystem.dao")
@RequestMapping("/demo")
public class DomeController {
	
	Logger log = LogManager.getLogger(DomeController.class);

	@ApiOperation(value = "预定", notes = "新增订单")
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public void add(@RequestParam(value = "trip_no",required = true) String tripStr,
                      @RequestParam(value = "flght_no",required = true) String flghtNo,
                      @RequestParam(value = "cabin_code",required = true) String cabinCode,
                      @RequestParam(value = "ticket_number",required = false) String ticketNumber, HttpServletResponse response) throws Exception {
        JSONObject addData = new JSONObject();
        //AVH/PKXSHA/21JUL/D/KN
        tripStr = tripStr.toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        flghtNo = flghtNo.toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        cabinCode = cabinCode.toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        ticketNumber = ticketNumber.toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
		log.info("=====tripStr====="+tripStr);
		System.out.println("=====tripStr====="+tripStr);
		log.info("=====flghtNo====="+flghtNo);
		System.out.println("=====flghtNo====="+flghtNo);
		log.info("=====cabinCode====="+cabinCode);
		System.out.println("=====cabinCode====="+cabinCode);
		log.info("=====ticketNumber====="+ticketNumber);
		System.out.println("=====ticketNumber====="+ticketNumber);
        addData.put("fromCityCode", tripStr.substring(4, 7));
        addData.put("toCityCode", tripStr.substring(7, 10));
        addData.put("ticketNumber", ticketNumber);
        String fromDate = tripStr.substring(11, 16);
        String desDate = DemoData.CURR_YEAR + DemoData.CALENDAR_MAP.get(fromDate.substring(2, 5)) + fromDate.substring(0, 2);
        String desDate2 = CommUtils.stringDateFormate(desDate);
        addData.put("fromDate", desDate2);
        addData.put("tripCode", tripStr);
        addData.put("fightNo", flghtNo);
        addData.put("cabinCode", cabinCode);
        //new DemoService().addTicket(addData);
        new FlightService3().addTicket(addData);

        response.sendRedirect("/flight/allFlightList");
    }
	
	
    @ApiOperation(value = "暂停", notes = "暂停订单")
    @RequestMapping("/cancel")
    @ResponseBody
    public void cancel(@RequestBody Map<String, String> param, HttpServletResponse response) throws Exception {
    	String oiId = param.get("oiId").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        String orderNo = param.get("orderNo").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        String accountNo = param.get("accountNo").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
		log.info("=====oiId====="+oiId);
		System.out.println("=====oiId====="+oiId);
		log.info("=====orderNo====="+orderNo);
		System.out.println("=====orderNo====="+orderNo);
		log.info("=====accountNo====="+accountNo);
		System.out.println("=====accountNo====="+accountNo);
        JSONObject cancelData = new JSONObject();
        cancelData.put("oiId", oiId);
        cancelData.put("orderNo", orderNo);
        cancelData.put("accountNo", accountNo);
        new FlightService3().suspendTicket(cancelData);
        //return "success";
        response.sendRedirect("/flight/allFlightList");
    }
	
	@ApiOperation(value = "启动", notes = "重新预定")
    @RequestMapping("/readd")
    @ResponseBody
    public void readd(@RequestBody Map<String, String> param, HttpServletResponse response) throws Exception {
		//从前端获取到了数据
        String oiId = param.get("oiId").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        String tripStr = param.get("tripCode").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        String flghtNo = param.get("flghtNo").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        String cabinCode = param.get("cabinCode").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
        String ticketNumber = param.get("ticketNumber").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
		log.info("=====oiId====="+oiId);
		System.out.println("=====oiId====="+oiId);
		log.info("=====tripStr====="+tripStr);
		System.out.println("=====tripStr====="+tripStr);
		log.info("=====flghtNo====="+flghtNo);
		System.out.println("=====flghtNo====="+flghtNo);
		log.info("=====cabinCode====="+cabinCode);
		System.out.println("=====cabinCode====="+cabinCode);
		log.info("=====ticketNumber====="+ticketNumber);
		System.out.println("=====ticketNumber====="+ticketNumber);
        if("null"==ticketNumber || ticketNumber == null) { ticketNumber=""; }
        
        //先将原订单更新为“正常结束”
        new KnSqlManager().updateOrderStatus2(oiId, "正常结束", "");
        //重新下单
        JSONObject addData = new JSONObject();
        //AVH/PKXSHA/21JUL/D/KN
        addData.put("fromCityCode", tripStr.substring(4, 7));
        addData.put("toCityCode", tripStr.substring(7, 10));
        addData.put("ticketNumber", ticketNumber);
        String fromDate = tripStr.substring(11, 16);
        String desDate = DemoData.CURR_YEAR + DemoData.CALENDAR_MAP.get(fromDate.substring(2, 5)) + fromDate.substring(0, 2);
        String desDate2 = CommUtils.stringDateFormate(desDate);
        addData.put("fromDate", desDate2);
        addData.put("fightNo", flghtNo);
        addData.put("cabinCode", cabinCode);
        addData.put("tripCode", tripStr);
        //new DemoService().addTicket(addData);
        new FlightService3().addTicket(addData);

        response.sendRedirect("/flight/allFlightList");
    }
	
	@ApiOperation(value = "删除", notes = "删除订单")
    @RequestMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Map<String, String> param, HttpServletResponse response) throws Exception {
		//从前端获取到了数据
        String oiId = param.get("oiId").toUpperCase().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
		log.info("=====oiId====="+oiId);
		System.out.println("=====oiId====="+oiId);
        new FlightService3().deleteOrder(oiId);
        response.sendRedirect("/flight/allFlightList");
    }
	
	@ApiOperation(value = "删除", notes = "删除丢票记录")
    @RequestMapping("/deletelost")
    public void deleteLost(HttpServletResponse response) throws Exception {
		//从前端获取到了数据
		log.info("删除丢票记录");
		System.out.println("删除丢票记录");
		new FlightService3().deleteLost();
    }
    
    @ApiOperation(value = "获取", notes = "获取丢票记录")
    @RequestMapping("/getlost")
    @ResponseBody
    public String getlost(HttpServletResponse response) throws Exception {
    	JSONObject lostJson = new FlightService3().getLost();
		String resultStr = lostJson.getString("resultStr");
		return resultStr;
    }
	
	
	
}
