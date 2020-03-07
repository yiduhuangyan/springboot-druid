package com.cn.zjh;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@SpringBootApplication
@MapperScan("com.cn.zjh.dao.mapper")
public class StarterApplication {

	@Value("${spring.datasource.druid.stat-view-servlet.loginUsername}")
	public String loginUserName;
	
	@Value("${spring.datasource.druid.stat-view-servlet.loginPassword}")
	public String loginPassword;
	
	public String getLoginUserName() {
		return loginUserName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public static void main(String[] args) {
		SpringApplication.run(StarterApplication.class, args);
	}
	
	//手动初始化DruidDataSource 对象
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druid() {
		DruidDataSource ds=new DruidDataSource();
		return ds;
	}
	
	//注册后台界面servlet
	@Bean
	public ServletRegistrationBean statViewServlet() {
		//创建statViewServlet，绑定到/druid/路径下
		//开启后，访问localhost:8080/druid就可以看到后台
		ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		//设置参数
		Map<String, String> param=new HashMap<String,String>();
		//设置登录的用户名
		param.put("loginUsername", loginUserName);
		//设置登录的密码
		param.put("loginPassword", loginPassword);
		//哪些ip允许访问后台，""代表所有地址
		param.put("allow", "");
		//不允许这个IP访问
		param.put("deny", "192.168.0.105");
		bean.setInitParameters(param);
		return bean;
		
	}
	
	//用于监听获取应用的数据，Fliter用于收集数据，Servlet用于展现数据
	@Bean
	public FilterRegistrationBean webStatFilter() {

		FilterRegistrationBean bean = new FilterRegistrationBean();
		//设置过滤器
		bean.setFilter(new WebStatFilter());
		bean.addUrlPatterns("/*");
		Map<String, String> param = new HashMap<String, String>();
		//排除静态资源
		param.put("exclusions", "*.png,*.woff,*.js,*.css,/druid/*/");
		bean.setInitParameters(param);
		return bean;
	}
}
