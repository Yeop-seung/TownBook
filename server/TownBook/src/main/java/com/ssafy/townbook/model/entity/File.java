package com.ssafy.townbook.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_contentType")
    private String fileContentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-file`", insertable = false, updatable = false)
    private Account account;

    @Column(name = "`fk-account-file`")
    private Long accountNo;

    @Builder
    public File(Long fileNo, String fileOriginName, String fileSavedName, String filePath, String fileExtension, Long fileSize, String fileContentType, Long accountNo) {
        this.fileNo = fileNo;
        this.fileOriginName = fileOriginName;
        this.fileSavedName = fileSavedName;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.fileContentType = fileContentType;
        this.accountNo = accountNo;
    }
}
