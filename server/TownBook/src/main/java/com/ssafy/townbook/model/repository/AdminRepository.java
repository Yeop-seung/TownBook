package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Account, Long> {

    /**
     * 회원번호로 회원 조회
     *
     * @param accountNo
     * @return Optional<Account>
     */
    Optional<Account> findAccountByAccountNo(Long accountNo);

}
