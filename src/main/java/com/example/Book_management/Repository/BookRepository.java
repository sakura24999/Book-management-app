package com.example.Book_management.Repository;

import java.io.ObjectInputFilter.Status;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Book_management.Model.BookModel;

@Repository
public interface BookRepository extends JpaRepository <BookModel Long>{
    // 状態検索
    List<BookModel> findByStatus(Boolean status);
}
