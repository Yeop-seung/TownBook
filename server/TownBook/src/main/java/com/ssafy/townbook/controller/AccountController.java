package com.ssafy.townbook.controller;


import com.ssafy.townbook.model.dto.AccountDto;
import com.ssafy.townbook.model.service.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signup(
            @Valid @RequestBody AccountDto accountDto
    ) {
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
}
