package com.qks.springbootherbinate.service;

import com.qks.springbootherbinate.entity.Book;
import com.qks.springbootherbinate.repository.BookDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-05-21 17:43
 */
@Service
public class BookServiceImpl {

    @Resource
    private BookDao bookDao;

    public Book getBookById(int bookId){
        return bookDao.getBookById(bookId);
    }

    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public synchronized boolean addBook(Book book){
        if(bookDao.bookExists(book.getTitle(),book.getCategory())){
            return false;
        }else {
            bookDao.addBook(book);
            return true;
        }
    }
    public void updateBook(Book book){
        bookDao.updateBook(book);
    }

    public void deleteBook(int bookId){
        bookDao.deleteBook(bookId);
    }
}
