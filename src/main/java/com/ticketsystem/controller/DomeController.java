package com.ticketsystem.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.model.User;
import com.ticketsystem.net.query.QueryHttpClient;
import com.ticketsystem.service.DemoService;
import com.ticketsystem.util.DemoData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "网络接口案例", tags = "网络接口")
@RestController
@MapperScan("com.ticketsystem.dao")
@RequestMapping("/demo")
public class DomeController {
	
	@ApiOperation(value = "预定", notes = "新增订单")
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody Map<String, String> param, HttpServletRequest request) {
        String username = param.get("username");
        String password = param.get("password");
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        System.out.println(username);
        System.out.println(password);
        JSONObject addData = DemoData.getData1();
        new DemoService().add(addData);
        return "success";
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
        QueryHttpClient.doPostOrGet("http://api.panhe.net/flight/cancelOrder?appKey=f46a96420331ea3be28eaf1036af4252&orderNo=FO2106240156206", "GET", "");
        
        return "success";
    }
	
	@ApiOperation(value = "启动", notes = "重新预定")
    @RequestMapping("/readd")
    @ResponseBody
    public String readd(@RequestBody Map<String, String> param, HttpServletRequest request) {
        //更新一个订单记录中的orderNo,其他跟新增一样（不要将orderNo作为主键，加一个流水号SerialNo做主键）
		// 更新记录
		this.add(param, request);
        return "success";
    }
}
