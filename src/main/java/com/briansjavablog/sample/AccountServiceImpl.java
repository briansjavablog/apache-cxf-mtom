
package com.briansjavablog.sample;

import javax.jws.WebService;

@WebService(endpointInterface = "com.briansjavablog.sample.AccountService")
public class AccountServiceImpl implements AccountService {

    public void test(String text) {
        //return "Hello " + text;
    }
}

