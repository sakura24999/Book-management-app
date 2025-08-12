package com.example.Book_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Book_management.Model.UserModel;
import com.example.Book_management.Repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    // パスワードのハッシュ化用
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 基本CRUD操作
    public UserModel create(UserModel user) {
        // パスワードをハッシュ化
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserModel findById(Long userId) {
        return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: " + userId));
    }

    @Transactional(readOnly = true)
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel update(UserModel user) {
        if (user.getUserId() == null) {
            throw new IllegalArgumentException("更新対象のユーザーIDが必要です");
        }
        // パスワードが変更された場合はハッシュ化
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public void deleteById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("削除対象のユーザーが存在しません: " + userId);
        }
        userRepository.deleteById(userId);
    }

    // ユーザー登録処理
    public UserModel register(UserModel newUser) {
        // メールアドレスの重複チェック
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new IllegalArgumentException("このメールアドレスはすでに登録されています: " + newUser.getEmail());
        }
        return userRepository.save(newUser);
        // パスワードの確認
        if (userRepository.existsByPassword(newUser.getPassword()) != userRepository.existsByConfirmPassword(newUser.getConfirmPassword())) {
            throw new IllegalArgumentException("パスワードと確認用パスワードが一致しません: " + newUser.getPassword());
        }
    }
}
