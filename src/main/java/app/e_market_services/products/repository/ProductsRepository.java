package app.e_market_services.products.repository;

import app.e_market_services.products.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, String> {
    @Query("""
           SELECT DISTINCT p 
           FROM Products p
           LEFT JOIN FETCH p.categories
           LEFT JOIN FETCH p.merchants
           """)
    List<Products> findAllWithCategoriesAndMerchants();
}
