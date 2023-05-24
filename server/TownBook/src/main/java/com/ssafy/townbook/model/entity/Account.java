package hide information.townbook.model.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
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
@ToString
@Entity
@NoArgsConstructor
public class Account {
    
    @Id
    @Column(name = "account_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNo;

    @Column(name = "account_type")
    @ColumnDefault("0")
    private Integer accountType;
    
    @Column(name = "account_email", length = 50, unique = true)
    @NotNull
    private String accountEmail;
    
    @Column(name = "account_pw", length = 100, unique = true)
    @NotNull
    private String accountPw;
    
    @Column(name = "account_name", length = 50)
    @NotNull
    private String accountName;
    
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
    
    @Column(name = "account_BirthDay")
    @NotNull
    private String accountBirthDay;
    
    @Column(name = "account_activated")
    @ColumnDefault("true")
    private Boolean accountActivated;
    
    @OneToMany(mappedBy = "account")
    private List<BookLog> bookLog;
    
    @OneToMany(mappedBy = "account")
    private List<WishList> wishLists = new ArrayList<>();
    
    @OneToOne(mappedBy = "account")
    private File file;
    
    @OneToMany(mappedBy = "account")
    private List<Notice> notices = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_no", referencedColumnName = "account_no")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
    
    @Builder
    public Account(Long accountNo, String accountEmail, String accountPw, String accountName, String accountAddress,
                   String accountPhoneNumber, Integer accountGender, Integer accountPoint, Integer accountBookCnt,
                   String accountNickname, String accountBirthDay, Boolean accountActivated, List<BookLog> bookLog,
                   List<WishList> wishLists,File file, List<Notice> notices, Set<Authority> authorities) {
        this.accountNo          = accountNo;
        this.accountEmail       = accountEmail;
        this.accountPw          = accountPw;
        this.accountName        = accountName;
        this.accountAddress     = accountAddress;
        this.accountPhoneNumber = accountPhoneNumber;
        this.accountGender      = accountGender;
        this.accountPoint       = accountPoint;
        this.accountBookCnt     = accountBookCnt;
        this.accountNickname    = accountNickname;
        this.accountBirthDay    = accountBirthDay;
        this.accountActivated   = accountActivated;
        this.bookLog            = bookLog;
        this.wishLists          = wishLists;
        this.file               = file;
        this.notices            = notices;
        this.authorities        = authorities;
    }
}
