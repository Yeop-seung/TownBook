package com.ssafy.townbook.model.entity;

import java.util.ArrayList;
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
import org.hibernate.annotations.ColumnDefault;

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
    @Column(name = "locker_latitude")
    private String lockerLatitude;

    @NotNull
    @Column(name = "locker_longitude")
    private String lockerLongitude;

    @Column(name = "locker_book_cnt")
    @ColumnDefault("0")
    private Integer lockerBookCnt;

    @OneToMany(mappedBy = "locker")
    private List<DetailLocker> detailLocker = new ArrayList<>();

    @Builder
    public Locker(Long lockerNo, String lockerRegion, String lockerLatitude, String lockerLongitude, Integer lockerBookCnt, List<DetailLocker> detailLocker) {
        this.lockerNo = lockerNo;
        this.lockerRegion = lockerRegion;
        this.lockerLatitude = lockerLatitude;
        this.lockerLongitude = lockerLongitude;
        this.lockerBookCnt = lockerBookCnt;
        this.detailLocker = detailLocker;
    }
}
