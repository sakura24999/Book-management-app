package com.example.Book_management.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class BookModel {
    // 主キー定義フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // プライベートフィールド
    private String book;
    private String title;
    private String author;
    private String genre;
    private Boolean status;

    // デフォルトコンストラクタ(JPA用)
    public BookModel(){}

    // コンストラクタ
    public BookModel(String book) {
        this.book = book;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = true;
    }

    // ゲッター
    public String getBook() {
        return this.book;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getGenre() {
        return this.genre;
    }

    public Boolean getStatus() {
        return this.status;
    }

    // セッター
    public void setBook(String book) {
        this.book = book;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
