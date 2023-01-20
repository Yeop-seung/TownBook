package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.entity.Book;

public interface BookService {
    void giveBook(Book book);
    void takeBook(Book book);

}
