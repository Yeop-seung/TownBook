package com.ssafy.townbook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Authority {
    
    @Id
    @Column(name = "authority_name")
    private String authorityName;
    
    @Builder
    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }
}
