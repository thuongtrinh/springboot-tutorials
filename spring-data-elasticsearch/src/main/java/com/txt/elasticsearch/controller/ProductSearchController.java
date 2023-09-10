package com.txt.elasticsearch.controller;

import com.txt.elasticsearch.entities.Product;
import com.txt.elasticsearch.service.ProductSearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProductSearch API", description = "ProductSearch API")
@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductSearchController {

    private ProductSearchService searchService;

    @Autowired
    public ProductSearchController(ProductSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> fetchByNameOrDesc(@RequestParam(value = "q", required = false) String query) {
        log.info("searching by name {}", query);
        List<Product> products = searchService.processSearch(query);
        log.info("products {}", products);
        return products;
    }

    @GetMapping("/suggestions")
    @ResponseBody
    public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {
        log.info("fetch suggests {}", query);
        List<String> suggests = searchService.fetchSuggestions(query);
        log.info("suggests {}", suggests);
        return suggests;
    }

    @PostMapping("/create-product")
    @ResponseBody
    public String createProductIndex(@RequestBody Product product) {
        log.info("createProductIndex {}", product);
        String documentId = searchService.createProductIndex(product);
        log.info("createProductIndex with documentId {}", documentId);
        return documentId;
    }

    @PostMapping("/create-product-list")
    @ResponseBody
    public List<IndexedObjectInformation> createProductIndexBulk(@RequestBody List<Product> products) {
        log.info("createProductIndex {}", products);
        List<IndexedObjectInformation> indexBulk = searchService.createProductIndexBulk(products);
        log.info("createProductIndex with documentId {}", indexBulk);
        return indexBulk;
    }

    @RequestMapping(value = "/get-by-product-name/{name}", method = RequestMethod.GET)
    public Object findByProductName(@PathVariable String name) {
        return searchService.findByProductName(name);
    }

    @RequestMapping(value = "/get-by-product-price/{price}", method = RequestMethod.GET)
    public Object findByProductPrice(@PathVariable Double price) {
        return searchService.findByProductPrice(price);
    }

    @RequestMapping(value = "/get-by-product-brand/{brand}", method = RequestMethod.GET)
    public Object findProductsByBrand(@PathVariable String brand) {
        return searchService.findProductsByBrand(brand);
    }

}
