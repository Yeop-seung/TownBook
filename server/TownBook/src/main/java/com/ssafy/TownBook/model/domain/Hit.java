package com.ssafy.TownBook.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Hit {

    @Id
    @Column(name = "hit_no")
    private Long hitNo;

    @NotNull
    @Column(name = "fk-hit-board")
    @ManyToOne
    private Board board;

    @NotNull
    @Column(name = "fk-account-hit")
    @ManyToOne
    private Account account;

    @Builder
    public Hit(Long hitNo, Board board, Account account) {
        this.hitNo = hitNo;
        this.board = board;
        this.account = account;
    }
}
