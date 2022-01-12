package org.internship.library.app.controller;

import org.internship.library.api.dto.StockDTO;
import org.internship.library.app.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "stock")
public class StockController {

    Logger logger = LoggerFactory.getLogger(StockController.class);
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStock(@PathVariable Integer id)
    {
        logger.info("Retrieving stock with the id of: " + id);

        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(stockService.findById(id));
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<StockDTO>> getAllStocks()
    {
        logger.info("Retrieving all stocks: ");

        return ResponseEntity.ok(stockService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO)
    {
        logger.info("Creating stock with id: " + stockDTO.getId());

        StockDTO newStock = stockService.createStock(stockDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable String id, @RequestBody StockDTO stockDTO)
    {
        logger.info("Updating stock with id: " + stockDTO.getId());

        return ResponseEntity.status(HttpStatus.OK).body(stockService.updateStock(id, stockDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Integer id)
    {
        logger.info("Deleting stock with id: " + id);

        stockService.deleteStock(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
