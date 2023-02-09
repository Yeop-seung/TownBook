package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.request.SaveFileRequestDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 파일 업로드&업데이트
     *
     * @param saveFileRequestDto
     * @param multipartFile
     * @return Boolean
     * @throws Exception
     */
    @Transactional
    @PostMapping
    public ResponseEntity<SaveOneResponseDto> fileSave(@RequestPart SaveFileRequestDto saveFileRequestDto, @RequestPart MultipartFile multipartFile) throws Exception {
        return new ResponseEntity<SaveOneResponseDto>(fileService.fileSave(saveFileRequestDto, multipartFile), HttpStatus.OK);
    }

    /**
     * 회원번호로 파일 찾기
     * 
     * @param accountNo
     * @return
     * @throws Exception
     */
    @Transactional
    @GetMapping("/{accountNo}")
    public ResponseEntity<SaveOneResponseDto> findFileByAccountNo(@PathVariable Long accountNo) throws Exception{
        return new ResponseEntity<SaveOneResponseDto>(fileService.findFileByAccountNo(accountNo),HttpStatus.OK);
    }
}
