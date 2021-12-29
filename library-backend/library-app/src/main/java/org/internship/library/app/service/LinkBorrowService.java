package org.internship.library.app.service;

import org.internship.library.api.dto.LinkBorrowDTO;
import org.internship.library.app.adapter.LinkBorrowMapper;
import org.internship.library.app.persistence.entity.LinkBorrowEntity;
import org.internship.library.app.persistence.repository.LinkBorrowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LinkBorrowService {

    private final LinkBorrowRepository linkBorrowRepository;

    public LinkBorrowService(LinkBorrowRepository linkBorrowRepository) {
        this.linkBorrowRepository = linkBorrowRepository;
    }

    public List<LinkBorrowDTO> findAll()
    {
        List<LinkBorrowEntity> allLinkBorrows = linkBorrowRepository.findAll();
        return new ArrayList<>(LinkBorrowMapper.listOfLinkBorrowsEntityToListOfLinkBorrowDTO(allLinkBorrows));
    }

    public LinkBorrowDTO findById(Integer id)
    {
        Optional<LinkBorrowEntity> optionalLinkBorrowEntity = linkBorrowRepository.findById(id);
        return LinkBorrowMapper.linkBorrowEntityToLinkBorrowDTO(optionalLinkBorrowEntity.orElseThrow(NoSuchElementException::new));
    }

    public LinkBorrowDTO createLinkBorrow(LinkBorrowDTO linkBorrow)
    {
        LinkBorrowEntity newLinkBorrow = LinkBorrowMapper.linkBorrowDTOtolinkBorrowEntity(linkBorrow);
        return LinkBorrowMapper.linkBorrowEntityToLinkBorrowDTO(linkBorrowRepository.save(newLinkBorrow));
    }

    public LinkBorrowDTO updateLinkBorrow(Integer id, LinkBorrowDTO linkBorrow)
    {
        Optional<LinkBorrowEntity> updateLinkBorrow =
                Optional.of(linkBorrowRepository.findById(id).orElseThrow(NoSuchElementException::new));
        return LinkBorrowMapper.linkBorrowEntityToLinkBorrowDTO(linkBorrowRepository.save(LinkBorrowMapper.linkBorrowDTOtolinkBorrowEntity(linkBorrow)));
    }

    public void deleteLinkBorrow(Integer id)
    {
        linkBorrowRepository.deleteById(id);
    }
}