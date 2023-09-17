package me.jincrates.ecommerce.product.application.port;

import me.jincrates.ecommerce.product.domain.Product;
import me.jincrates.ecommerce.product.domain.Stock;

public interface StockPort {

    Stock saveStock(Stock stock);

    Stock findStockByProduct(Product product);

    void deleteAllStockInBatch();
}
