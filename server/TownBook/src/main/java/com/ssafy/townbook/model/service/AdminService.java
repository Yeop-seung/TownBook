package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;

public interface AdminService {
    
    /**
     * 전체 회원 조회
     *
     * @return List<AccountDto>
     */
    FindListResponseDto findAll();
    
    /**
     * 회원번호로 단일 회원을 조회
     *
     * @param accountNo
     * @return AccountDto
     */
    FindOneResponseDto findAccountByAccountNo(Long accountNo);
}
