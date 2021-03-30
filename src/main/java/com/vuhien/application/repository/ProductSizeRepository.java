package com.vuhien.application.repository;

import com.vuhien.application.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize,Long> {

    //Lấy size của sản phẩm
    @Query(nativeQuery = true,value = "SELECT ps.size FROM product_size ps WHERE ps.product_id = ?1 AND ps.quantity > 0")
    List<Integer> findAllSizeOfProduct(String id);

    List<ProductSize> findByProductId(String id);
}
