package org.internship.library.app.service;

import org.internship.library.api.dto.AuthorDTO;
import org.internship.library.api.dto.AuthorDTO;
import org.internship.library.app.adapter.AuthorMapper;
import org.internship.library.app.adapter.UserMapper;
import org.internship.library.app.persistence.entity.AuthorEntity;
import org.internship.library.app.persistence.entity.UserEntity;
import org.internship.library.app.persistence.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> findAll()
    {
        List<AuthorEntity> allAuthors = authorRepository.findAll();
        return new ArrayList<>(AuthorMapper.listOfAuthorsEntityToListOfAuthorDTO(allAuthors));
    }

    public AuthorDTO findById(Integer authorId)
    {
        Optional<AuthorEntity> optionalAuthorEntity = authorRepository.findById(authorId);
        return AuthorMapper.authorEntityToAuthorDTO(optionalAuthorEntity.orElseThrow(NoSuchElementException::new));
    }

    public AuthorDTO createAuthor(AuthorDTO author)
    {
        AuthorEntity newAuthor = AuthorMapper.authorDTOtoAuthorEntity(author);
        return AuthorMapper.authorEntityToAuthorDTO(authorRepository.save(newAuthor));
    }

    public AuthorDTO updateAuthor(Integer authorId, AuthorDTO author)
    {
        Optional<AuthorEntity> updateAuthor =
                Optional.of(authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new));
        return AuthorMapper.authorEntityToAuthorDTO(authorRepository.save(AuthorMapper.authorDTOtoAuthorEntity(author)));
    }

    public void deleteAuthor(Integer authorId)
    {
        authorRepository.deleteById(authorId);
    }
}