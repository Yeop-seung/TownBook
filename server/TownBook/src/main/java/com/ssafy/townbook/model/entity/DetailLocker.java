package com.ssafy.townbook.model.entity;

import javax.persistence.*;
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

    @Column(name = "detail_locker_isEmpty")
    @ColumnDefault("false")
    private Boolean detailLockerIsEmpty;

    @Builder
    public DetailLocker(Long detailLockerNo, Locker locker) {
        this.detailLockerNo = detailLockerNo;
        this.locker = locker;
    }
}
