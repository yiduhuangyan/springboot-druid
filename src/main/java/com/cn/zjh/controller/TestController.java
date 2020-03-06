package com.cn.zjh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.zjh.service.CustomerProileService;
import com.cn.zjh.service.output.CustomerProfileOutput;

/**
 * 
 * @author zhujianhua
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private CustomerProileService customerProileService;

	@GetMapping("/profileinfo")
	@ResponseBody
	public CustomerProfileOutput getCustomerOrderInfo() {
		CustomerProfileOutput info = new CustomerProfileOutput();
		info = customerProileService.getCustomerProfileInfo();
		return info;
	}
}
