package app.e_market_services.categories.controller;

import app.e_market_services.categories.dto.response.Category;
import app.e_market_services.categories.service.CategoryService;
import app.e_market_services.common.constant.HttpStatusDesc;
import app.e_market_services.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category/v1")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<Category>>> findAllCategories() {
        return ResponseEntity.ok()
                .body(ApiResponse
                        .<List<Category>>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(categoryService.findAllCategories())
                        .build());
    }
}
