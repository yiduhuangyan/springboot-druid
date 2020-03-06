package com.cn.zjh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cn.zjh.dao.mapper.CustomerProfileMapper;

public class BasicServiceImpl {

	@Autowired
	private CustomerProfileMapper customerProfileMapper;

	public CustomerProfileMapper getCustomerProfileMapper() {
		return customerProfileMapper;
	}

}
