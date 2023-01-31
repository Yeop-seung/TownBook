package com.ssafy.townbook.model.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "comment_state")
    @ColumnDefault("true")
    private Boolean commentState;

    @OneToOne
    @JoinColumn(name = "`fk-comment-comment`")
    private Comment comment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-board-comment`")
    private Board board;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-comment`")
    @JsonIgnore
    private Account account;

    @Builder
    public Comment(Long commentNo, LocalDateTime commentDate, String commentContent,
            Boolean commentState, Comment comment, Board board, Account account) {
        this.commentNo = commentNo;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.commentState = commentState;
        this.comment = comment;
        this.board = board;
        this.account = account;
    }
}
