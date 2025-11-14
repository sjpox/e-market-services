package app.e_market_services.categories.controller;

import app.e_market_services.categories.dto.request.CategoryRequest;
import app.e_market_services.categories.dto.response.CategoryResponse;
import app.e_market_services.categories.service.CategoryService;
import app.e_market_services.common.constant.HttpStatusDesc;
import app.e_market_services.common.response.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> findAllCategories() {
        return ResponseEntity.ok()
                .body(ApiResponse
                        .<List<CategoryResponse>>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(categoryService.findAllCategories())
                        .build());
    }

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody CategoryRequest categoryRequest) throws BadRequestException {
        return ResponseEntity.ok()
                . body(ApiResponse.<CategoryResponse>builder()
                        .status(HttpStatusDesc.SUCCESS)
                        .result(categoryService.createCategory(categoryRequest))
                        .build());
    }
}
