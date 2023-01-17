package com.ssafy.TownBook.model.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Locker {

    @Id
    @Column(name = "locker_no")
    private Long lockerNo;

    @NotNull
    @Column(name = "locker_region")
    private String lockerRegion;

    @NotNull
    @Column(name = "locker_weight", columnDefinition = "Integer default 0")
    private Integer lockerWeight;

    @NotNull
    @Column(name = "locker_book_cnt", columnDefinition = "Integer default 0")
    private Integer lockerBookCnt;

    @OneToMany(mappedBy = "locker")
    private List<DetailLocker> detailLocker;

    @Builder
    public Locker(Long lockerNo, String lockerRegion, Integer lockerWeight, Integer lockerBookCnt,
            List<DetailLocker> detailLocker) {
        this.lockerNo = lockerNo;
        this.lockerRegion = lockerRegion;
        this.lockerWeight = lockerWeight;
        this.lockerBookCnt = lockerBookCnt;
        this.detailLocker = detailLocker;
    }
}
