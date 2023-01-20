package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AccountDto;
import com.ssafy.townbook.model.entity.Account;

public interface AccountService {
    AccountDto signup(AccountDto accountDto);

    AccountDto getUserWithAuthorities(String accountId);

    AccountDto getMyUserWithAuthorities();

}
