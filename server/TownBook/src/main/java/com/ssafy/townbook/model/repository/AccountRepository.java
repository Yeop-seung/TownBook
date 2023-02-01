package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByAccountEmail(String account);

    /**
     * 핸드폰 번호로 account 가져오기
     *
     * @param accountPhoneNumber
     * @return Optional<Account>
     */
    Optional<Account> findByAccountPhoneNumber(String accountPhoneNumber);

    /**
     * 이메일로 account 가져오기
     *
     * @param accountEmail
     * @return Optional<Account>
     */
    Optional<Account> findByAccountEmail(String accountEmail);

    /**
     * 회원 넘버로 account 가져오기
     *
     * @param accountNo
     * @return Optional<Account>
     */
    Optional<Account> findByAccountNo(Long accountNo);


    /**
     * 유저의 기부한 책 수 역순으로 유저 랭킹 가져오기
     *
     * @return Optional<List<Account>>
     */
    Optional<List<Account>> findAccountByAccountActivatedOrderByAccountBookCntDesc(Boolean activated);
}
