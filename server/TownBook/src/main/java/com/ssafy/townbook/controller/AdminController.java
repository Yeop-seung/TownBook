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

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    private AdminService adminService;
    @Autowired
    private MyPageServiceImpl myPageService;
    
    /**
     * 전체 회원 조회
     * DTO로 변환하여 반환한다.
     *
     * @return List
     */
    @GetMapping("")
    public ResponseEntity<?> findAccounts() {
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

    @GetMapping("/detail/{accountNo}/log")
    public ResponseEntity<?> findBookLogByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<>(myPageService.findBookLogByAccountNo(accountNo), HttpStatus.OK);
    }




}
