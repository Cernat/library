package org.internship.library.impl;

import org.internship.library.api.Book;
import org.internship.library.api.BookRepository;
import org.internship.library.api.BookService;

import java.util.List;


public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    /**
     * This method will return book
     *
     * @param id The book id
     * @return The book found at this id
     */
    @Override
    public Book getBook(String id) {
        Book book = bookRepository.findBookById(id);
        return book;
    }

    /**
     * This method will create book
     *
     * @param book The book to create
     * @return The book created
     */
    @Override
    public Book createBook(Book book) {
        Book newBook = bookRepository.createBook(book);
        return newBook;
    }

    /**
     * This method will update book
     *
     * @param book The book to update
     * @return The book updated
     */
    @Override
    public Book updateBook(String id, Book book) {
        return bookRepository.updateBook(id, book);
    }

    /**
     * This method will delete book
     *
     * @param id The book to delete
     * @return The book deleted
     */
    @Override
    public void deleteBook(String id) {
        bookRepository.deleteBook(id);
    }

    @Override
    public List<Book> findBookEntitiesByAuthor(String author) {
        return bookRepository.findBookEntitiesByAuthor(author);
    }

    @Override
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookRepository getBookRepository() {
        return this.bookRepository;
    }


}
