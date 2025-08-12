package com.example.Book_management.Repository;

import org.springframework.stereotype.Repository;
import com.example.Book_management.Model.LoanHistoryModel;
import com.example.Book_management.Model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistoryModel,Long>{
    // 検索メソッド
    List<LoanHistoryModel>findByLendDate(LocalDateTime lendDate);
    List<LoanHistoryModel>findByReturnDate(LocalDateTime returnDate);

    // 貸出中
    List<LoanHistoryModel> findByReturnDateIsNull();
    // 返却中
    List<LoanHistoryModel> findByReturnDateIsNotNull();

    // 外部キー関連の検索
    // ユーザーID検索
    List <LoanHistoryModel> findByUserUserId(Long userId);
    // 書籍ID検索
    List <LoanHistoryModel> findByBookBookId(Long bookId);
}
