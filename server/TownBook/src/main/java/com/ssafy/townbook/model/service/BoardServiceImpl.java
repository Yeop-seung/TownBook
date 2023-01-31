package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.entity.Board;
import com.ssafy.townbook.model.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;


}
