package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.repository.AccountRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
// getPrincipal()를 하게 되면 UserDetails를 구현한 loadUserByUsername에서 반홚란 User 객체를 받는다.
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    // accountId를 받아서 account를 데이터베이스에서 찾는다.
    // 찾은 권한이 활성화 되어있다면
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String accountEmail) {
        return accountRepository.findOneWithAuthoritiesByAccountEmail(accountEmail)
                .map(account -> createUser(accountEmail, account))
                .orElseThrow(() -> new UsernameNotFoundException(accountEmail + " -> 데이터베이스에서 찾을 수 없습니다."));
    }
    
    // accountId를 가지고 활성화되어있지 않다면 exception
    // account로 부터
    private org.springframework.security.core.userdetails.User createUser(String accountEmail, Account account) {
        // 할성화 되어 있지 않아있을 경우
        if (!account.getAccountActivated()) {
            throw new RuntimeException(accountEmail + " -> 활성화되어 있지 않습니다.");
        }
        
        List<GrantedAuthority> grantedAuthorities = account.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        // 해당 이메일을 가지고 UserDetails 타입의 User를 생성해주어서 반환한다. (들어있는 속성은 이메일, 패스워드, 권한)
        return new org.springframework.security.core.userdetails.User(account.getAccountEmail(),
                account.getAccountPw(),
                grantedAuthorities);
    }
}
