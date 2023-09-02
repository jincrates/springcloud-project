package me.jincrates.api.ecommerce.api.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.api.ecommerce.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository repository;
}
