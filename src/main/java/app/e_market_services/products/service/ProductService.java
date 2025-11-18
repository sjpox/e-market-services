package app.e_market_services.products.service;

import app.e_market_services.categories.dto.CategoryDto;
import app.e_market_services.products.dto.request.ProductRequest;
import app.e_market_services.products.dto.response.ProductResponse;
import app.e_market_services.products.dto.response.Product;
import app.e_market_services.products.model.Products;
import app.e_market_services.products.repository.ProductsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

    public List<Product> findAllWithCategoriesAndMerchants() {
        return productsRepository.findAllWithCategoriesAndMerchants().stream()
                .map(product -> Product.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .merchantName(product.getMerchants() != null ? product.getMerchants().getMerchantName() : null)
                        .categories(new HashSet<>(product.getCategories()).stream()
                                .map(cat -> new CategoryDto(cat.getCategoryId(), cat.getCategoryName()))
                                .collect(Collectors.toSet()))
                        .build())
                .toList();
    }

    public List<Product> findProductLists() {
        logger.info("service > findProductLists");

        return productsRepository.findAll().stream()
                .map(product -> {
                    Set<CategoryDto> categories = product.getCategories().stream()
                            .map(cat -> new CategoryDto(cat.getCategoryId(), cat.getCategoryName()))
                            .collect(Collectors.toSet());

                    return Product.builder()
                            .productId(product.getProductId())
                            .productName(product.getProductName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .categories(categories)
                            .build();
                })
                .toList();
    }

    public ProductResponse findProductById(String productId) {
        Products product = productsRepository.findById(productId).orElse(null);
        if (Objects.isNull(product))
            return null;

        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) throws RuntimeException {
        Products getProduct = productsRepository.findById(id).orElseThrow(RuntimeException::new);
        if (Objects.isNull(getProduct))
            throw new RuntimeException("No product found");

        Products request = Products.builder()
                .productName(StringUtils.isNotEmpty(productRequest.getProductName()) ? productRequest.getProductName() : getProduct.getProductName())
                .description(StringUtils.isNotEmpty(productRequest.getDescription()) ? productRequest.getDescription() : getProduct.getDescription())
                .price(ObjectUtils.isNotEmpty(productRequest.getPrice()) ? productRequest.getPrice() : getProduct.getPrice())
                .stockQuantity(ObjectUtils.isNotEmpty(productRequest.getStockQuantity()) ? productRequest.getStockQuantity() : getProduct.getStockQuantity())
                .merchants(getProduct.getMerchants())
                .productId(id).build();

        Products savedProduct = productsRepository.save(request);

        return ProductResponse.builder()
                .productName(savedProduct.getProductName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .stockQuantity(savedProduct.getStockQuantity())
                .build();
    }

    public Boolean deleteProduct(String id) {
        try {
            productsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
