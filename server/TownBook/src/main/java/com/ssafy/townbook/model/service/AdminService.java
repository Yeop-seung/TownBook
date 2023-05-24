package hide information.townbook.model.service;

import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;

public interface AdminService {
    
    /**
     * 전체 회원 조회
     *
     * @return List<AccountDto>
     */
    FindListResponseDto findAllAccounts();
    
    /**
     * 회원번호로 단일 회원을 조회
     *
     * @param accountNo
     * @return AccountDto
     */
    FindOneResponseDto findAccountByAccountNo(Long accountNo);

    /**
     * 특정 회원에게 포인트 부여
     *
     * @param accountNo
     * @return Integer
     */
    FindOneResponseDto givePointOneUser(Long accountNo, Integer point);

    /**
     * Type이 1인 유저 : 취약계층 전체 회원에게 포인트 부여
     *
     * @param point
     * @return Boolean
     */
    FindOneResponseDto givePointTypeUser(Integer point);
}
