package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;

public interface AdminService {
    
    /**
     * 전체 회원 조회
     *
     * @return List<AccountDto>
     */
    FindListResponseDto findAllAccounts();
    
    /**
     * 회원번호로 단일 회원을 조회
     *
     * @param accountNo
     * @return AccountDto
     */
    FindOneResponseDto findAccountByAccountNo(Long accountNo);

    /**
     * 특정 회원에게 포인트 부여
     *
     * @param accountNo
     * @return Integer
     */
    FindOneResponseDto givePointOneUser(Long accountNo, Integer point);
}
