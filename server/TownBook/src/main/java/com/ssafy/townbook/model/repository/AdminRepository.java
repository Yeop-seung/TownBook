package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Account, Long> {
    
    /**
     * 회원번호로 단일 회원을 조회
     * DTO로 변환하여 반환한다.
     *
     * @param accountNo
     * @return AdminDto
     */
    Account findAccountByAccountNo(Long accountNo);
}
