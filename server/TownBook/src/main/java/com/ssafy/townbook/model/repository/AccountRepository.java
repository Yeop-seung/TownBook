package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByAccountId(String account);


}
