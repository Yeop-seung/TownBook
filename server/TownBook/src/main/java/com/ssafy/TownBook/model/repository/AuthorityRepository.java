package com.ssafy.TownBook.model.repository;

import com.ssafy.TownBook.model.Entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
