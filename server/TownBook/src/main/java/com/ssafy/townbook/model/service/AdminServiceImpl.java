package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AdminDto;
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
     * DTO로 변환하여 반환한다.
     *
     * @return List
     */
    @Override
    public List<AdminDto> findAll() {
        Optional<List<Account>> findAccounts =Optional.ofNullable(adminRepository.findAll());
        return findAccounts.get().stream()
                .map(AdminDto::new)
                .collect(Collectors.toList());
    }
    
    /**
     * 회원번호로 단일 회원을 조회
     * DTO로 변환하여 반환한다.
     *
     * @param accountNo
     * @return AdminDto
     */
    @Override
    public AdminDto findAccountByAccountNo(Long accountNo) {
        Account findAccount = adminRepository.findAccountByAccountNo(accountNo).get();
        return new AdminDto(findAccount);
    }
}
