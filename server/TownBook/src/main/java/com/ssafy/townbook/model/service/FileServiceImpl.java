package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.request.SaveFileRequestDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.entity.File;
import com.ssafy.townbook.model.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Blob;
import java.util.Optional;


@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private EntityManagerFactory entityManagerFactory;

    private FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(EntityManagerFactory entityManagerFactory, FileRepository fileRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.fileRepository = fileRepository;
    }

    /**
     * 파일 업로드&업데이트
     *
     * @param saveFileRequestDto
     * @param multipartFile
     * @return Boolean
     */
    @Override
    public SaveOneResponseDto fileSave(SaveFileRequestDto saveFileRequestDto, MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty()) {
                throw new Exception("파일이 등록되어있지 않습니다");
            }

            EntityManager em = entityManagerFactory.createEntityManager();
            Session session = (Session) em.getDelegate();

            File file;
            Optional<File> fileOptional = fileRepository.findByAccountNo(saveFileRequestDto.getAccountNo());

            if (fileOptional.isEmpty()) {
                file = saveFileRequestDto.toEntity(Hibernate.getLobCreator(session).createBlob(multipartFile.getInputStream(), multipartFile.getSize()));
            } else {
                file = fileOptional.get();
                file.setFileOriginName(saveFileRequestDto.getFileOriginName());
                file.setFileMultipartFile(Hibernate.getLobCreator(session).createBlob(multipartFile.getInputStream(), multipartFile.getSize()));
            }

            fileRepository.save(file);

            return new SaveOneResponseDto(true);
        } catch (Exception e) {
            return new SaveOneResponseDto(e.getMessage());
        }
    }

    /**
     * 회원번호로 파일 찾기
     *
     * @param accountNo
     * @return Byte[]
     * @throws Exception
     */
    @Override
    public SaveOneResponseDto findFileByAccountNo(Long accountNo) throws Exception {
        try {
            File file;
            Optional<File> fileOptional = fileRepository.findByAccountNo(accountNo);
            if (fileOptional.isEmpty()) {
                return new SaveOneResponseDto(null);
            } else {
                file = fileOptional.get();
                Blob blob = (Blob) file.getFileMultipartFile();
                int blobLength = (int) blob.length();
                byte[] blobBytes = blob.getBytes(1, blobLength);
                blob.free();

                return new SaveOneResponseDto(blobBytes);
            }
        } catch (Exception e) {
            return new SaveOneResponseDto();
        }
    }
}
