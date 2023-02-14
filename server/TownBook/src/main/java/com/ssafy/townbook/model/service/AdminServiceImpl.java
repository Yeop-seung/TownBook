package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AdminDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.repository.AccountRepository;
import com.ssafy.townbook.model.repository.AdminRepository;
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

    @Autowired
    public AdminServiceImpl(AccountRepository accountRepository, AdminRepository adminRepository) {
        this.accountRepository = accountRepository;
        this.adminRepository = adminRepository;
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
        Optional<Account> account = accountRepository.findByAccountNo(accountNo);
        if (account.isEmpty())
            return new FindOneResponseDto(null);
        Integer result = account.get().getAccountPoint() + point;
        account.get().setAccountPoint(result);
        return new FindOneResponseDto(result);
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
            adminRepository.givePointTypeUser(point, 1);
            return new FindOneResponseDto("Success");
        } catch (Exception e) {
            return new FindOneResponseDto();
        }
    }
}
