package com.example.Book_management.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_history")
public class LoanHistoryModel {
    // 主キー定義フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "history_id")
    private Long historyId;

    // 外部キー
    @Column(name = "book_id")
    private Long bookId;

    // フィールド
    @Column(name = "lend_date")
    private LocalDateTime lendDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    // デフォルトコンストラクタ
    public LoanHistoryModel(){}

    //コンストラクタ
    public LoanHistoryModel(LocalDateTime lendDate, LocalDateTime returnDate) {
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    // ゲッター
    public Long getHistoryId() {
        return this.historyId;
    }

    public LocalDateTime getLendDate() {
        return this.lendDate;
    }

    public LocalDateTime getReturnDate() {
        return this.returnDate;
    }

    // セッター
    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public void setLendDate(LocalDateTime lendDate) {
        this.lendDate = lendDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
