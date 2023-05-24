package hide information.townbook.queryrepository;

import static hide information.townbook.model.entity.QAccount.account;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

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
    public Optional<Long> givePointTypeUser(Integer point, Integer type) {
        return Optional.ofNullable(queryFactory
                .update(account)
                .set(account.accountPoint, account.accountPoint.add(point))
                .where(account.accountType.eq(type).and(account.accountActivated.eq(true))).execute());
    }
}
