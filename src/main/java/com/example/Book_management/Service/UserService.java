package com.example.Book_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Book_management.Model.UserModel;
import com.example.Book_management.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 基本CRUD操作
    public UserModel create(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel findById(Long userId) {
        return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: " userId));
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel update(UserModel user) {
        return userRepository.save(user);
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    // ユーザー登録処理
    public UserModel register(UserModel userId) {
        return userRepository.save(userId);

        if (!userId) {
            return "会員登録されていません。新規会員登録をしてください。";
        }else {
            return "会員登録されているので、ログインページからログインしてください。"
        }
    }
}
