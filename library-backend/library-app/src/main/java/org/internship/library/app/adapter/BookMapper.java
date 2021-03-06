package org.internship.library.app.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.internship.library.api.dto.BookDTO;
import org.internship.library.app.persistence.entity.BookEntity;

public class BookMapper
{

    public static BookDTO bookEntityToBookDTO(BookEntity book)
    {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(AuthorMapper.authorEntityToAuthorDTO(book.getAuthor()));
        bookDTO.setTitle(book.getTitle());
        bookDTO.setNumberOfPages(book.getNumberOfPages());
        return bookDTO;
    }

    public static List<BookDTO> listOfBooksEntityToListOfBookDTO(List<BookEntity> bookEntities)
    {
        return bookEntities.stream().map(BookMapper::bookEntityToBookDTO).collect(Collectors.toList());
    }

    public static BookEntity bookDTOtoBookEntity(BookDTO bookDTO)
    {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setAuthor(AuthorMapper.authorDTOtoAuthorEntity(bookDTO.getAuthor()));
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setNumberOfPages(bookDTO.getNumberOfPages());
        return bookEntity;
    }

    public static List<BookEntity> listOfBooksDTOtoListOfBookEntities(List<BookDTO> bookDTOList)
    {
        return bookDTOList.stream().map(BookMapper::bookDTOtoBookEntity).collect(Collectors.toList());
    }
}
