package com.briansjavablog.mtom.endpoint;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import com.blog.samples.webservices.accountservice.AccountDetailsResponse;
import com.blog.samples.webservices.accountservice.EnumAccountStatus;
import com.blog.samples.webservices.accountservice.ObjectFactory;
import com.briansjavablog.accounts.Accounts;
import com.briansjavablog.mtom.Application;
import com.briansjavablog.mtom.config.ClientConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class, ClientConfig.class })
public class AccountServiceEndpointTest {

	@Autowired
	@Qualifier("accountServiceClient")
	private Accounts accountsServiceClient;
	private AccountDetailsRequest accountDetailsRequest;

	@Before
	public void setUp() throws Exception {

		ObjectFactory objectFactory = new ObjectFactory();
		accountDetailsRequest = objectFactory.createAccountDetailsRequest();
		accountDetailsRequest.setAccountNumber("12345");
	}

	@Test
	public void testGetAccountDetails() throws Exception {

		AccountDetailsResponse response = accountsServiceClient.getAccountDetails(accountDetailsRequest);
		assertTrue(response.getAccountDetails() != null);
		assertTrue(response.getAccountDetails().getAccountNumber().equals("12345"));
		assertTrue(response.getAccountDetails().getAccountName().equals("Joe Bloggs"));
		assertTrue(response.getAccountDetails().getAccountBalance() == 3400);
		assertTrue(response.getAccountDetails().getAccountStatus().equals(EnumAccountStatus.ACTIVE));
		assertThat(response.getAccountDetails().getStatement().getContentType(), equalTo("application/pdf"));
		InputStream is = response.getAccountDetails().getStatement().getInputStream();
		OutputStream os = new FileOutputStream(new File("AccountStatement.pdf"));

		IOUtils.copy(is, os);
		IOUtils.closeQuietly(os);
		IOUtils.closeQuietly(is);
	}

}