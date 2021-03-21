package com.vuhien.application.repository;

import com.vuhien.application.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByName(String name);

    @Query(value = "SELECT * FROM product p " +
            "INNER JOIN product_category pc ON p.id = pc.product_id " +
            "INNER JOIN category c ON c.id = pc.category_id " +
            "WHERE p.id LIKE CONCAT('%',?1,'%') " +
            "AND p.name LIKE CONCAT('%',?2,'%') " +
            "AND c.id LIKE CONCAT('%',?3,'%') " +
            "AND p.brand_id LIKE CONCAT('%',?4,'%')", nativeQuery = true)
    Page<Product> adminGetListProducts(String id, String name, String category, String brand, Pageable pageable);
}
