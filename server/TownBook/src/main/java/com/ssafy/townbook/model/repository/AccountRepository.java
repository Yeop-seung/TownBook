package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByAccountEmail(String account);

    /**
     * 핸드폰 번호로 account 가져오기
     * @param accountPhoneNumber
     * @return
     */
    Account findByAccountPhoneNumber (String accountPhoneNumber);

    /**
     * 이메일로 account 가져오기
     * @param accountEmail
     * @return
     */
    Optional<Account> findByAccountEmail (String accountEmail);

}
