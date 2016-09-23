package com.briansjavablog.accounts.service;

import static org.junit.Assert.assertTrue;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import com.blog.samples.webservices.accountservice.AccountDetailsResponse;
import com.blog.samples.webservices.accountservice.EnumAccountStatus;
import com.blog.samples.webservices.accountservice.ObjectFactory;
import com.briansjavablog.accounts.Accounts;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( {"classpath:beans-test.xml"} )
public class AccountServiceEndpointTest {

	//@Autowired
	private JaxWsProxyFactoryBean proxyFactoryBean;
	private Accounts accountsService;
	private AccountDetailsRequest accountDetailsRequest;
	
	@Before
	public void setUp() throws Exception {
		
		/*accountsService = proxyFactoryBean.create(Accounts.class);		
		ObjectFactory objectFactory = new ObjectFactory();
		accountDetailsRequest = objectFactory.createAccountDetailsRequest();
		accountDetailsRequest.setAccountNumber("12345");*/
	}

	@After
	public void tearDown() throws Exception {
	
	
	}

	@Test
	public void testGetAccountDetails() throws Exception {
		Thread.sleep(600000);
		AccountDetailsResponse response = accountsService.getAccountDetails(accountDetailsRequest);
		assertTrue(response.getAccountDetails()!= null);
		assertTrue(response.getAccountDetails().getAccountNumber().equals("12345"));
		assertTrue(response.getAccountDetails().getAccountName().equals("Joe Bloggs"));
		assertTrue(response.getAccountDetails().getAccountBalance() == 3400);
		assertTrue(response.getAccountDetails().getAccountStatus().equals(EnumAccountStatus.ACTIVE));
	}

}