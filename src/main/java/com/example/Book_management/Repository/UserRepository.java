package com.example.Book_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Book_management.Model.UserModel;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    // 基本検索
    List<UserModel> findByUserId(Long userId);
    List<UserModel> findByName(String name);
    List<UserModel> findByEmail(String email);
    List<UserModel> findByPassword(String password);
    List<UserModel> findByConfirmPassword(String confirmPassword);

    // カスタム検索
    boolean existsByEmail(String email);
}
