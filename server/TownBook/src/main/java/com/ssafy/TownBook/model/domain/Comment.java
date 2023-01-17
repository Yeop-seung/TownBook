package com.ssafy.TownBook.model.domain;

import java.time.LocalDateTime;
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
public class Comment {

    @Id
    @Column(name = "comment_no")
    private Long commentNo;

    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @NotNull
    @Column(name = "comment_content")
    private String commentContent;

    @OneToOne
    @JoinColumn(name = "`fk-comment-comment`")
    private Comment comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "`fk-board-comment`")
    private Board board;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "`fk-account-comment`")
    private Account account;

    @Builder
    public Comment(Long commentNo, LocalDateTime commentDate, String commentContent,
            Comment comment,
            Board board, Account account) {
        this.commentNo = commentNo;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.comment = comment;
        this.board = board;
        this.account = account;
    }
}
