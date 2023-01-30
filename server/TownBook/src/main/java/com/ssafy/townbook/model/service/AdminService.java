package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AdminDto;
import java.util.List;

public interface AdminService {
    
    /**
     * 전체 회원 조회
     * DTO로 변환하여 반환한다.
     *
     * @return List
     */
    List<AdminDto> findAll();
    
    /**
     * 회원번호로 단일 회원을 조회
     * DTO로 변환하여 반환한다.
     *
     * @param accountNo
     * @return AdminDto
     */
    AdminDto findAccountByAccountNo(Long accountNo);
}
