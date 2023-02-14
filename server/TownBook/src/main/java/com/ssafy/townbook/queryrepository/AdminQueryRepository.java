package com.ssafy.townbook.queryrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.townbook.model.entity.BookLog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ssafy.townbook.model.entity.QAccount.account;
import static com.ssafy.townbook.model.entity.QBookLog.bookLog;

@RequiredArgsConstructor
@Repository
public class AdminQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 단일 회원에게 포인트 부여
     *
     * @param point
     * @param accountNo
     * @return Optional<Long>
     */
    @Modifying
    public Optional<Long> givePointOneUser(Integer point, Long accountNo) {
        return Optional.ofNullable(queryFactory
                .update(account)
                .set(account.accountPoint, point)
                .where(account.accountNo.eq(accountNo).and(account.accountActivated.eq(true))).execute());
    }
    /**
     * Type이 1인 유저 : 취약계층 전체 회원에게 포인트 부여
     *
     * @param point
     * @param type
     * @return Optional<Long>
     */
    @Modifying
    public Optional<Long> givePointTypeUser(Integer point, Integer type){
        return Optional.ofNullable(queryFactory
                .update(account)
                .set(account.accountPoint, account.accountPoint.add(point))
                .where(account.accountType.eq(type).and(account.accountActivated.eq(true))).execute());
    }
}
