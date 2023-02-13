package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AdminDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    
    private AdminRepository adminRepository;
    
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
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
        Account  findAccount = adminRepository.findAccountByAccountNo(accountNo).get();
        AdminDto adminDto    = new AdminDto(findAccount);
        return new FindOneResponseDto(adminDto);
    }
}
