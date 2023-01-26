package com.ssafy.townbook.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Board {

    @Id
    @Column(name = "board_no")
    private Long boardNo;

    @NotNull
    @Column(name = "board_title")
    private String boardTitle;

    @NotNull
    @Column(name = "board_content")
    @Lob
    private String boardContent;

    @NotNull
    @Column(name = "board_write_date")
    private LocalDateTime boardWriteDate;

    @NotNull
    @Column(name = "board_views", columnDefinition = "Integer default 0")
    private Integer boardViews;

    @Column(name = "board_state")
    @ColumnDefault("true")
    private Boolean boardState;
    @OneToMany(mappedBy = "board")
    List<Hit> hit = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    List<Comment> comment = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-board`")
    Account account;

    @Builder
    public Board(Long boardNo, String boardTitle, String boardContent,
            LocalDateTime boardWriteDate, Integer boardViews, Boolean boardState, List<Hit> hit,
            List<Comment> comment, Account account) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriteDate = boardWriteDate;
        this.boardViews = boardViews;
        this.boardState = boardState;
        this.hit = hit;
        this.comment = comment;
        this.account = account;
    }
}
