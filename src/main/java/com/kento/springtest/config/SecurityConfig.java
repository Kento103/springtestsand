package com.kento.springtest.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
            .loginProcessingUrl("/login") // ログイン
            .loginPage("/login") // ログインページの指定
            .defaultSuccessUrl("/user") // ログイン成功した時にリダイレクトするページ
            .failureUrl("/login?error") // ログイン失敗したときに移動するページ
            .permitAll() // このページは誰でも許可
        ).logout(logout -> logout
            .logoutSuccessUrl("/")
        ).authorizeHttpRequests(authz -> authz
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 静的なものはすべて権限なしでも許可する
            .requestMatchers("/").permitAll() // 権限なしでも許可する
            .requestMatchers("/profile/**").permitAll() // 権限なしでも許可する
            .requestMatchers("/user").hasAnyRole("USER", "ADMIN") // USER、ADMINロールのみ許可する
            .anyRequest().authenticated() // ルールにないものは常に認証が必要
        );
        return http.build(); // ビルドは必須命令
    }
}
