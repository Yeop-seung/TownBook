package com.ssafy.townbook.model.service;

import com.ssafy.townbook.exception.DuplicateMemberException;
import com.ssafy.townbook.exception.NotFoundMemberException;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Authority;
import com.ssafy.townbook.model.dto.AccountDto;
import com.ssafy.townbook.model.repository.AccountRepository;
import com.ssafy.townbook.util.SecurityUtil;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AccountDto signup(AccountDto accountDto) {
        if (accountRepository.findOneWithAuthoritiesByAccountEmail(accountDto.getAccountEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Account account = Account.builder()
                .accountPw(passwordEncoder.encode(accountDto.getAccountPw()))
                .accountName(accountDto.getAccountName())
                .accountPhoneNumber(accountDto.getAccountPhoneNumber())
                .accountAddress(accountDto.getAccountAddress())
                .accountBirthday(accountDto.getAccountBirthDay())
                .accountNickname(accountDto.getAccountNickname())
                .accountEmail(accountDto.getAccountEmail())
                .accountGender(accountDto.getAccountGender())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return AccountDto.from(accountRepository.save(account));
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getUserWithAuthorities(String accountEmail) {
        return AccountDto.from(accountRepository.findOneWithAuthoritiesByAccountEmail(accountEmail).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getMyUserWithAuthorities() {
        return AccountDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(accountRepository::findOneWithAuthoritiesByAccountEmail)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }


}
