package org.internship.library.app.adapter;

import org.internship.library.api.dto.LinkBorrowDTO;
import org.internship.library.api.dto.StockDTO;
import org.internship.library.app.persistence.entity.LinkBorrowEntity;
import org.internship.library.app.persistence.entity.StockEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LinkBorrowMapper {

    public static LinkBorrowDTO linkBorrowEntityToLinkBorrowDTO(LinkBorrowEntity linkBorrow)
    {
        LinkBorrowDTO linkBorrowDTO = new LinkBorrowDTO();
        linkBorrowDTO.setId(linkBorrow.getId());
        linkBorrowDTO.setUser(UserMapper.userEntityToUserDTO(linkBorrow.getUser()));
        linkBorrowDTO.setBook(BookMapper.bookEntityToBookDTO(linkBorrow.getBook()));
        linkBorrowDTO.setBorrow(BorrowMapper.borrowEntityToBorrowDTO(linkBorrow.getBorrow()));
        return linkBorrowDTO;
    }

    public static List<LinkBorrowDTO> listOfLinkBorrowsEntityToListOfLinkBorrowDTO(List<LinkBorrowEntity> linkBorrowEntities)
    {
        return linkBorrowEntities.stream().map(LinkBorrowMapper::linkBorrowEntityToLinkBorrowDTO).collect(Collectors.toList());
    }

    public static LinkBorrowEntity linkBorrowDTOtolinkBorrowEntity(LinkBorrowDTO linkBorrowDTO)
    {
        LinkBorrowEntity linkBorrowEntity = new LinkBorrowEntity();
        linkBorrowEntity.setId(linkBorrowDTO.getId());
        linkBorrowEntity.setUser(UserMapper.userDTOtoUserEntity(linkBorrowDTO.getUser()));
        linkBorrowEntity.setBook(BookMapper.bookDTOtoBookEntity(linkBorrowDTO.getBook()));
        linkBorrowEntity.setBorrow(BorrowMapper.borrowDTOtoBorrowEntity(linkBorrowDTO.getBorrow()));
        return linkBorrowEntity;
    }

    public static List<LinkBorrowEntity> listOfLinkBorrowsDTOtoListOfLinkBorrowEntities(List<LinkBorrowDTO> linkBorrowDTOList)
    {
        return linkBorrowDTOList.stream().map(LinkBorrowMapper::linkBorrowDTOtolinkBorrowEntity).collect(Collectors.toList());
    }
}
