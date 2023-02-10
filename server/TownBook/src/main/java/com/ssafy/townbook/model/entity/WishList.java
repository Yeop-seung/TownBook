package com.ssafy.townbook.model.entity;

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
    @JoinColumn(name = "`fk-account-wish_list`", insertable = false, updatable = false)
    private Account account;
    
    @Column(name = "`fk-account-wish_list`")
    private Long accountNo;
    
    @ManyToOne
    @JoinColumn(name = "`fk-book_log-wish_list`", insertable = false, updatable = false)
    private BookLog bookLog;
    
    @Column(name = "`fk-book_log-wish_list`")
    private Long bookLogNo;
}
