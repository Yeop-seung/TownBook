package com.ssafy.TownBook.model.Entity;

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
public class Hit {

    @Id
    @Column(name = "hit_no")
    private Long hitNo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "`fk-hit-board`")
    private Board board;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "`fk-account-hit`")
    private Account account;

    @Builder
    public Hit(Long hitNo, Board board, Account account) {
        this.hitNo = hitNo;
        this.board = board;
        this.account = account;
    }
}
