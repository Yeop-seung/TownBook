package hide information.townbook.model.service;

import hide information.townbook.model.dto.AccountDto;
import hide information.townbook.model.dto.request.ModifyAccountRequestDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.dto.response.SaveOneResponseDto;
import net.minidev.json.JSONArray;

public interface AccountService {
    
    SaveOneResponseDto signup(AccountDto accountDto);
    
    AccountDto getUserWithAuthorities(String accountId);
    
    AccountDto getMyUserWithAuthorities();
    
    FindOneResponseDto findEmail(String accountPhoneNumber);
    
    SaveOneResponseDto accountModify(ModifyAccountRequestDto modifyAccountRequestDto);
    
    SaveOneResponseDto accountRemove(String accountEmail, String accountPw);
    
    Boolean updatePassword(String accountEmail, String tmpPassword);
    
    FindOneResponseDto findRankAccountBookCnt(Long accountNo) throws Exception;
    
    AccountDto findAccountByAccountEmail(String accountEmail);
    
}
