package com.ssafy.townbook.model.dto.request;

import com.ssafy.townbook.model.entity.File;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequestDto {

        private Long id;                    //id

        private String fileOriginName;      //원본 파일명

        private String fileSavedName;       //저장된 파일명

        private String filePath;           //경로명

        private String fileExtension;           //확장자

        private Long fileSize;                  //파일 사이즈

        private String fileContentType;         //ContentType

        private  Long accountNo;

        public FileRequestDto(){

        }

        @Builder
        public FileRequestDto(Long id, String fileOriginName, String fileSavedName, String filePath
                , String fileExtension, Long fileSize, String fileContentType, Long accountNo){
            this.id = id;
            this.fileOriginName = fileOriginName;
            this.fileSavedName = fileSavedName;
            this.filePath = filePath;
            this.fileExtension = fileExtension;
            this.fileSize = fileSize;
            this.fileContentType = fileContentType;
            this.accountNo = accountNo;
        }

        public File toEntity(){
            return File.builder()
                    .fileOriginName(fileOriginName)
                    .fileSavedName(fileSavedName)
                    .filePath(filePath)
                    .fileExtension(fileExtension)
                    .fileSize(fileSize)
                    .fileContentType(fileContentType)
                    .accountNo(accountNo)
                    .build();
        }

    }