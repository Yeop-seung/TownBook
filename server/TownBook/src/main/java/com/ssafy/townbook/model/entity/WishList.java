package hide information.townbook.model.entity;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public WishList(Long accountNo, Long bookLogNo) {
        this.accountNo = accountNo;
        this.bookLogNo = bookLogNo;
    }
}
