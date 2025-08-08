package com.example.Book_management.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookModel {
    // 主キー定義フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "book_id")
    private Long bookId;

    // プライベートフィールド
    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "genre")
    private String genre;

    @Column(name = "available")
    private Boolean available;

    // デフォルトコンストラクタ(JPA用)
    public BookModel(){}

    // コンストラクタ
    public BookModel(String title, String author, String isbn, String genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.available = true;
    }

    // ゲッター
    public Long getId() {
        return this.bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getGenre() {
        return this.genre;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    // セッター
    public void setId(Long bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
