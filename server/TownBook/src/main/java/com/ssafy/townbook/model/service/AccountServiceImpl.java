package com.ssafy.townbook.model.service;

import com.ssafy.townbook.exception.DuplicateMemberException;
import com.ssafy.townbook.exception.NotFoundMemberException;
import com.ssafy.townbook.model.dto.AccountDto;
import com.ssafy.townbook.model.dto.AdminDto;
import com.ssafy.townbook.model.dto.request.ModifyAccountRequestDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.ModifyAccountResponseDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Authority;
import com.ssafy.townbook.model.repository.AccountRepository;
import com.ssafy.townbook.util.SecurityUtil;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    
    private AccountRepository accountRepository;
    private PasswordEncoder   passwordEncoder;
    
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder   = passwordEncoder;
    }
    
    /**
     * 회원 가입
     *
     * @param accountDto
     * @return
     */
    @Override
    @Transactional
    public SaveOneResponseDto signup(AccountDto accountDto) {
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
                .accountBirthDay(accountDto.getAccountBirthDay())
                .accountNickname(accountDto.getAccountNickname())
                .accountEmail(accountDto.getAccountEmail())
                .accountGender(accountDto.getAccountGender())
                .authorities(Collections.singleton(authority))
                .accountActivated(true)
                .build();
        accountRepository.save(account);
        account = accountRepository.findByAccountEmail(accountDto.getAccountEmail()).get();
        return new SaveOneResponseDto(true);
    }
    
    // 보류
    @Override
    @Transactional(readOnly = true)
    public AccountDto getUserWithAuthorities(String accountEmail) {
        return AccountDto.from(accountRepository.findOneWithAuthoritiesByAccountEmail(accountEmail).orElse(null));
    }
    
    // 보류
    @Override
    @Transactional(readOnly = true)
    public AccountDto getMyUserWithAuthorities() {
        return AccountDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(accountRepository::findOneWithAuthoritiesByAccountEmail)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
    
    /**
     * 핸드폰 번호로 이메일 찾기
     *
     * @param accountPhoneNumber
     * @return
     */
    @Override
    public FindOneResponseDto findEmail(String accountPhoneNumber) {
        
        try {
            Account account = accountRepository.findByAccountPhoneNumber(accountPhoneNumber).get();
            return new FindOneResponseDto(account.getAccountEmail());
        } catch (Exception exception) {
            exception.getMessage();
            return new FindOneResponseDto();
        }
    }
    
    /**
     * 계정 수정하기
     *
     * @param modifyAccountRequestDto
     * @return
     */
    @Override
    public SaveOneResponseDto accountModify(ModifyAccountRequestDto modifyAccountRequestDto) {
        try {
            Account account = accountRepository.findByAccountNo(modifyAccountRequestDto.getAccountNo())
                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
            account.setAccountAddress(modifyAccountRequestDto.getAccountAddress());
            account.setAccountPhoneNumber(modifyAccountRequestDto.getAccountPhoneNumber());
            account.setAccountBirthDay(modifyAccountRequestDto.getAccountBirthDay());
            account.setAccountNickname(modifyAccountRequestDto.getAccountNickname());
            accountRepository.save(account);
            
            ModifyAccountResponseDto modifyAccountResponseDto = new ModifyAccountResponseDto(account);
            return new SaveOneResponseDto(modifyAccountResponseDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new SaveOneResponseDto();
        }
    }
    
    /**
     * 계정비활성화
     *
     * @param accountEmail
     * @param accountPw
     * @return
     */
    @Override
    public SaveOneResponseDto accountRemove(String accountEmail, String accountPw) {
        try {
            Account account = accountRepository.findByAccountEmail(accountEmail).orElseThrow(() ->
                    new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
            try {
                if (passwordEncoder.matches(accountPw, account.getAccountPw())) {
                    account.setAccountActivated(false);
                    accountRepository.save(account);
                    return new SaveOneResponseDto(true);
                } else {
                    throw new IllegalArgumentException("해당 비밀번호가 맞지 않습니다.");
                }
            } catch (Exception e) {
                e.getMessage();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new SaveOneResponseDto();
        }
        return new SaveOneResponseDto();
    }
    
    
    /**
     * 비밀 번호 찾기(임시비밀번호로 바꾸기)
     *
     * @param accountEmail
     * @param tmpPassword
     */
    @Override
    public Boolean updatePassword(String accountEmail, String tmpPassword) {
        String encryptPassword = passwordEncoder.encode(tmpPassword);
        try {
            Account account = accountRepository.findByAccountEmail(accountEmail).orElseThrow(() ->
                    new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
            account.setAccountPw(encryptPassword);
            accountRepository.save(account);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * 유저 랭킹 TOP10과 자신의 랭킹 조회
     *
     * @param accountNo
     * @return JSONArrays
     * @throws Exception
     */
    public FindOneResponseDto findRankAccountBookCnt(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        List<AdminDto> accounts = accountRepository.findAccountByAccountActivatedOrderByAccountBookCntDesc(true).get()
                .stream()
                .map(AdminDto::new)
                .collect(Collectors.toList());
        
        int size = 10;
        int rank = 1;
        int cnt  = -1;
        
        // 유저가 존재할 경우
        if (accounts.size() != 0) {
            cnt = accounts.get(0).getAccountBookCnt();
        }
        
        // TOP10이 아닌경우 범위 재설정
        if (accounts.size() < 10) {
            size = accounts.size();
        }
        
        // 내 랭킹 찾아서 저장할 변수
        String nickName  = "";
        int    myRank    = 0;
        int    bookCount = 0;
        
        // TOP10 찾기
        for (int i = 0; i < size; i++) {
            AdminDto account = accounts.get(i);
            
            if (cnt != account.getAccountBookCnt()) {
                cnt = account.getAccountBookCnt();
                ++rank;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rank", rank);
            jsonObject.put("accountNickname", account.getAccountNickname());
            jsonObject.put("accountBookCnt", account.getAccountBookCnt());
            
            // 내 랭킹 저장
            if (account.getAccountNo().equals(accountNo)) {
                myRank    = rank;
                nickName  = account.getAccountNickname();
                bookCount = account.getAccountBookCnt();
            }
            
            jsonArray.add(jsonObject);
        }
        
        // 내 랭킹
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rank", myRank);
        jsonObject.put("accountNickname", nickName);
        jsonObject.put("accountBookCnt", bookCount);
        jsonArray.add(jsonObject);
        
        return new FindOneResponseDto(jsonArray);
    }
    
    @Override
    public AccountDto findAccountByAccountEmail(String accountEmail) {
        return AccountDto.from(accountRepository.findByAccountEmail(accountEmail).get());
    }
}
