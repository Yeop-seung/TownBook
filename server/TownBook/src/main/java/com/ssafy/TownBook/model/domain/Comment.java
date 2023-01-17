package com.ssafy.TownBook.model.domain;

import java.time.LocalDateTime;
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
public class Comment {

    @Id
    @Column(name = "comment_no")
    private Long commentNo;

    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @NotNull
    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "fk-comment-comment")
    @OneToOne(mappedBy = "comment")
    private Comment comment;

    @NotNull
    @Column(name = "fk-board-comment")
    @ManyToOne
    private Board board;

    @NotNull
    @Column(name = "fk-account-comment")
    @ManyToOne
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
