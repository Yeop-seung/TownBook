//package com.ssafy.townbook.model.entity;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import net.minidev.json.annotate.JsonIgnore;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
//@Entity
//public class Hit {
//
//    @Id
//    @Column(name = "hit_no")
//    private Long hitNo;
//
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "`fk-account-hit`")
//    @JsonIgnore
//    private Account account;
//
//    @Builder
//    public Hit(Long hitNo, Account account) {
//        this.hitNo = hitNo;
//        this.board = board;
//        this.account = account;
//    }
//}
