package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
