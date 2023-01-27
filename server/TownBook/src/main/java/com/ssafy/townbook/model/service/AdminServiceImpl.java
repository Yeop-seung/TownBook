package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AdminDto;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.repository.AdminRepository;
import java.util.List;
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
    
    @Override
    public List<AdminDto> findAll() {
        List<Account> findAccounts = adminRepository.findAll();
        return findAccounts.stream()
                .map(AdminDto::new)
                .collect(Collectors.toList());
    }
    
    @Override
    public AdminDto findAccountByAccountNo(Long accountNo) {
        Account findAccount = adminRepository.findAccountByAccountNo(accountNo);
        return new AdminDto(findAccount);
    }
}
