package com.ssafy.TownBook.model.domain;

import javax.persistence.*;
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
    private Long detailLockerNo;

    @OneToOne(mappedBy = "detailLocker")
    private Book book;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "`fk-locker-detail_locker`")
    private Locker locker;

    @Builder
    public DetailLocker(Long detailLockerNo, Locker locker) {
        this.detailLockerNo = detailLockerNo;
        this.locker = locker;
    }
}
