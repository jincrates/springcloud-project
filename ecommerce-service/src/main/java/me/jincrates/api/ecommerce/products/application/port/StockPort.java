package me.jincrates.api.ecommerce.products.application.port;

import me.jincrates.api.ecommerce.products.domain.Stock;

public interface StockPort {
    Stock saveStock(Stock stock);
}
