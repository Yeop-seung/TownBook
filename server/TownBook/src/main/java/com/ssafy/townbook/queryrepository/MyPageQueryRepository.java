package com.ssafy.townbook.queryrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.townbook.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ssafy.townbook.model.entity.QBookLog.bookLog;
import static com.ssafy.townbook.model.entity.QWishList.wishList;

@RequiredArgsConstructor
@Repository
public class MyPageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<List<BookLog>> findBookLogByAccountNo(Long accountNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(bookLog).where(bookLog.account.accountNo.eq(accountNo)).fetch());
    }

    public Optional<BookLog> findBookLogByBookLogNo(Long bookLogNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(bookLog).where(bookLog.bookLogNo.eq(bookLogNo)).fetch().get(0));
    }

    public Optional<List<BookLog>> findReceiveBookLog(Long receiverNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(bookLog).where(bookLog.bookLogReceiverNo.eq(receiverNo)).fetch());
    }

    public Optional<List<WishList>> findWishList(Long accountNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(wishList).where(wishList.account.accountNo.eq(accountNo)).fetch());
    }
}