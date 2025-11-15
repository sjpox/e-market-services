package app.e_market_services.categories.service;

import app.e_market_services.categories.dto.request.CategoryRequest;
import app.e_market_services.categories.dto.response.CategoryResponse;
import app.e_market_services.categories.model.Category;
import app.e_market_services.categories.repository.CategoryRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> findAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> CategoryResponse.builder()
                        .categoryId(category.getCategoryId())
                        .categoryName(category.getCategoryName())
                        .description(category.getDescription())
                        .createdAt(category.getCreatedAt())
                        .updatedAt(category.getUpdatedAt())
                        .build())
                .toList();
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) throws BadRequestException {
        if (Objects.isNull(categoryRequest))
            throw new BadRequestException("Paramater is required.");
        Category savedCategory = categoryRepository.save(Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .description(categoryRequest.getDescription())
                .build());

        return CategoryResponse.builder()
                .categoryName(savedCategory.getCategoryName())
                .description(savedCategory.getDescription())
                .build();
    }

    public CategoryResponse updateCategory(String id, CategoryRequest categoryRequest) {
        Category updateCategory = categoryRepository.save(
                Category.builder()
                        .categoryName(categoryRequest.getCategoryName())
                        .description(categoryRequest.getDescription())
                        .categoryId(id)
                        .build());

        return CategoryResponse.builder()
                .categoryName(updateCategory.getCategoryName())
                .description(updateCategory.getDescription())
                .build();
    }
}
