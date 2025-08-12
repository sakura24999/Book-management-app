package com.example.Book_management.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_history")
public class LoanHistoryModel {
    // 主キー定義フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    // 外部キー関連付け(JPA リレーション)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookModel book; // BookModelとの関連

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user; // UserModelとの関連

    // フィールド
    @Column(name = "lend_date")
    private LocalDateTime lendDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // デフォルトコンストラクタ
    public LoanHistoryModel(){}

    //コンストラクタ
    public LoanHistoryModel(LocalDateTime lendDate, LocalDateTime returnDate) {
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    //JPA ライフサイクルコールバック
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ゲッター
    public UserModel getUser() {
        return user;
    }

    public BookModel getBook() {
        return book;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public LocalDateTime getLendDate() {
        return lendDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    // セッター
    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public void setLendDate(LocalDateTime lendDate) {
        this.lendDate = lendDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    // ビジネスロジック(単純)
    public boolean isReturned() {
        return returnDate != null;
    }

    public void returnBook() {
        if (this.returnDate == null) {
            this.returnDate = LocalDateTime.now();
        }
    }
}
