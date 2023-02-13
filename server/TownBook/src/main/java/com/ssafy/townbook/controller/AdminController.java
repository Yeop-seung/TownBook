package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.service.AdminService;
import com.ssafy.townbook.model.service.MyPageServiceImpl;
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
    
    private AdminService adminService;
    
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    
    @Autowired
    private MyPageServiceImpl myPageService;
    
    /**
     * 전체 회원 조회
     *
     * @return List<AccountDto>
     */
    @GetMapping("")
    public ResponseEntity<FindListResponseDto> findAllAccounts() {
        return new ResponseEntity<FindListResponseDto>(adminService.findAllAccounts(), HttpStatus.OK);
    }
    
    /**
     * 회원번호로 단일 회원을 조회
     *
     * @param accountNo
     * @return AccountDto
     */
    @GetMapping("/{accountNo}")
    public ResponseEntity<FindOneResponseDto> findAccountByAccountNo(@PathVariable Long accountNo) {
        return new ResponseEntity<FindOneResponseDto>(adminService.findAccountByAccountNo(accountNo), HttpStatus.OK);
    }


    /**
     *
     *
     * @param accountNo
     * @return
     */
//    @GetMapping("/givePoint/{accountNo")
//    public ResponseEntity<FindOneResponseDto> givePointOneUser(@PathVariable Long accountNo){
//        return new ResponseEntity<FindOneResponseDto>(adminService.givePointOneUser(accountNo),HttpStatus.OK);
//    }
}
