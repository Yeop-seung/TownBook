package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    private final AdminService adminService;
    
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    /**
     * 전체 회원 조회
     * DTO로 변환하여 반환한다.
     *
     * @return List
     */
    @GetMapping("")
    public ResponseEntity<?> accounts() {
        return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
    }
    
    /**
     * 회원번호로 단일 회원을 조회
     * DTO로 변환하여 반환한다.
     *
     * @param accountNo
     * @return AdminDto
     */
    @GetMapping("/{accountNo}")
    public ResponseEntity<?> findAccountByAccountNo(@PathVariable Long accountNo) {
        return new ResponseEntity<>(adminService.findAccountByAccountNo(accountNo), HttpStatus.OK);
    }
}
