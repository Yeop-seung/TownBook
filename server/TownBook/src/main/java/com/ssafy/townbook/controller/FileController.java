package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.request.SaveFileRequestDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<SaveOneResponseDto<Boolean>> fileSave(@RequestBody SaveFileRequestDto saveFileRequestDto) throws Exception {
        return new ResponseEntity<SaveOneResponseDto<Boolean>>(fileService.fileSave(saveFileRequestDto), HttpStatus.OK);
    }
}
