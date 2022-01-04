package org.internship.library.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.internship.library.api.dto.StockDTO;
import org.internship.library.app.adapter.StockMapper;
import org.internship.library.app.persistence.entity.StockEntity;
import org.internship.library.app.persistence.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService
{

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository)
    {
        this.stockRepository = stockRepository;
    }

    public List<StockDTO> findAll()
    {
        List<StockEntity> allStocks = stockRepository.findAll();
        return new ArrayList<>(StockMapper.listOfStocksEntityToListOfStockDTO(allStocks));
    }

    public StockDTO findById(Integer id)
    {
        Optional<StockEntity> optionalStockEntity = stockRepository.findById(id);
        return StockMapper.stockEntityToStockDTO(optionalStockEntity.orElseThrow(NoSuchElementException::new));
    }

    public StockDTO createStock(StockDTO stock)
    {
        StockEntity newStock = StockMapper.stockDTOtoStockEntity(stock);
        return StockMapper.stockEntityToStockDTO(stockRepository.save(newStock));
    }

    // TODO Use book id instead of stock id
    public StockDTO updateStock(Integer id, StockDTO stock)
    {
        Optional<StockEntity> updateStock =
            Optional.of(stockRepository.findById(id).orElseThrow(NoSuchElementException::new));
        return StockMapper.stockEntityToStockDTO(stockRepository.save(StockMapper.stockDTOtoStockEntity(stock)));
    }

    public void deleteStock(Integer id)
    {
        stockRepository.deleteById(id);
    }
}
