package com.briansjavablog.mtom.config;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.briansjavablog.accounts.Accounts;

@Configuration
public class ClientConfig {
	
	@Bean
	@Qualifier("accountServiceClient")
	public Accounts accountServiceClient(LoggingInInterceptor inInterceptor, 
										 LoggingOutInterceptor outInterceptor){
		
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setServiceClass(Accounts.class);
		jaxWsProxyFactoryBean.setAddress("http://localhost:8080/mtom-demo/service");
		return (Accounts)jaxWsProxyFactoryBean.create();
	}
	    
}
