package com.ssafy.townbook.controller;


import com.ssafy.townbook.model.dto.AccountDto;
import com.ssafy.townbook.model.dto.request.ModifyAccountRequestDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.dto.response.SaveOneResponseDto;
import com.ssafy.townbook.model.service.AccountService;
import com.ssafy.townbook.model.service.EmailService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    
    private AccountService accountService;
    private EmailService   emailService;
    
    @Autowired
    public AccountController(AccountService accountService, EmailService emailService) {
        this.accountService = accountService;
        this.emailService   = emailService;
    }
    
    /**
     * 회원 가입
     *
     * @param accountDto
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<SaveOneResponseDto> signup(@Valid @RequestBody AccountDto accountDto) {
        return new ResponseEntity<SaveOneResponseDto>(accountService.signup(accountDto), HttpStatus.OK);
    }
    
    // 보류
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<AccountDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(accountService.getMyUserWithAuthorities());
    }
    
    // 보류
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
    public ResponseEntity<FindOneResponseDto> findEmail(@PathVariable String phoneNumber) {
        return new ResponseEntity<FindOneResponseDto>(accountService.findEmail(phoneNumber), HttpStatus.OK);
    }
    
    /**
     * 계정 수정
     *
     * @param modifyAccountRequestDto
     * @return
     */
    @PutMapping("/modify")
    public ResponseEntity<SaveOneResponseDto> accountModify(
            @RequestBody ModifyAccountRequestDto modifyAccountRequestDto) {
        return new ResponseEntity<SaveOneResponseDto>(accountService.accountModify(modifyAccountRequestDto),
                HttpStatus.OK);
        
    }
    
    
    /**
     * 계정 비활성화
     *
     * @param leaveInfo
     * @return
     */
    @PutMapping("/leave")
    public ResponseEntity<SaveOneResponseDto> accountRemove(@RequestBody Map<String, String> leaveInfo) {
        return new ResponseEntity<SaveOneResponseDto>(
                accountService.accountRemove(leaveInfo.get("accountEmail"), leaveInfo.get("accountPw")), HttpStatus.OK);
        
    }
    
    /**
     * 이메일 인증 --> front로 보낸 인증 값과 비교해서 확인한다.
     *
     * @param email
     * @return
     * @throws Exception
     */
    @PostMapping("/emailConfirm")
    public ResponseEntity<String> emailConfirm(@RequestBody Map<String, String> email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email.get("email"));
        return new ResponseEntity<String>(confirm, HttpStatus.OK);
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
        String Email        = accountEmail.get("accountEmail");
        
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
        String pw    = AccountInfo.get("accountPw");
        
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
    public ResponseEntity<FindOneResponseDto> findAccountBookCnt(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<FindOneResponseDto>(accountService.findAccountBookCnt(accountNo), HttpStatus.OK);
    }
}
