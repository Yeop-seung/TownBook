package com.ssafy.TownBook.model.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board {

    @Id
    Long no;

    @NotNull
    String title;

    @NotNull
    String category;

    @NotNull
    @Lob
    String text;

    LocalDateTime writeDate;

    @Column(columnDefinition = "Integer default 0")
    Integer views;

//    @OneToMany(mappedBy = "board")
//    List<Hit> hit = new ArrayList<>();

//    @OneToMany(mappedBy = "board")
//    List<Comment> comment = new ArrayList<>();

    @ManyToOne
    Account account;

}
