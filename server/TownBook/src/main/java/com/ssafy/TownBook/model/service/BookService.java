package com.ssafy.TownBook.model.service;

import com.ssafy.TownBook.model.Entity.Book;
import com.ssafy.TownBook.model.Entity.DetailLocker;
import com.ssafy.TownBook.model.Entity.Locker;
import com.ssafy.TownBook.model.repository.BookRepository;
import com.ssafy.TownBook.model.repository.DetailLockerRepository;
import com.ssafy.TownBook.model.repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DetailLockerRepository detailLockerRepository;

    @Autowired
    private LockerRepository lockerRepository;

    /**
     * 책을 기부했을 때
     * @param book
     */
    public void giveBook(Book book){
        bookRepository.save(book);

        DetailLocker detailLocker = book.getDetailLocker();
        detailLocker.setDetailLockerIsEmpty(false);
        detailLockerRepository.save(detailLocker);

        Locker locker = detailLocker.getLocker();
        locker.setLockerBookCnt(locker.getLockerBookCnt() + 1);
        lockerRepository.save(locker);
    }

    /**
     * 책을 가져갔을 때
     * @param book
     */
    public void takeBook(Book book){
        book.setBookCnt(0);
        bookRepository.save(book);

        DetailLocker detailLocker = book.getDetailLocker();
        detailLocker.setDetailLockerIsEmpty(true);
        detailLockerRepository.save(detailLocker);

        Locker locker = detailLocker.getLocker();
        locker.setLockerBookCnt(locker.getLockerBookCnt() - 1);
        lockerRepository.save(locker);
    }
}
