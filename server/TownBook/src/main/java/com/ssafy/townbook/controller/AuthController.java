package hide information.townbook.controller;


import hide information.townbook.jwt.JwtFilter;
import hide information.townbook.jwt.TokenProvider;
import hide information.townbook.model.dto.LoginDto;
import hide information.townbook.model.dto.TokenDto;
import hide information.townbook.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private AccountService               accountService;
    private TokenProvider                tokenProvider;
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    
    @Autowired
    public AuthController(AccountService accountService, TokenProvider tokenProvider,
                          AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.accountService               = accountService;
        this.tokenProvider                = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
        
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getAccountEmail(), loginDto.getAccountPw());
        
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = tokenProvider.createToken(authentication);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearera" + jwt);
        
        return new ResponseEntity<TokenDto>(
                new TokenDto(accountService.findAccountByAccountEmail(loginDto.getAccountEmail()), jwt), httpHeaders,
                HttpStatus.OK);
    }
}
