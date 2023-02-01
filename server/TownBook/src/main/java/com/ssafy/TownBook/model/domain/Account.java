package com.ssafy.TownBook.model.domain;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
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
public class Account {
    
    @Id
    @Column(name = "account_no")
    private Long accountNo;
    
    @NotNull
    @Column(name = "account_id")
    private String accountId;
    
    @NotNull
    @Column(name = "account_pw")
    private String accountPw;
    
    @NotNull
    @Column(name = "account_name")
    private String accountName;
    
    @NotNull
    @Column(name = "account_address")
    private String accountAddress;
    
    @NotNull
    @Column(name = "account_phone_number")
    private String accountPhoneNumber;
    
    @NotNull
    @Column(name = "account_email")
    private String accountEmail;
    
    @NotNull
    @Column(name = "account_point", columnDefinition = "integer default 0")
    private Integer accountPoint;
    
    @NotNull
    @Column(name = "account_book_cnt", columnDefinition = "integer default 0")
    private Integer accountBookCnt;
    
    @NotNull
    @Column(name = "account_nickname")
    private String accountNickname;
    
    @NotNull
    @Column(name = "account_birthday")
    private String accountBirthday;
    
    @OneToOne(mappedBy = "account")
    private Book book;
    
    @OneToMany(mappedBy = "account")
    private List<Hit> hit = new ArrayList<>();
    
    @OneToMany(mappedBy = "account")
    private List<Board> board = new ArrayList<>();
    
    @OneToMany(mappedBy = "account")
    private List<Comment> comment = new ArrayList<>();
    
    @Builder
    public Account(Long accountNo, String accountId, String accountPw, String accountName,
            String accountAddress, String accountPhoneNumber, String accountEmail,
            Integer accountPoint,
            Integer accountBookCnt, String accountNickname, String accountBirthday, Book book,
            List<Hit> hit, List<Board> board, List<Comment> comment) {
        this.accountNo = accountNo;
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.accountName = accountName;
        this.accountAddress = accountAddress;
        this.accountPhoneNumber = accountPhoneNumber;
        this.accountEmail = accountEmail;
        this.accountPoint = accountPoint;
        this.accountBookCnt = accountBookCnt;
        this.accountNickname = accountNickname;
        this.accountBirthday = accountBirthday;
        this.book = book;
        this.hit = hit;
        this.board = board;
        this.comment = comment;
    }
}
