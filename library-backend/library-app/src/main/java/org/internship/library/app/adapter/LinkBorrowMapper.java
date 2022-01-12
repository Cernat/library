package org.internship.library.app.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.internship.library.api.dto.LinkBorrowDTO;
import org.internship.library.app.persistence.entity.LinkBorrowEntity;

public class LinkBorrowMapper
{

    public static LinkBorrowDTO linkBorrowEntityToLinkBorrowDTO(LinkBorrowEntity linkBorrow)
    {
        LinkBorrowDTO linkBorrowDTO = new LinkBorrowDTO();
        linkBorrowDTO.setId(linkBorrow.getId());
        linkBorrowDTO.setUser(linkBorrow.getUserIDasUserEntity());
        linkBorrowDTO.setBook(linkBorrow.getBookIDasBookEntity());
        linkBorrowDTO.setBorrow(linkBorrow.getBorrowIDasBorrowEntity());
        return linkBorrowDTO;
    }

    public static List<LinkBorrowDTO> listOfLinkBorrowsEntityToListOfLinkBorrowDTO(
        List<LinkBorrowEntity> linkBorrowEntities)
    {
        return linkBorrowEntities.stream().map(LinkBorrowMapper::linkBorrowEntityToLinkBorrowDTO)
            .collect(Collectors.toList());
    }

    public static LinkBorrowEntity linkBorrowDTOtolinkBorrowEntity(LinkBorrowDTO linkBorrowDTO)
    {
        LinkBorrowEntity linkBorrowEntity = new LinkBorrowEntity();
        linkBorrowEntity.setId(linkBorrowDTO.getId());
        linkBorrowEntity.setUser(linkBorrowDTO.getUser());
        linkBorrowEntity.setBook(linkBorrowDTO.getBook());
        linkBorrowEntity.setBorrow(linkBorrowDTO.getBorrow());
        return linkBorrowEntity;
    }

    public static List<LinkBorrowEntity> listOfLinkBorrowsDTOtoListOfLinkBorrowEntities(
        List<LinkBorrowDTO> linkBorrowDTOList)
    {
        return linkBorrowDTOList.stream().map(LinkBorrowMapper::linkBorrowDTOtolinkBorrowEntity)
            .collect(Collectors.toList());
    }
}
