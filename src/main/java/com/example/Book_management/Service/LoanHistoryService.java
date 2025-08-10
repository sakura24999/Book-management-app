package com.example.Book_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Book_management.Model.LoanHistoryModel;
import com.example.Book_management.Repository.LoanHistoryRepository;

@Service
public class LoanHistoryService {
    @Autowired
    private LoanHistoryRepository loanhistoryRepository;

    // 基本CRUD操作
    public LoanHistoryModel create(LoanHistoryModel history) {
        return loanhistoryRepository.save(history);
    }

    public LoanHistoryModel findById(Long historyId) {
        return loanhistoryRepository.findById(historyId)
        .orElseThrow(() -> new RuntimeException("貸出し履歴がありません: " historyId));
    }

    public List<LoanHistoryModel> findAll() {
        return loanhistoryRepository.findAll();
    }

    public LoanHistoryModel update(LoanHistoryModel history) {
        return loanhistoryRepository.save(history);
    }

    public void deleteById(Long historyId) {
        loanhistoryRepository.deleteById(historyId);
    }
}
