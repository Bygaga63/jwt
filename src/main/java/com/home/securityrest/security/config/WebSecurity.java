package com.home.securityrest.security.config;

import com.home.securityrest.security.details.UserDetailsServiceImpl;
import com.home.securityrest.security.filters.JWTAuthenticationFilter;
import com.home.securityrest.security.provider.TokenAuthentificationProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {
    //Не обязателен, полезен для передачи ошибки, если аутентификация прошла неуспешно.
    private AuthenticationEntryPoint authEntryPoint;
    private UserDetailsService userDetailsService;
    private TokenAuthentificationProvider tokenAuthentificationProvider;
//    private Aut
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(tokenAuthentificationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/css/**", "/js/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                //включаем basic аутентификацию
                    .httpBasic()
                //если будут ошибки аутентификации, добовляем формат ошибки
                    .authenticationEntryPoint(authEntryPoint)
                .and()
                    .authenticationProvider(tokenAuthentificationProvider)
                .addFilterAfter(getAthenticationFilter(), BasicAuthenticationFilter.class);
    }

    public JWTAuthenticationFilter getAthenticationFilter() throws Exception {
        final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/**");
        return filter;
    }

    @Override
    //здесь можно без аутентификации пользователя открывать urls
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web
                    .ignoring().antMatchers(HttpMethod.POST,"/users")
                .and()
                    .ignoring().antMatchers("/h2-console/**");
    }
}
