package com.qks.springbootherbinate.repository;

import com.qks.springbootherbinate.entity.Book;

import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-05-21 17:41
 */
public interface BookDao {
    List<Book> getAllBooks();

    Book getBookById(int bookId);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int bookId);

    boolean bookExists(String title,String category);
}
