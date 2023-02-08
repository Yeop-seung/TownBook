package com.ssafy.townbook.model.dto.request;

import com.ssafy.townbook.model.entity.File;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Getter
@Setter
public class SaveFileRequestDto {

    private SessionFactory sessionFactory;

    private Long accountNo;

    private MultipartFile multipartFile;


    @Builder
    public SaveFileRequestDto(SessionFactory sessionFactory, Long accountNo, MultipartFile multipartFile) {
        this.sessionFactory = sessionFactory;
        this.accountNo = accountNo;
        this.multipartFile = multipartFile;
    }

    public File toEntity() throws IOException {
        if (multipartFile == null) return null;
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

        return File.builder()
                .fileOriginName(multipartFile.getOriginalFilename())
                .fileSavedName(UUID.randomUUID() + extension)
                .fileExtension(extension)
                .fileSize(multipartFile.getSize())
                .fileContentType(multipartFile.getContentType())
                .accountNo(accountNo)
                .fileMultipartFile(sessionFactory.getCurrentSession().getLobHelper().createBlob(multipartFile.getInputStream(), multipartFile.getSize()))
                .build();
    }

}