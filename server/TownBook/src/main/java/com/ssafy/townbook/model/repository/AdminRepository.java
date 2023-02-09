package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Account, Long> {
    
    /**
     * 회원번호로 단일 회원을 조회
     *
     * @param accountNo
     * @return Optional<Account>
     */
    Optional<Account> findAccountByAccountNo(Long accountNo);
}
