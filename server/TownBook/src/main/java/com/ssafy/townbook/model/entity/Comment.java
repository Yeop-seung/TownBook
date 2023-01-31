package com.ssafy.townbook.model.entity;

import com.beust.ah.A;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@DynamicInsert
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_parent_no")
    private Comment commentParent;

    @OneToMany(mappedBy = "commentParent", fetch = FetchType.LAZY)
    private List<Comment> childComment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-board-comment`")
    private Board board;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`fk-account-comment`")
    private Account account;

    @Builder
    public Comment(Long commentNo, LocalDateTime commentDate, String commentContent,
            Boolean commentState, Comment commentParent, Board board, Account account) {
        this.commentNo = commentNo;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.commentState = commentState;
        this.board = board;
        this.account = account;
        this.commentParent = commentParent;
    }
}
