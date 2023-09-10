package com.txt.elasticsearch.service;

import com.txt.elasticsearch.entities.Product;
import com.txt.elasticsearch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSearchServiceWithRepo {

    private final ProductRepository productRepository;

    public void createProductIndexBulk(final List<Product> products) {
        productRepository.saveAll(products);
    }

    public void createProductIndex(final Product product) {
        productRepository.save(product);
    }

    public List<Product> findProductsByManufacturerAndCategory(final String manufacturer, final String category) {
        return productRepository.findByManufacturerAndCategory(manufacturer, category);
    }

    public List<Product> findByProductName(final String productName) {
        return productRepository.findByName(productName);
    }

    public List<Product> findByProductMatchingNames(final String productName) {
        return productRepository.findByNameContaining(productName);
    }

    public List<Product> fetchProductNames(final String name) {
        return productRepository.findByManufacturerAndCategory(name, "");
    }

    public List<Product> fetchProductNamesContaining(final String name) {
        return productRepository.findByNameContaining(name);
    }
}
