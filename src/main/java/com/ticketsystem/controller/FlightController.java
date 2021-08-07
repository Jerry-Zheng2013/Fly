package com.ticketsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.model.Flight;
import com.ticketsystem.model.User;
import com.ticketsystem.model.OrderInfo;
import com.ticketsystem.service.DemoService;
import com.ticketsystem.service.FlightService;
import com.ticketsystem.service.OrderInfoService;
import com.ticketsystem.util.DemoData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Api(value = "航班操作", tags = "航班操作信息接口")
@Controller
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String page(ModelMap modelMap){
        return "dashboard";
    }
    
    @ApiOperation(value = "获取所有订单信息", notes = "")
    @RequestMapping(value = "/allFlightList",method = RequestMethod.GET)
    public String getAllFlight(ModelMap modelMap) {
    	List<OrderInfo> orderInfoList = orderInfoService.getOrderInfoList();
    	modelMap.put("orderInfoList", orderInfoList);
        return "flightTicket";
    }
    
    @ApiOperation(value = "获取历史订单信息", notes = "")
    @RequestMapping(value = "/historyFlightList",method = RequestMethod.GET)
    public String getHistoryFlight(ModelMap modelMap) {
    	List<OrderInfo> orderInfoList = orderInfoService.getHistoryList();
    	modelMap.put("orderInfoList", orderInfoList);
        return "historyTicket";
    }

    @ApiOperation(value = "获取航班ID", notes = "")
    @RequestMapping("flightId")
    public Flight getFlightById(@RequestBody Map<String, String> map) {
        int flightId = Integer.parseInt(map.get("flightId"));
        return flightService.getFlightById(flightId);
    }

    @ApiOperation(value = "获取城市信息", notes = "可以根据起飞城市或者到达城市的任一条件或者两者同时作为查询条件")
    @RequestMapping("city")
    public List<Flight> getByCity(@RequestBody JSONObject jsonObject) {
        return flightService.queryByCity(jsonObject.getString("startCity"), jsonObject.getString("endCity"));
    }

    @ApiOperation(value = "修改航班信息", notes = "管理员权限接口，管理员权限下的航班信息修改功能")
    @RequestMapping("altFlight")
    public void altFlight(@RequestBody JSONObject jsonObject) {
        Flight flight = JSONObject.parseObject(jsonObject.toJSONString(), Flight.class);
        flightService.altFlight(flight);
    }

    @ApiOperation(value = "删除航班", notes = "管理员权限接口，管理员权限下的航班删除功能，根据航班ID进行删除")
    @RequestMapping("deleteById")
    public void delFlight(@RequestBody JSONObject jsonObject) {
        int id = jsonObject.getInteger("flightId");
        flightService.delFlight(id);
    }

	/*
	 * @ApiOperation(value = "增加航班", notes = "管理员权限接口，管理员权限下的航班增加功能")
	 * 
	 * @RequestMapping("add") public void addFlight(@RequestBody JSONObject
	 * jsonObject) { Flight flight =
	 * JSONObject.parseObject(jsonObject.toJSONString(), Flight.class);
	 * flightService.addFlight(flight); }
	 */
    
    @ApiOperation(value = "预定", notes = "新增订单")
    @RequestMapping("/addFlight")
    @ResponseBody
    public Object add(@RequestParam(value = "tripStr",required = true) String tripStr,
                      @RequestParam(value = "flghtNo",required = true) String flghtNo,
                      @RequestParam(value = "cabinCode",required = true) String cabinCode) {
    	Map<String, Object> dataMap1 = new HashMap<String, Object>();
        dataMap1.put("tripStr", tripStr);//AVH/PKXCTU/20210723/D/KN
        dataMap1.put("flghtNo", flghtNo);//KN0000
        dataMap1.put("cabinCode", cabinCode);//C
        JSONObject dataJson1 = new JSONObject(dataMap1);
        new DemoService().booking(dataJson1);
        return "success";
    }
}
