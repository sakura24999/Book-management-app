package com.example.Book_management.Model;

import java.util.List;

import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {
    // 主キーフィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    // リレーショナル(1対多)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <LoanHistoryModel> loanHistories = new ArrayList<>();

    // フィールド
    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(name = "user_email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    // 一時的なフィールドとして扱う(DBには保存しない)
    @Transient
    private String confirmPassword;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // デフォルトコンストラクタ
    public UserModel(){}

    // コンストラクタ
    public UserModel(String name,String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // JPA ライフサイクルコールバック
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
    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<LoanHistoryModel> getLoanHistories() {
        return loanHistories;
    }

    // セッター
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setLoanHistories(List<LoanHistoryModel> loanHistories) {
        this.loanHistories = loanHistories;
    }

    // ビジネスロジック(単純)
    public void addLoanHistory(LoanHistoryModel loanHistory) {
        loanHistories.add(loanHistory);
        loanHistory.setUser(this);
    }

    public void removeLoanHistory(LoanHistoryModel loanHistory) {
        loanHistories.remove(loanHistory);
        loanHistory.setUser(null);
    }

    public boolean hasActiveLoan() {
        return loanHistories.stream()
                .anyMatch(history -> !history.isReturned());
    }
}
