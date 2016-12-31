package com.briansjavablog.accounts.service;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.samples.webservices.accountservice.Account;
import com.blog.samples.webservices.accountservice.AccountDetailsRequest;
import com.blog.samples.webservices.accountservice.AccountDetailsResponse;
import com.blog.samples.webservices.accountservice.EnumAccountStatus;
import com.blog.samples.webservices.accountservice.ObjectFactory;
import com.sun.istack.ByteArrayDataSource;

@Service
public class AccountServiceImpl implements AccountService {

	private PdfGenerationService pdfGenerationService;
	
	@Autowired
	public AccountServiceImpl(PdfGenerationService pdfGenerationService) {
		this.pdfGenerationService = pdfGenerationService;
	}
	
	@Override
	public AccountDetailsResponse getAccountDetails(AccountDetailsRequest parameters) {

		ObjectFactory factory = new ObjectFactory();
		AccountDetailsResponse response = factory.createAccountDetailsResponse();
		
		Account account = factory.createAccount();
		account.setAccountNumber("12345");
		account.setAccountStatus(EnumAccountStatus.ACTIVE);
		account.setAccountName("Joe Bloggs");
		account.setAccountBalance(3400);
		
		byte[] pdfDocument = pdfGenerationService.generatePdf();		
		DataSource byteArrayDataSource = new ByteArrayDataSource(pdfDocument, "application/pdf");
		account.setStatement(new DataHandler(byteArrayDataSource));
		
		response.setAccountDetails(account);		
		return response;
	}
}