package org.internship.library.impl;

import java.util.List;

import org.internship.library.api.book.Book;
import org.internship.library.api.book.BookRepository;
import org.internship.library.api.book.BookService;

public class BookServiceImpl implements BookService
{

    private BookRepository bookRepository;

    /**
     * This method will return book
     *
     * @param id The book id
     * @return The book found at this id
     */
    @Override
    public Book getBook(String id)
    {
        return bookRepository.findBookById(id);
    }

    /**
     * This method will create book
     *
     * @param book The book to create
     * @return The book created
     */
    @Override
    public Book createBook(Book book)
    {
        return bookRepository.createBook(book);
    }

    /**
     * This method will update book
     *
     * @param book The book to update
     * @return The book updated
     */
    @Override
    public Book updateBook(String id, Book book)
    {
        return bookRepository.updateBook(id, book);
    }

    /**
     * This method will delete book
     *
     * @param id The book to delete
     */
    @Override
    public void deleteBook(String id)
    {
        bookRepository.deleteBook(id);
    }

    @Override
    public List<Book> findBookEntitiesByAuthor(String author)
    {
        return bookRepository.findBookEntitiesByAuthor(author);
    }

    @Override
    public void setBookRepository(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookRepository getBookRepository()
    {
        return this.bookRepository;
    }

}
