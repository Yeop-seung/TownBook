package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.request.SaveFileRequestDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.entity.File;
import com.ssafy.townbook.model.repository.FileRepository;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public SaveOneResponseDto<Boolean> fileSave(SaveFileRequestDto saveFileRequestDto) throws Exception {
        File file = saveFileRequestDto.toEntity();
        // 파일 저장해볼 차례
fileRepository.save(file);
        return null;
    }
}
