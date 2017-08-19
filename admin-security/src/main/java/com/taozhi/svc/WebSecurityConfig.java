package com.taozhi.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.taozhi.filter.MyExceptionTranslationFilter;
import com.taozhi.filter.MySecurityFilter;
import com.taozhi.handler.LoginFailHandler;
import com.taozhi.handler.LoginSuccessHandler;
import com.taozhi.handler.MyAccessDeniedHandler;
import com.taozhi.handler.MyLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySecurityFilter mySecurityFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private MyExceptionTranslationFilter myExceptionTranslationFilter;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(mySecurityFilter, FilterSecurityInterceptor.class)//
                .addFilterAfter(myExceptionTranslationFilter, MyExceptionTranslationFilter.class)//
                .authorizeRequests().antMatchers("/", "/signin").permitAll()//
                .anyRequest().authenticated()//
                .and()//
                .headers().frameOptions().disable()//
                .and()//
                .formLogin()//
                .loginProcessingUrl("/login")//
                .usernameParameter("username")//
                .passwordParameter("password")//
                .permitAll()//
                .successHandler(loginSuccessHandler())//
                .failureUrl("/signin")//
                .failureHandler(loginFailHandler())//
                .and()//
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler())//
                .and()//
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())//
                .and()//
                .csrf().disable();//
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setHideUserNotFoundExceptions(false);
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(new Md5PasswordEncoder());
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailHandler loginFailHandler() {
        return new LoginFailHandler();
    }

    @Bean
    public MyLogoutSuccessHandler logoutSuccessHandler() {
        return new MyLogoutSuccessHandler();
    }

    @Bean
    public MyAccessDeniedHandler accessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }
}
