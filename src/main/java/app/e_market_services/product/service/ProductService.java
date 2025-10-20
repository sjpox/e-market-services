package app.e_market_services.product.service;

import app.e_market_services.product.dto.ProductResponseDto;
import app.e_market_services.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
            .stream()
            .map(e -> new ObjectMapper().convertValue(e, ProductResponseDto.class))
            .toList();
    }
}
