package com.kento.springtest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kento.springtest.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // コンストラクタは自動設定される
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository; // @RequiredArgsConstructorによって子インストラクタが自動設定される

    // @Override
    // public UserDetails loadUserByUseremail(String email) throws UsernameNotFoundException {
    //     return userRepository.findByEmail(email)
    //         .orElseThrow(() -> new UsernameNotFoundException("error"));
    // }

    // 後ろのUsernameNotFoundExceptionをthrowするために、必ずリポジトリ(userRepository)をOptionalにしなければならない。
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
