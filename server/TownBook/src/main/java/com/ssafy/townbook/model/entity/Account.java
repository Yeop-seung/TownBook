package com.ssafy.townbook.model.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "`account`")
public class Account {
    @Id
    @Column(name = "account_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNo;

    @Column(name = "account_email", length = 50, unique = true)
    @NotNull
    private String accountEmail;

    @Column(name = "account_pw", length = 100, unique = true)
    @NotNull
    private String accountPw;

    @Column(name = "account_name", length = 50)
    @NotNull
    String accountName;

    @Column(name = "account_address")
    @NotNull
    private String accountAddress;

    @Column(name = "account_phone_number")
    @NotNull
    private String accountPhoneNumber;

    @Column(name = "account_gender")
    @NotNull
    private Integer accountGender;

    @Column(name = "account_point")
    @ColumnDefault("0")
    private Integer accountPoint;

    @Column(name = "account_book_cnt")
    @ColumnDefault("0")
    private Integer accountBookCnt;

    @Column(name = "account_nickname")
    @NotNull
    private String accountNickname;

    @Column(name = "account_birthday")
    @NotNull
    private String accountBirthday;

    @Column(name = "account_activated")
    @ColumnDefault("true")
    private Boolean accountActivated;

    @OneToOne(mappedBy = "account")
    private BookLog bookLog;

    @OneToMany(mappedBy = "account")
    private List<Hit> hits = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Comment> comments = new ArrayList<>();


    @OneToMany(mappedBy = "account")
    private List<WishList> wishLists = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Notice> notices = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_no", referencedColumnName = "account_no")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;


    @Builder
    public Account(Long accountNo, String accountEmail, String accountPw, String accountName,
            String accountAddress, String accountPhoneNumber, Integer accountGender,
            Integer accountPoint, Integer accountBookCnt, String accountNickname,
            String accountBirthday, BookLog bookLog, List<Hit> hits, List<Board> boards,
            List<Comment> comments, Boolean accountActivated, List<WishList> wishLists, List<File> files,
            List<Notice> notices,
            Set<Authority> authorities) {
        this.accountNo = accountNo;
        this.accountEmail = accountEmail;
        this.accountPw = accountPw;
        this.accountName = accountName;
        this.accountAddress = accountAddress;
        this.accountPhoneNumber = accountPhoneNumber;
        this.accountGender = accountGender;
        this.accountPoint = accountPoint;
        this.accountBookCnt = accountBookCnt;
        this.accountNickname = accountNickname;
        this.accountBirthday = accountBirthday;
        this.bookLog = bookLog;
        this.hits = hits;
        this.boards = boards;
        this.comments = comments;
        this.accountActivated = accountActivated;
        this.wishLists = wishLists;
        this.files = files;
        this.authorities = authorities;
        this.notices = notices;
    }
}
