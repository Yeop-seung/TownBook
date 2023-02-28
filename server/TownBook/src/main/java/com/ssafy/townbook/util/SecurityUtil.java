package com.ssafy.townbook.util;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    
    private SecurityUtil() {
    }
    
    // 인증
    // 모든 접근 주체는 Authentication을 생성한다.
    // 이것은 SecuriyContext에 접근 주체(Authentication)와 인증정보(GrantedAuthority)을 담겨져 사용된다.
    // 현재 로그인한 사용자 정보를 가지고 있는 바스켓(?)이라 생각하면 될 듯 하다.
    // ThreadLocal에 보관되며, SecurityContextHolder를 통해 접근할 수 있다.
    
    // SecurityContextHolder.getContext().getAuthentication() 처럼 현재 사용자 정보를 가져와 사용할 수 있다.
    // 현재 아이디 가져오기 메소드

    // Security Session => Authentication => UserDetails(PrincipalDetails) (여기에 계정 정보가 들어있음)
    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }
        
        String username = null;
        
        // UserDetails = 사용자의 정보를 담는 인터페이스
        // principal --> ID정보만 가져다 사용할 수 있다 --> getUseranme-->ID가 들어있다.
        
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }
        
        // 인증된 유저 아이디 리턴
        return Optional.ofNullable(username);
    }
}
