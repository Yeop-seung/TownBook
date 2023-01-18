package com.ssafy.TownBook.model.domain;


import java.util.ArrayList;
import java.util.HashSet;
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
@Builder
@Table(name = "`account`")
public class Account {


    @Id
    @Column(name = "account_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNo;

    @Column(name = "account_id", length = 50, unique = true)
    private String accountId;

    @Column(name = "account_pw", length = 100, unique = true)
    private String accountPw;

    @Column(name = "account_name", length = 50)
    String accountName;

    @Column(name = "account_address")
    private String accountAddress;

    @Column(name = "account_phone_number")
    private String accountPhoneNumber;

    @Column(name = "account_email")
    private String accountEmail;

    @Column(name = "account_point")
    @ColumnDefault("0")
    private Integer accountPoint;

    @Column(name = "account_book_cnt")
    @ColumnDefault("0")
    private Integer accountBookCnt;

    @Column(name = "account_nickname")
    private String accountNickname;

    @Column(name = "account_type")
    @ColumnDefault("false")
    private Boolean accountType;

    @Column(name = "account_birthday")
    private String accountBirthday;

    @OneToOne(mappedBy = "account")
    private Book book;

    @OneToMany(mappedBy = "account")
    private List<Hit> hits = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Comment> comments = new ArrayList<>();
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_no", referencedColumnName = "account_no")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;


    @Builder
    public Account(Long accountNo, String accountId, String accountPw, String accountName,
            String accountAddress, String accountPhoneNumber, String accountEmail,
            Integer accountPoint,
            Integer accountBookCnt, String accountNickname, Boolean accountType,
            String accountBirthday,
            Book book, List<Hit> hit, List<Board> board, List<Comment> comment, boolean activated,
            Set<Authority> authorities) {
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
        this.accountType = accountType;
        this.accountBirthday = accountBirthday;
        this.book = book;
        this.hits = hit;
        this.boards = board;
        this.comments = comment;
        this.activated = activated;
        this.authorities = authorities;
    }
}
