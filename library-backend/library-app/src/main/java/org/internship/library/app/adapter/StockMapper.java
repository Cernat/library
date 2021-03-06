package org.internship.library.app.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.internship.library.api.dto.StockDTO;
import org.internship.library.app.persistence.entity.StockEntity;

public class StockMapper
{

    public static StockDTO stockEntityToStockDTO(StockEntity stock)
    {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setNumberOfBooks(stock.getNumberOfBooks());
        stockDTO.setBookID(stock.getBookIDasBookEntity());
        return stockDTO;
    }

    public static List<StockDTO> listOfStocksEntityToListOfStockDTO(List<StockEntity> stockEntities)
    {
        return stockEntities.stream().map(StockMapper::stockEntityToStockDTO).collect(Collectors.toList());
    }

    public static StockEntity stockDTOtoStockEntity(StockDTO stockDTO)
    {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId(stockDTO.getId());
        stockEntity.setNumberOfBooks(stockDTO.getNumberOfBooks());
        stockEntity.setBookID(stockDTO.getBookID());
        return stockEntity;
    }

    public static List<StockEntity> listOfStocksDTOtoListOfStockEntities(List<StockDTO> stockDTOList)
    {
        return stockDTOList.stream().map(StockMapper::stockDTOtoStockEntity).collect(Collectors.toList());
    }
}
