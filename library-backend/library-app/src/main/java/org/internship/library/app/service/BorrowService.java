package org.internship.library.app.service;

import org.internship.library.api.dto.BorrowDTO;
import org.internship.library.app.adapter.BorrowMapper;
import org.internship.library.app.persistence.entity.BorrowEntity;
import org.internship.library.app.persistence.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BorrowService {

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    private final BorrowRepository borrowRepository;

    public List<BorrowDTO> findAll()
    {
        List<BorrowEntity> allBorrows = borrowRepository.findAll();
        return new ArrayList<>(BorrowMapper.listOfBorrowsEntityToListOfBorrowDTO(allBorrows));
    }

    public BorrowDTO findById(Integer id)
    {
        Optional<BorrowEntity> optionalBorrowEntity = borrowRepository.findById(id);
        return BorrowMapper.borrowEntityToBorrowDTO(optionalBorrowEntity.orElseThrow(NoSuchElementException::new));
    }

    public BorrowDTO createBorrow(BorrowDTO borrow)
    {
        BorrowEntity newBorrow = BorrowMapper.borrowDTOtoBorrowEntity(borrow);
        return BorrowMapper.borrowEntityToBorrowDTO(borrowRepository.save(newBorrow));
    }

    public BorrowDTO updateBorrow(Integer id, BorrowDTO borrow)
    {
        Optional<BorrowEntity> updateBorrow =
                Optional.of(borrowRepository.findById(id).orElseThrow(NoSuchElementException::new));
        return BorrowMapper.borrowEntityToBorrowDTO(borrowRepository.save(BorrowMapper.borrowDTOtoBorrowEntity(borrow)));
    }

    public void deleteBorrow(Integer id)
    {
        borrowRepository.deleteById(id);
    }
}
