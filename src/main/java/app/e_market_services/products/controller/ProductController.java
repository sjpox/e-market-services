package app.e_market_services.products.controller;

import java.util.List;

import app.e_market_services.common.constant.HttpStatusDesc;
import app.e_market_services.common.response.ApiResponse;
import app.e_market_services.products.dto.response.ProductDetails;
import app.e_market_services.products.dto.response.Products;
import app.e_market_services.products.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/product/v1")
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    public ResponseEntity<ApiResponse<List<Products>>> findAllProducts() throws JsonProcessingException {
        return ResponseEntity.ok()
                .body(ApiResponse
                        .<List<Products>>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(productService.findAll())
                        .build());
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<Products>>> findProductLists() throws JsonProcessingException {
        logger.info("controller > findProductLists");
        return ResponseEntity.ok()
                .body(ApiResponse
                        .<List<Products>>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(productService.findProductLists())
                        .build());
    }

    @GetMapping("/productDetails")
    public ResponseEntity<ApiResponse<ProductDetails>> findProductById(@RequestParam("productId") String productId) {
        logger.info("controller > findProductById");
        return ResponseEntity.ok()
                .body(ApiResponse.<ProductDetails>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(productService.findProductById(productId))
                        .build());

    }
}
