package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
   private AccountRepository accountRepository;

   // accountId를 받아서 account를 데이터베이스에서 찾는다.
   // 찾은 권한이 활성화 되어있다면
   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String accountId) {
      return accountRepository.findOneWithAuthoritiesByAccountId(accountId)
         .map(account -> createUser(accountId, account))
         .orElseThrow(() -> new UsernameNotFoundException(accountId + " -> 데이터베이스에서 찾을 수 없습니다."));
   }

   // accountId를 가지고 활성화되어있지 않다면 exception
   // account로 부터
   private org.springframework.security.core.userdetails.User createUser(String accountId, Account account) {
      if (!account.isActivated()) {
         throw new RuntimeException(accountId + " -> 활성화되어 있지 않습니다.");
      }

      List<GrantedAuthority> grantedAuthorities = account.getAuthorities().stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
              .collect(Collectors.toList());

      return new org.springframework.security.core.userdetails.User(account.getAccountId(),
              account.getAccountPw(),
              grantedAuthorities);
   }
}
