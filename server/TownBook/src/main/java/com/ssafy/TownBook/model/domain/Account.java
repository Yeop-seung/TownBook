package com.ssafy.TownBook.model.domain;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Account {

    @Id
    Long no;

    @NotNull
    @Column
    String id;

    @NotNull
    String pw;

    @NotNull
    String name;

    @NotNull
    String address;

    @NotNull
    @Column
    String phone_number;

    @NotNull
    String email;

    @Column(columnDefinition = "integer default 0")
    Integer point;

    @Column(columnDefinition = "integer default 0")
    Integer book_cnt;

    @NotNull
    Integer type;

    @NotNull
    String nickname;

    @NotNull
    String birthday;

//    @OneToOne
//    Book book;

//    @OneToMany(mappedBy = "account")
//    List<Hit> hit = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    List<Board> board = new ArrayList<>();

//    @OneToMany(mappedBy = "account")
//    List<Comment> comment = new ArrayList<>();

}
