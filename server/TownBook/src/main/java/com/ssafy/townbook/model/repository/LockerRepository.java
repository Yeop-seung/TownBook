package hide information.townbook.model.repository;

import hide information.townbook.model.entity.Locker;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker, Long> {
    
    /**
     * 단일 보관함 및 보관함에 할당된 세부 보관함 조회
     *
     * @param lockerNo
     * @return Optional<Locker>
     */
    Optional<Locker> findLockerByLockerNo(Long lockerNo);
}
