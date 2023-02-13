package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList,Long> {

    Optional<WishList> findByBookLogNo(Long bookLogNo)throws Exception;
}
