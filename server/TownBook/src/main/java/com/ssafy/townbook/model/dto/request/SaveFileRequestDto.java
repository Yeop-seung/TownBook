package hide information.townbook.model.dto.request;

import hide information.townbook.model.entity.File;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.UUID;

@Getter
@Setter
public class SaveFileRequestDto {

    private Long accountNo;

    private String fileOriginName;


    @Builder
    public SaveFileRequestDto(Long accountNo, String fileOriginName) {
        this.accountNo = accountNo;
        this.fileOriginName = fileOriginName;
    }


    public File toEntity(Blob fileMultipartFile) throws IOException {
        if (fileMultipartFile == null) return null;
        return File.builder()
                .fileOriginName(fileOriginName)
                .accountNo(accountNo)
                .fileMultipartFile(fileMultipartFile)
                .build();
    }

}