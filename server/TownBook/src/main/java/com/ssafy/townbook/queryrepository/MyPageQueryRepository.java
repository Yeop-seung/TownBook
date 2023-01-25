package com.ssafy.townbook.queryrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.townbook.model.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.townbook.model.entity.QAccount.account;

@RequiredArgsConstructor
@Repository
public class MyPageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Account> findByName(Long accountNo) throws Exception{
        return queryFactory.selectFrom(account).where(account.accountNo.eq(accountNo)).fetch();
    }
}
