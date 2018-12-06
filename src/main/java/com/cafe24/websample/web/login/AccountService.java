package com.cafe24.websample.web.login;

import com.cafe24.websample.domain.account.Account;
import com.cafe24.websample.domain.account.AccountRepository;
import com.cafe24.websample.domain.role.Role;
import com.cafe24.websample.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder ;

    public Account createAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRegDateTime(new Date());
        account.setEmail("tldn23@gmail.com");
        account.setActive(1);
        Role role = createRole("ROLE_ADMIN");
        account.setRoleId(role.getId());
        return accountRepository.save(account);
    }

    private Role createRole(String roleName){
        Role role = new Role();
        role.setRoleName(roleName);
        return roleRepository.save(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> byUsername = accountRepository.findByUsername(username);
        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(account.getUsername(), account.getPassword(), authorities(account.getRoleId()));
    }

/*    private Collection<? extends GrantedAuthority> authorities(Set<Role> roles) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role authority : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getRoleName());
            grantedAuthorities.add(grantedAuthority);
        }
        return grantedAuthorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }*/
   private Collection<? extends GrantedAuthority> authorities(Long roleId) {
       Optional<Role> byId = roleRepository.findById(roleId);
       Role role = byId.orElseThrow(() -> new NullPointerException(roleId.toString()));
        return Arrays.asList(new SimpleGrantedAuthority(role.getRoleName()));
    }
}