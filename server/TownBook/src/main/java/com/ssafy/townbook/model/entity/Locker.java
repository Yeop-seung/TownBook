package hide information.townbook.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@DynamicInsert
public class Locker {
    
    @Id
    @Column(name = "locker_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lockerNo;
    
    @NotNull
    @Column(name = "locker_region")
    private String lockerRegion;
    
    @NotNull
    @Column(name = "locker_latitude")
    private Double lockerLatitude;
    
    @NotNull
    @Column(name = "locker_longitude")
    private Double lockerLongitude;
    
    @Column(name = "locker_book_cnt")
    @ColumnDefault("0")
    private Integer lockerBookCnt;
    
    @OneToMany(mappedBy = "locker")
    private List<DetailLocker> detailLocker = new ArrayList<>();
    
    @Builder
    public Locker(Long lockerNo, String lockerRegion, Double lockerLatitude, Double lockerLongitude,
            Integer lockerBookCnt, List<DetailLocker> detailLocker) {
        this.lockerNo        = lockerNo;
        this.lockerRegion    = lockerRegion;
        this.lockerLatitude  = lockerLatitude;
        this.lockerLongitude = lockerLongitude;
        this.lockerBookCnt   = lockerBookCnt;
        this.detailLocker    = detailLocker;
    }
    
    public void addDetailLocker(DetailLocker detailLocker) {
        this.getDetailLocker().add(detailLocker);
        detailLocker.setLocker(this);
    }
}
