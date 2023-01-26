package com.ssafy.townbook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class WishList {

    @Id
    @Column(name = "wish_list_no")
    private Long wishListNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-wish_list`")
    private Account account;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-book-wish_list`")
    private BookLog bookLog;
}
