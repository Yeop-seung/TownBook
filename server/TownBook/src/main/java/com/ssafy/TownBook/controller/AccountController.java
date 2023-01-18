package com.ssafy.TownBook.controller;


import com.ssafy.TownBook.model.dto.AccountDto;
import com.ssafy.TownBook.model.service.AccountSerivce;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountSerivce accountSerivce;

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signup(
            @Valid @RequestBody AccountDto accountDto
    ) {
        return ResponseEntity.ok(accountSerivce.signup(accountDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<AccountDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(accountSerivce.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<AccountDto> getUserInfo(@PathVariable String accountId) {
        return ResponseEntity.ok(accountSerivce.getUserWithAuthorities(accountId));
    }
}
