package hide information.townbook.queryrepository;

import static hide information.townbook.model.entity.QBook.book;
import static hide information.townbook.model.entity.QBookLog.bookLog;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hide information.townbook.model.entity.Book;
import hide information.townbook.model.entity.BookLog;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SearchQueryRepository {
    
    private JPAQueryFactory jpaQueryFactory;
    
    @Autowired
    public SearchQueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return Optional<List < BookLog>>
     */
    public Optional<List<BookLog>> findBookLogByBookTitle(String bookTitle) {
        return Optional.ofNullable(jpaQueryFactory
                .select(bookLog)
                .from(bookLog)
                .where(bookLog.bookLogState.eq(true).and(bookLog.book.bookTitle.contains(bookTitle)))
                .fetch());
    }
}
