package app.e_market_services.products.controller;

import java.util.List;

import app.e_market_services.common.constant.HttpStatusDesc;
import app.e_market_services.common.response.ApiResponse;
import app.e_market_services.products.dto.ProductsResponse;
import app.e_market_services.products.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

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

}
