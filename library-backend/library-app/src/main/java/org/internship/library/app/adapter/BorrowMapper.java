package org.internship.library.app.adapter;

import org.internship.library.api.dto.AuthorDTO;
import org.internship.library.api.dto.BorrowDTO;
import org.internship.library.app.persistence.entity.AuthorEntity;
import org.internship.library.app.persistence.entity.BorrowEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowMapper {

    public static BorrowDTO borrowEntityToBorrowDTO(BorrowEntity borrow)
    {
        BorrowDTO borrowDTO = new BorrowDTO();
        borrowDTO.setId(borrow.getId());
        borrowDTO.setDate_borrowed(borrow.getDate_borrowed());
        borrowDTO.setTo_be_returned(borrow.getTo_be_returned());
        borrowDTO.setReturned_on_time(borrow.isReturned_on_time());
        borrowDTO.setReturned(borrow.isReturned());
        borrowDTO.setLinkBorrow(LinkBorrowMapper.linkBorrowEntityToLinkBorrowDTO(borrow.getLinkBorrow()));
        return borrowDTO;
    }

    public static List<BorrowDTO> listOfBorrowsEntityToListOfBorrowDTO(List<BorrowEntity> borrowEntities)
    {
        return borrowEntities.stream().map(BorrowMapper::borrowEntityToBorrowDTO).collect(Collectors.toList());
    }

    public static BorrowEntity borrowDTOtoBorrowEntity(BorrowDTO borrowDTO)
    {
        BorrowEntity borrowEntity = new BorrowEntity();
        borrowEntity.setId(borrowDTO.getId());
        borrowEntity.setDate_borrowed(borrowDTO.getDate_borrowed());
        borrowEntity.setTo_be_returned(borrowDTO.getTo_be_returned());
        borrowEntity.setReturned_on_time(borrowDTO.isReturned_on_time());
        borrowEntity.setReturned(borrowDTO.isReturned());
        borrowEntity.setLinkBorrow(LinkBorrowMapper.linkBorrowDTOtolinkBorrowEntity(borrowDTO.getLinkBorrow()));
        return borrowEntity;
    }

    public static List<BorrowEntity> listOfBorrowsDTOtoListOfBorrowEntities(List<BorrowDTO> borrowDTOList)
    {
        return borrowDTOList.stream().map(BorrowMapper::borrowDTOtoBorrowEntity).collect(Collectors.toList());
    }
}
