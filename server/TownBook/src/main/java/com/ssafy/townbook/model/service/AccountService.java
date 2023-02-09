package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AccountDto;
import com.ssafy.townbook.model.dto.request.ModifyAccountRequestDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import net.minidev.json.JSONArray;

public interface AccountService {
    
    SaveOneResponseDto signup(AccountDto accountDto);
    
    AccountDto getUserWithAuthorities(String accountId);
    
    AccountDto getMyUserWithAuthorities();
    
    FindOneResponseDto findEmail(String accountPhoneNumber);
    
    SaveOneResponseDto accountModify(ModifyAccountRequestDto modifyAccountRequestDto);
    
    SaveOneResponseDto accountRemove(String accountEmail, String accountPw);
    
    Boolean updatePassword(String accountEmail, String tmpPassword);
    
    JSONArray findAccountBookCnt(Long accountNo) throws Exception;

    AccountDto findAccountByAccountEmail(String accountEmail);

}
