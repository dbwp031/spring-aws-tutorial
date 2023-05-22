package com.dbwp031.springaws.config.auth;

import com.dbwp031.springaws.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
@Configuration // 얘를 안달면 들가자마자 로그인.
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()// h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeHttpRequests() //url별 권한 관리를 설정하는 옵션의 시작접 (얘가 있어야 matchers사용가능)
                        .requestMatchers("/","/css/**","/images/**","/js/**","/h2-console/**")
                            .permitAll()
                        .requestMatchers("/api/v1/**")
                            .hasRole(Role.USER.name()) //
                        .anyRequest()// 나머지 모든 url
                            .authenticated()// 인증받은사람들만 사용가능
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃 기능에 대한 여러 설정의 진입점, 로그아웃 성공시 url
                .and()
                    .oauth2Login()// OAuth2 로그인 기능에 대한 여러 설정의 진입점
                       .userInfoEndpoint()// OAuth2 로그인 성공 이후 사용자의 정보를 가져올 때의 설정을 담당
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록.
                                                                    // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
        return http.build();
    }
}
