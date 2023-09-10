package com.txt.elasticsearch;

import com.txt.elasticsearch.entities.Product;
import com.txt.elasticsearch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.util.*;

//@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class ProductDataImportApplication {

    private static final String COMMA_DELIMITER = ",";
    private final ElasticsearchRestTemplate esOps;
    private final ProductRepository productRepo;

    public static void main(String[] args) {
        SpringApplication.run(ProductDataImportApplication.class, args);
    }

    @PreDestroy
    public void deleteIndex() {
        esOps.indexOps(Product.class).delete();
    }

    @PostConstruct
    public void buildIndex() {
        esOps.indexOps(Product.class).refresh();
        productRepo.deleteAll();
        productRepo.saveAll(prepareDataset());
    }

    private Collection<Product> prepareDataset() {
        Resource resource = new ClassPathResource("fashion-products.csv");
        List<Product> productList = new ArrayList<>();

        try (
                InputStream input = resource.getInputStream();
                Scanner scanner = new Scanner(resource.getInputStream())
        ) {
            int lineNo = 0;
            while (scanner.hasNextLine()) {
                ++lineNo;
                String line = scanner.nextLine();
                if (lineNo == 1) continue;
                Optional<Product> product = csvRowToProductMapper(line);
                if (product.isPresent())
                    productList.add(product.get());
            }
        } catch (Exception e) {
            log.error("File read error {}", e);
        }
        return productList;
    }

    private Optional<Product> csvRowToProductMapper(final String line) {
        try (
                Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                String name = rowScanner.next();
                String description = rowScanner.next();
                String manufacturer = rowScanner.next();
                return Optional.of(
                        Product.builder()
                                .name(name)
                                .description(description)
                                .manufacturer(manufacturer)
                                .build());
            }
        }
        return Optional.of(null);
    }

}
