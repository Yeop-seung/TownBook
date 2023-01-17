package com.ssafy.TownBook.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class DetailLocker {

    @Id
    @Column(name = "detail_locker_no")
    @OneToOne(mappedBy = "detaillocker")
    private Long detailLockerNo;

    @NotNull
    @Column(name = "fk-locker-detail_locker")
    @ManyToOne
    private Locker locker;

    @Builder
    public DetailLocker(Long detailLockerNo, Locker locker) {
        this.detailLockerNo = detailLockerNo;
        this.locker = locker;
    }
}
