//package com.txt.elasticsearch.service;
//
//import com.txt.elasticsearch.entities.Product;
//import com.txt.elasticsearch.repository.ProductRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class SearchService {
//
//    private ProductRepository productRepository;
//    private ElasticsearchOperations elasticsearchOperations;
//
//    @Autowired
//    public SearchService(ProductRepository productRepository, ElasticsearchOperations elasticsearchOperations) {
//        super();
//        this.productRepository = productRepository;
//        this.elasticsearchOperations = elasticsearchOperations;
//    }
//
//    public List<Product> fetchProductNames(final String name) {
//        return productRepository.findByManufacturerAndCategory(name, "");
//    }
//
//    public List<Product> fetchProductNamesContaining(final String name) {
//        return productRepository.findByNameContaining(name);
//    }
//
//}
