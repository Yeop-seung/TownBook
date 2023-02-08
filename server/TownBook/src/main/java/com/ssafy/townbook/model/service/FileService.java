package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.request.SaveFileRequestDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;

public interface FileService {

    SaveOneResponseDto<Boolean> fileSave(SaveFileRequestDto saveFileRequestDto) throws Exception;
}
