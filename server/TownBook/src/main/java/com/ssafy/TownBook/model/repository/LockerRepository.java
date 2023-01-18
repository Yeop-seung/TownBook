package com.ssafy.TownBook.model.repository;
import com.ssafy.TownBook.model.domain.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker, Long> {

}
