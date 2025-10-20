package app.e_market_services.product.controller;

import java.util.List;

import app.e_market_services.common.constant.HttpStatusDesc;
import app.e_market_services.common.response.ApiResponse;
import app.e_market_services.product.dto.ProductResponseDto;
import app.e_market_services.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> findAllProducts() {
        return ResponseEntity.ok()
                .body(ApiResponse
                        .<List<ProductResponseDto>>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(productService.findAll())
                        .build());
    }

}
