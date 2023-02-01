package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AdminDto;
import java.util.List;

public interface AdminService {
    
    /**
     * 전체 회원 조회
     *
     * @return List<AccountDto>
     */
    List<AdminDto> findAll();
    
    /**
     * 회원번호로 단일 회원을 조회
     *
     * @param accountNo
     * @return AccountDto
     */
    AdminDto findAccountByAccountNo(Long accountNo);
}
