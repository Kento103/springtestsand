package com.kento.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.kento.springtest.Repository.UserRepository;
import com.kento.springtest.model.User;
/*
 * Service ビジネスロジックを作成するクラスを作成する
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 全ユーザを取得する
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ユーザーをIDで取得(データ型注意！クラスのデータ型になる！(intじゃなくてIntegerなど))
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // ユーザーを保存
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // ユーザーを削除
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
