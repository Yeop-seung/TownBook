package com.ssafy.TownBook.model.repository;

import com.ssafy.TownBook.model.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
