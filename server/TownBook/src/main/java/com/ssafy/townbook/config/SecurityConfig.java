package com.ssafy.townbook.config;


import com.ssafy.townbook.jwt.JwtAccessDeniedHandler;
import com.ssafy.townbook.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.townbook.jwt.JwtSecurityConfig;
import com.ssafy.townbook.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록된다.
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    
    private final TokenProvider               tokenProvider;
    private final CorsFilter                  corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler      jwtAccessDeniedHandler;
    
    public SecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider               = tokenProvider;
        this.corsFilter                  = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler      = jwtAccessDeniedHandler;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()
                
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                
                //                // enable h2-console
                //                .and()
                //                .headers()
                //                .frameOptions()
                //                .sameOrigin()
                
                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // antMatchers로 허용하는 api들 설정
                .and()
                .authorizeHttpRequests()
                .antMatchers("/account/authenticate", "/account/signup", "/account/**", "/auth/**", "/**").permitAll()
                .antMatchers("/book/**", "/admin/**", "/locker/**", "/detailLocker/**").permitAll()
                .antMatchers("/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        /* swagger v3 */
                        "/v3/api-docs/**",
                        "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
        
        return httpSecurity.build();
    }
}