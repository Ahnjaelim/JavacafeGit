package kr.co.javacafe.config;

import javax.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

 
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                	.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
                )
                .formLogin(login -> login	// form 방식 로그인 사용
                		.loginPage("/login") //로그인페이지
                		.loginProcessingUrl("/login") //로그인 sumbit받을 url
                		.passwordParameter("pw") //sumbit받을 pw ( id도 받을 수 있음)
                        .defaultSuccessUrl("/", true)	// 성공 시 home로
                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
                )
                .logout();
                // .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

        return http.build();
    }
}
