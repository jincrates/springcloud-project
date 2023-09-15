package me.jincrates.ecommerce.products.application.port;

import me.jincrates.ecommerce.products.domain.Product;
import me.jincrates.ecommerce.products.domain.Stock;

public interface StockPort {

    Stock saveStock(Stock stock);

    Stock findStockByProduct(Product product);
}
