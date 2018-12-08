package com.cafe24.websample;


import com.cafe24.websample.domain.account.Account;
import com.cafe24.websample.web.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account silverhyuk = authService.createAccount("eunhyuk", passwordEncoder.encode("1234"));
        System.out.println(silverhyuk.getUsername() + " password: "+ silverhyuk.getPassword());
    }
}
