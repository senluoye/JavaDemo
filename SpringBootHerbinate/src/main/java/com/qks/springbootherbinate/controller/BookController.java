package com.qks.springbootherbinate.controller;

import com.qks.springbootherbinate.entity.Book;
import com.qks.springbootherbinate.service.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-05-21 17:52
 */
@RestController
public class BookController {

    @Resource
    private BookServiceImpl bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable("id") int id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public boolean addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/books", method = RequestMethod.PUT)
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }
}

