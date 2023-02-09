package com.ssafy.townbook.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class File {

    @Id
    @Column(name = "file_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileNo;

    @Column(name = "file_origin_name")
    @NotNull
    private String fileOriginName;

    @Column(name = "file_multipart_file")
    @Lob
    private Blob fileMultipartFile;

    @Column(name = "`fk-account-file`")
    private Long accountNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-file`", insertable = false, updatable = false)
    private Account account;


    @Builder
    public File(Long fileNo, String fileOriginName, Long accountNo, Blob fileMultipartFile) {
        this.fileNo = fileNo;
        this.fileOriginName = fileOriginName;
        this.accountNo = accountNo;
        this.fileMultipartFile = fileMultipartFile;
    }
}
