package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.DetailLocker;
import com.ssafy.townbook.model.entity.Locker;
import com.ssafy.townbook.model.repository.BookRepository;
import com.ssafy.townbook.model.repository.DetailLockerRepository;
import com.ssafy.townbook.model.repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceImpl implements BookService {

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
    @Override
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
    @Override
    public void takeBook(Book book){
        book.setBookState(false);
        bookRepository.save(book);

        DetailLocker detailLocker = book.getDetailLocker();
        detailLocker.setDetailLockerIsEmpty(true);
        detailLockerRepository.save(detailLocker);

        Locker locker = detailLocker.getLocker();
        locker.setLockerBookCnt(locker.getLockerBookCnt() - 1);
        lockerRepository.save(locker);
    }

    /**
     *
     * @param lockerNo
     */
    public void searchAllBook(Long lockerNo){

    }
}
