package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.AdminDto;
import java.util.List;

public interface AdminService {
    
    List<AdminDto> findAll();
    
    AdminDto findAccountByAccountNo(Long accountNo);
}
