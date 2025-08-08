package com.example.Book_management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Book_management.Model.LoanHistoryModel;
import com.example.Book_management.Repository.LoanHistoryRepository;

@Service
public class LoanHistoryService {
    @Autowired
    private LoanHistoryRepository loanhistoryrepository;
}
