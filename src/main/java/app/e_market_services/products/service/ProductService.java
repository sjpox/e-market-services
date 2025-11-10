package app.e_market_services.products.service;

import app.e_market_services.categories.dto.Category;
import app.e_market_services.products.dto.response.ProductDetails;
import app.e_market_services.products.dto.response.Products;
import app.e_market_services.products.repository.ProductsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductsRepository productsRepository;
    private final ObjectMapper objectMapper;
    public ProductService(ProductsRepository productsRepository,
                          ObjectMapper objectMapper) {
        this.productsRepository = productsRepository;
        this.objectMapper = objectMapper;
    }

    public List<Products> findAll() {
        return productsRepository.findAllWithCategoriesAndMerchants().stream()
                .map(product -> Products.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .merchantName(product.getMerchants() != null ? product.getMerchants().getMerchantName() : null)
                        .categories(new HashSet<>(product.getCategories()).stream()
                                .map(cat -> new Category(cat.getCategoryId(), cat.getCategoryName()))
                                .collect(Collectors.toSet()))
                        .build())
                .toList();
    }

    public List<Products> findProductLists() {
        logger.info("service > findProductLists");
        return productsRepository.findAll().stream()
                .map(product -> {
                    Set<Category> categories = product.getCategories().stream()
                            .map(cat -> new Category(cat.getCategoryId(), cat.getCategoryName()))
                            .collect(Collectors.toSet());

                    return Products.builder()
                            .productId(product.getProductId())
                            .productName(product.getProductName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .categories(categories)
                            .build();
                })
                .toList();
    }

    public ProductDetails findProductById(String productId) {
        app.e_market_services.products.model.Products product = productsRepository.findById(productId).orElse(null);
        if (Objects.isNull(product))
            return null;

        return ProductDetails.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }
}
