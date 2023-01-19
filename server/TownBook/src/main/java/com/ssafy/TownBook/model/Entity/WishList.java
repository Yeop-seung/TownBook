package com.ssafy.TownBook.model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "`fk-account-wish_list`")
    private Account account;


    @ManyToOne
    @JoinColumn(name = "`fk-book-wish_list`")
    private Book book;
}
