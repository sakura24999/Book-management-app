package com.example.Book_management.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Book_management.Model.LoanHistoryModel;
import com.example.Book_management.Repository.LoanHistoryRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoanHistoryService {
    @Autowired
    private LoanHistoryRepository loanhistoryRepository;

    // 基本CRUD操作
    public LoanHistoryModel create(LoanHistoryModel history) {
        return loanhistoryRepository.save(history);
    }

    @Transactional(readOnly = true)
    public LoanHistoryModel findById(Long historyId) {
        return loanhistoryRepository.findById(historyId)
        .orElseThrow(() -> new RuntimeException("貸出し履歴がありません: " + historyId));
    }

    @Transactional(readOnly = true)
    public List<LoanHistoryModel> findAll() {
        return loanhistoryRepository.findAll();
    }

    public LoanHistoryModel update(LoanHistoryModel history) {
        if (history.getHistoryId() == null) {
            throw new IllegalArgumentException("更新対象の履歴IDが必要です");
        }
        return loanhistoryRepository.save(history);
    }

    public void deleteById(Long historyId) {
        if (!loanhistoryRepository.existsById(historyId)) {
            throw new RuntimeException("削除対象の履歴が存在しません: " + historyId);
        }
        loanhistoryRepository.deleteById(historyId);
    }

    // ビジネスロジック(複雑)
    @Transactional(readOnly = true)
    public List<LoanHistoryModel> findByLendDate(LocalDateTime lendDate) {
        return loanhistoryRepository.findByLendDate(lendDate);
    }

    @Transactional(readOnly = true)
    public List<LoanHistoryModel> findByReturnDate(LocalDateTime returnDate) {
        return loanhistoryRepository.findByReturnDate(returnDate);
    }

    @Transactional(readOnly = true)
    public List <LoanHistoryModel> findByActiveLoans() {
        return loanhistoryRepository.findByReturnDateIsNull();
    }

    @Transactional(readOnly = true)
    public List<LoanHistoryModel> findByUserId(Long userId) {
        return loanhistoryRepository.findByUserUserId(userId);
    }

    @Transactional(readOnly = true)
    public List <LoanHistoryModel> findByBookId(Long bookId) {
        return loanhistoryRepository.findByBookBookId(bookId);
    }

    // 貸出し処理
    public LoanHistoryModel lendBook(Long bookId, Long userId) {
        // BookとUserの存在確認
        LoanHistoryModel newLoan = new LoanHistoryModel();
        // BookServiceとUserServiceから取得
        newLoan.setLendDate(LocalDateTime.now());
        return loanhistoryRepository.save(newLoan);
    }

    // 返却処理
    public LoanHistoryModel returnBook(Long historyId) {
        LoanHistoryModel history = findById(historyId);
        if (history.isReturned()) {
            throw new IllegalStateException("すでに返却済みです" + historyId);
        }
        history.returnBook();
        return loanhistoryRepository.save(history);
    }

    // バリデーション
    private void validateLoanHistory(LoanHistoryModel history) {
        if (history.getUser() == null) {
            throw new IllegalArgumentException("ユーザー情報が必要です");
        }
        if (history.getBook() == null) {
            throw new IllegalArgumentException("書籍情報が必要です");
        }
        if (history.getLendDate() == null) {
            throw new IllegalArgumentException("貸出日が必要です");
        }
    }
}
