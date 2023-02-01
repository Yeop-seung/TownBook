package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @GetMapping("/{word}")
    public ResponseEntity<?> findBoardByBoardStateAndBoardTitleLike(@PathVariable String word) throws Exception {
        return new ResponseEntity<>(boardService.findBoardByBoardStateAndBoardTitleLike(word), HttpStatus.OK);
    }
}
