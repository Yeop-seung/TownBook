package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<List<Board>> findBoardByBoardStateAndBoardTitleLike(Boolean state, String boardTitle) throws Exception;
}
