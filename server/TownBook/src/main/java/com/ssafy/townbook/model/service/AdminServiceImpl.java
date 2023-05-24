package hide information.townbook.model.service;

import hide information.townbook.model.dto.AdminDto;
import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.entity.Account;
import hide information.townbook.model.repository.AccountRepository;
import hide information.townbook.model.repository.AdminRepository;
import hide information.townbook.queryrepository.AdminQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private AccountRepository accountRepository;
    private AdminRepository adminRepository;

    private AdminQueryRepository adminQueryRepository;

    @Autowired
    public AdminServiceImpl(AccountRepository accountRepository, AdminRepository adminRepository, AdminQueryRepository adminQueryRepository) {
        this.accountRepository = accountRepository;
        this.adminRepository = adminRepository;
        this.adminQueryRepository = adminQueryRepository;
    }

    /**
     * 전체 회원 조회
     *
     * @return List<AccountDto>
     */
    @Override
    public FindListResponseDto findAllAccounts() {
        Optional<List<Account>> findAccounts = Optional.ofNullable(adminRepository.findAll());
        return new FindListResponseDto(findAccounts.get().stream()
                .map(AdminDto::new)
                .collect(Collectors.toList()));
    }

    /**
     * 회원번호로 단일 회원을 조회
     *
     * @param accountNo
     * @return AccountDto
     */
    @Override
    public FindOneResponseDto findAccountByAccountNo(Long accountNo) {
        Account findAccount = adminRepository.findAccountByAccountNo(accountNo).get();
        AdminDto adminDto = new AdminDto(findAccount);
        return new FindOneResponseDto(adminDto);
    }

    /**
     * 특정 회원에게 포인트 부여
     *
     * @param accountNo
     * @return Integer
     */
    @Override
    public FindOneResponseDto givePointOneUser(Long accountNo, Integer point) {
        try {
        Optional<Account> account = accountRepository.findByAccountNo(accountNo);
        if (account.isEmpty())
            return new FindOneResponseDto(null);
        Integer updatePoint = account.get().getAccountPoint() + point;
        Long result = adminQueryRepository.givePointOneUser(updatePoint, accountNo).get();
        if(result==1)
            return new FindOneResponseDto(updatePoint);
        else
            return new FindOneResponseDto("SQL 오류");
        }
        catch (Exception e){
            return new FindOneResponseDto();
        }
    }

    /**
     * Type이 1인 유저 : 취약계층 전체 회원에게 포인트 부여
     *
     * @param point
     * @return Boolean
     */
    @Override
    public FindOneResponseDto givePointTypeUser(Integer point) {
        try {
            Long result = adminQueryRepository.givePointTypeUser(point, 1).get();
            if(result==0)
                return new FindOneResponseDto("SQL 오류");
            else
                return new FindOneResponseDto("성공");
        }
        catch (Exception e){
            return new FindOneResponseDto();
        }
    }
}
