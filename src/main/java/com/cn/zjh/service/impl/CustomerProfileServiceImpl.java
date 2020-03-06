package com.cn.zjh.service.impl;

import org.springframework.stereotype.Service;

import com.cn.zjh.dao.output.CustomerProfileDaoOutput;
import com.cn.zjh.service.CustomerProileService;
import com.cn.zjh.service.output.CustomerProfileOutput;

@Service
public class CustomerProfileServiceImpl extends BasicServiceImpl implements CustomerProileService {

	public CustomerProfileOutput getCustomerProfileInfo() {
		CustomerProfileOutput info = new CustomerProfileOutput();
		CustomerProfileDaoOutput infos = this.getCustomerProfileMapper().getCustomerProfileInfo();
		info.setUserId(infos.getUserId());
		info.setUsername(infos.getUsername());
		info.setPwd(infos.getPwd());
		info.setStatus(infos.getStatus());
		return info;
	}

}
