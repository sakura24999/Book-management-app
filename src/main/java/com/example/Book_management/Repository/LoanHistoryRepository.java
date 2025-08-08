package com.example.Book_management.Repository;

import org.springframework.stereotype.Repository;
import com.example.Book_management.Model.LoanHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistoryModel,Long>{
    // 検索メソッド
    List<LoanHistoryModel>findByLendDate(LocalDateTime lendDate);
    List<LoanHistoryModel>findByGiveback(LocalDateTime returnDate);
}
