package com.ssafy.TownBook.model.service;

import com.ssafy.TownBook.exception.DuplicateMemberException;
import com.ssafy.TownBook.exception.NotFoundMemberException;
import com.ssafy.TownBook.model.domain.Account;
import com.ssafy.TownBook.model.domain.Authority;
import com.ssafy.TownBook.model.dto.AccountDto;
import com.ssafy.TownBook.model.repository.AccountRepository;
import com.ssafy.TownBook.util.SecurityUtil;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountSerivce {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public AccountDto signup(AccountDto accountDto) {
        if (accountRepository.findOneWithAuthoritiesByAccountId(accountDto.getAccountId()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Account account = Account.builder()
                .accountId(accountDto.getAccountId())
                .accountPw(passwordEncoder.encode(accountDto.getAccountPw()))
                .accountName(accountDto.getAccountName())
                .accountPhoneNumber(accountDto.getAccountPhoneNumber())
                .accountAddress(accountDto.getAccountAddress())
                .accountBirthday(accountDto.getAccountBirthDay())
                .accountNickname(accountDto.getAccountNickname())
                .accountEmail(accountDto.getAccountEmail())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return AccountDto.from(accountRepository.save(account));
    }

    @Transactional(readOnly = true)
    public AccountDto getUserWithAuthorities(String accountId) {
        return AccountDto.from(accountRepository.findOneWithAuthoritiesByAccountId(accountId).orElse(null));
    }

    @Transactional(readOnly = true)
    public AccountDto getMyUserWithAuthorities() {
        return AccountDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(accountRepository::findOneWithAuthoritiesByAccountId)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
}
