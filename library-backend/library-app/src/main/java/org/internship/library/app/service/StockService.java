package org.internship.library.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.internship.library.api.dto.BookDTO;
import org.internship.library.api.dto.StockDTO;
import org.internship.library.app.adapter.StockMapper;
import org.internship.library.app.persistence.entity.BookEntity;
import org.internship.library.app.persistence.entity.StockEntity;
import org.internship.library.app.persistence.repository.BookRepositoryImpl;
import org.internship.library.app.persistence.repository.BookSpringProvidedRepository;
import org.internship.library.app.persistence.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService
{

    private final StockRepository stockRepository;
    private final BookRepositoryImpl bookRepository;

    public StockService(StockRepository stockRepository, BookRepositoryImpl bookRepository)
    {
        this.stockRepository = stockRepository;
        this.bookRepository = bookRepository;
    }

    public List<StockDTO> findAll()
    {
        List<StockEntity> allStocks = stockRepository.findAll();
        return new ArrayList<>(StockMapper.listOfStocksEntityToListOfStockDTO(allStocks));
    }

    public StockDTO findById(Integer stockId)
    {
        Optional<StockEntity> optionalStockEntity = stockRepository.findById(stockId);
        return StockMapper.stockEntityToStockDTO(optionalStockEntity.orElseThrow(NoSuchElementException::new));
    }

    public StockDTO createStock(StockDTO stock)
    {
        StockEntity newStock = StockMapper.stockDTOtoStockEntity(stock);
        return StockMapper.stockEntityToStockDTO(stockRepository.save(newStock));
    }

    public StockDTO updateStock(String bookId, StockDTO stock)
    {
        BookDTO findBook = bookRepository.findBookById(bookId);
        return StockMapper.stockEntityToStockDTO(stockRepository.save(StockMapper.stockDTOtoStockEntity(stock)));
    }

    public void deleteStock(Integer stockId)
    {
        stockRepository.deleteById(stockId);
    }
}
