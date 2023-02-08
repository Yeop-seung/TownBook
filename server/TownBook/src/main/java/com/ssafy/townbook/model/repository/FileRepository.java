package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    /**
     * 파일 업로드&업데이트
     *
     * @param accountNo
     * @return Optional<File>
     */
    Optional<File> findByAccountNo(Long accountNo);

}
