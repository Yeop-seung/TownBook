package com.ssafy.townbook.controller;


import com.ssafy.townbook.model.dto.AccountDto;
import com.ssafy.townbook.model.service.AccountService;
import com.ssafy.townbook.model.service.EmailService;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signup(@Valid @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.signup(accountDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<AccountDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(accountService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{accountEmail}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<AccountDto> getUserInfo(@PathVariable String accountEmail) {
        return ResponseEntity.ok(accountService.getUserWithAuthorities(accountEmail));
    }

    /**
     * 핸드폰 번호를 가지고 이메일 찾기
     *
     * @param phoneNumber
     * @return
     */
    @GetMapping("/findEmail/{phoneNumber}")
    public ResponseEntity<String> findEmail(@PathVariable String phoneNumber) {

        return new ResponseEntity<>(accountService.findEmail(phoneNumber), HttpStatus.OK);
    }

    /**
     * 계정 수정
     *
     * @param accountDto
     * @return
     */
    @PutMapping("/modify")
    public ResponseEntity accountModify(@RequestBody AccountDto accountDto) {
        return new ResponseEntity(accountService.accountModify(accountDto), HttpStatus.OK);

    }


    /**
     * 계정 비활성화
     *
     * @param leaveInfo
     * @return
     */
    @PutMapping("/leave")
    public ResponseEntity accountRemove(@RequestBody Map<String, String> leaveInfo) {
        System.out.println(leaveInfo.get("accountEmail"));
        return new ResponseEntity(accountService.accountRemove(leaveInfo.get("accountEmail"), leaveInfo.get("accountPw")), HttpStatus.OK);

    }

    /**
     * 이메일 인증 --> front로 보낸 인증 값과 비교해서 확인한다.
     *
     * @param email
     * @return
     * @throws Exception
     */
    @PostMapping("/emailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestBody String email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email);

        return new ResponseEntity(confirm, HttpStatus.OK);
    }

    /**
     * 임시 비밀번호
     *
     * @param accountEmail
     * @return
     * @throws Exception
     */
    @PostMapping("/tempPassword")
    public ResponseEntity tempPassword(@RequestBody Map<String, String> accountEmail) throws Exception {
        String tempPassword = emailService.getTmpPassword();
        String Email = accountEmail.get("accountEmail");

        if (accountService.updatePassword(Email, tempPassword)) {
            emailService.sendPasswordMessage(Email, tempPassword);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 비밀번호 변경
     *
     * @param AccountInfo
     * @return
     */
    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody Map<String, String> AccountInfo) {
        String email = AccountInfo.get("accountEmail");
        String pw = AccountInfo.get("accountPw");

        accountService.updatePassword(email, pw);

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 유저 TOP10 랭킹과 자신의 랭킹 조회
     *
     * @param accountNo
     * @return JSONArray
     * @throws Exception
     */
    @GetMapping("/ranking/{accountNo}")
    public ResponseEntity<?> findAccountBookCnt(@PathVariable Long accountNo) throws Exception{
        return new ResponseEntity<>(accountService.findAccountBookCnt(accountNo),HttpStatus.OK);
    }
}
