package com.example.Book_management.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Book_management.Model.BookModel;

@Repository
public interface BookRepository extends JpaRepository <BookModel,Long>{
    // 基本検索メソッド
    List<BookModel> findByAvailable(Boolean available);
    List<BookModel> findByTitle(String title);
    List<BookModel> findByAuthor(String author);
    List<BookModel> findByGenre(String genre);
    Optional<BookModel> findByIsbn(String isbn);
    // ISBN重複チェック
    boolean existsByIsbn(String isbn);
}
