package com.cafe24.websample;


import com.cafe24.websample.domain.account.Account;
import com.cafe24.websample.web.login.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account silverhyuk = accountService.createAccount("eunhyuk", passwordEncoder.encode("1234"));
        System.out.println(silverhyuk.getUsername() + " password: "+ silverhyuk.getPassword());
    }
}
