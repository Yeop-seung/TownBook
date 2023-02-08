package com.ssafy.townbook.model.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class File {

    @Id
    @Column(name = "file_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileNo;

    @Column(name = "file_origin_name")
    @NotNull
    private String fileOriginName;

    @Column(name = "file_saved_name")
    @NotNull
    private String fileSavedName;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_contentType")
    private String fileContentType;

    @Column(name = "file_multipart_file")
    @Lob
    private Blob fileMultipartFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-file`", insertable = false, updatable = false)
    private Account account;

    @Column(name = "`fk-account-file`")
    private Long accountNo;

    @Builder
    public File(Long fileNo, String fileOriginName, String fileSavedName, String fileExtension, Long fileSize, String fileContentType, Long accountNo,Blob fileMultipartFile) {
        this.fileNo = fileNo;
        this.fileOriginName = fileOriginName;
        this.fileSavedName = fileSavedName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.fileContentType = fileContentType;
        this.accountNo = accountNo;
        this.fileMultipartFile = fileMultipartFile;
    }
}
