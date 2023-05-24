package hide information.townbook.queryrepository;

import static hide information.townbook.model.entity.QBookLog.bookLog;
import static hide information.townbook.model.entity.QWishList.wishList;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hide information.townbook.model.entity.BookLog;
import hide information.townbook.model.entity.WishList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MyPageQueryRepository {
    
    private final JPAQueryFactory queryFactory;
    
    public Optional<List<BookLog>> findBookLogByAccountNo(Long accountNo) throws Exception {
        return Optional.ofNullable(queryFactory
                .selectFrom(bookLog)
                .where(bookLog.account.accountNo.eq(accountNo))
                .fetch());
    }
    
    public Optional<List<BookLog>> findDonateBookLogByAccountNo(Long accountNo) throws Exception {
        return Optional.ofNullable(queryFactory
                .select(bookLog)
                .from(bookLog)
                .where(bookLog.accountNo.eq(accountNo))
                .fetch());
    }
    
    public Optional<List<BookLog>> findReceiveBookLogByReceiverNo(Long receiverNo) throws Exception {
        return Optional.ofNullable(queryFactory
                .selectFrom(bookLog)
                .where(bookLog.bookLogReceiverNo.eq(receiverNo)).fetch());
    }
    
    public Optional<BookLog> findBookLogByBookLogNo(Long bookLogNo) throws Exception {
        return Optional.ofNullable(queryFactory
                .selectFrom(bookLog)
                .where(bookLog.bookLogNo.eq(bookLogNo))
                .fetch().get(0));
    }
    
    public Optional<List<WishList>> findWishList(Long accountNo) throws Exception {
        return Optional.ofNullable(
                queryFactory.selectFrom(wishList).where(wishList.account.accountNo.eq(accountNo)).fetch());
    }
}