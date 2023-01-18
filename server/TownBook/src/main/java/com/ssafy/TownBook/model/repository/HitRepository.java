package com.ssafy.TownBook.model.repository;

import com.ssafy.TownBook.model.domain.Hit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitRepository extends JpaRepository<Hit,Long> {
}
