package hide information.townbook.model.repository;

import hide information.townbook.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    /**
     * 회원번호로 파일 찾기
     * 
     *
     * @param accountNo
     * @return Optional<File>
     */
    Optional<File> findByAccountNo(Long accountNo);

}
