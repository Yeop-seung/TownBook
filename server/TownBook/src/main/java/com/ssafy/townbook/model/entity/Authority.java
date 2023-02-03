package com.ssafy.townbook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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