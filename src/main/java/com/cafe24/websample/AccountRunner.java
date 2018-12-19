package com.cafe24.websample;


import com.cafe24.websample.domain.account.Account;
import com.cafe24.websample.web.auth.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class AccountRunner implements ApplicationRunner {

    private final CustomUserDetailService customUserDetailService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account silverhyuk = customUserDetailService.createAccount("eunhyuk", passwordEncoder.encode("1234"));
        System.out.println(silverhyuk.getUsername() + " password: "+ silverhyuk.getPassword());
    }
}
