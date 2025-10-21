package app.e_market_services.products.service;

import app.e_market_services.products.dto.ProductResponseDto;
import app.e_market_services.products.repository.ProductsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<ProductResponseDto> findAll() {
        return productsRepository.findAll()
            .stream()
            .map(e -> new ObjectMapper().convertValue(e, ProductResponseDto.class))
            .toList();
    }
}
