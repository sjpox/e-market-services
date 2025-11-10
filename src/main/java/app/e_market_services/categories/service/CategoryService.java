package app.e_market_services.categories.service;

import app.e_market_services.categories.dto.response.Category;
import app.e_market_services.categories.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> Category.builder()
                        .categoryId(category.getCategoryId())
                        .categoryName(category.getCategoryName())
                        .createdAt(category.getCreatedAt())
                        .updatedAt(category.getUpdatedAt())
                        .build())
                .toList();
    }
}
