package app.e_market_services.products.controller;

import java.util.List;

import app.e_market_services.common.constant.HttpStatusDesc;
import app.e_market_services.common.response.ApiResponse;
import app.e_market_services.products.dto.ProductsResponse;
import app.e_market_services.products.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductsResponse>>> findAllProducts() throws JsonProcessingException {
        return ResponseEntity.ok()
                .body(ApiResponse
                        .<List<ProductsResponse>>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(productService.findAll())
                        .build());
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ProductsResponse>>> findProductLists() throws JsonProcessingException {
        logger.info("controller > findProductLists");
        return ResponseEntity.ok()
                .body(ApiResponse
                        .<List<ProductsResponse>>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(productService.findProductLists())
                        .build());
    }
}
