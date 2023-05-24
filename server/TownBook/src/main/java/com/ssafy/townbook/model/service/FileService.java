package hide information.townbook.model.service;

import hide information.townbook.model.dto.request.SaveFileRequestDto;
import hide information.townbook.model.dto.response.SaveOneResponseDto;
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

    /**
     * 회원번호로 파일 찾기
     *
     * @param accountNo
     * @return SaveOneResponseDto
     */
    SaveOneResponseDto findFileByAccountNo(Long accountNo) throws Exception;
}
