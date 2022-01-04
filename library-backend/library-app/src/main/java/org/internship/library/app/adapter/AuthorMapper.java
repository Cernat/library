package org.internship.library.app.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.internship.library.api.dto.AuthorDTO;
import org.internship.library.app.persistence.entity.AuthorEntity;

public class AuthorMapper
{

    public static AuthorDTO authorEntityToAuthorDTO(AuthorEntity author)
    {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirst_name(author.getFirst_name());
        authorDTO.setLast_name(author.getLast_name());
        return authorDTO;
    }

    public static List<AuthorDTO> listOfAuthorsEntityToListOfAuthorDTO(List<AuthorEntity> authorEntities)
    {
        return authorEntities.stream().map(AuthorMapper::authorEntityToAuthorDTO).collect(Collectors.toList());
    }

    public static AuthorEntity authorDTOtoAuthorEntity(AuthorDTO authorDTO)
    {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorDTO.getId());
        authorEntity.setFirst_name(authorDTO.getFirst_name());
        authorEntity.setLast_name(authorDTO.getLast_name());
        return authorEntity;
    }

    public static List<AuthorEntity> listOfAuthorsDTOtoListOfAuthorEntities(List<AuthorDTO> authorDTOList)
    {
        return authorDTOList.stream().map(AuthorMapper::authorDTOtoAuthorEntity).collect(Collectors.toList());
    }
}
