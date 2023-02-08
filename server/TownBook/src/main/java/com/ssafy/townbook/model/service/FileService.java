package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.request.SaveFileRequestDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 파일 업로드&업데이트
     *
     * @param saveFileRequestDto
     * @param multipartFile
     * @return Boolean
     * @throws Exception
     */
    SaveOneResponseDto fileSave(SaveFileRequestDto saveFileRequestDto, MultipartFile multipartFile) throws Exception;
}
