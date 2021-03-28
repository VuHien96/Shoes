package com.vuhien.application.repository;

import com.vuhien.application.entity.Product;
import com.vuhien.application.model.dto.ProductInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    //Lấy sản phẩm theo tên
    Product findByName(String name);

    //Lấy tất cả sản phẩm
    @Query(value = "SELECT DISTINCT p.* FROM product p " +
            "INNER JOIN product_category pc ON p.id = pc.product_id " +
            "INNER JOIN category c ON c.id = pc.category_id " +
            "WHERE p.id LIKE CONCAT('%',?1,'%') " +
            "AND p.name LIKE CONCAT('%',?2,'%') " +
            "AND c.id LIKE CONCAT('%',?3,'%') " +
            "AND p.brand_id LIKE CONCAT('%',?4,'%')", nativeQuery = true)
    Page<Product> adminGetListProducts(String id, String name, String category, String brand, Pageable pageable);

//    @Query(value = "SELECT NEW com.vuhien.application.model.dto.ProductInfoDTO(p.id, p.name, p.slug, p.price ,p.images ->> '$[0]', p.total_sold) " +
//            "FROM product p " +
//            "WHERE p.status = 1 " +
//            "ORDER BY p.created_at DESC limit ?1",nativeQuery = true)
//    List<ProductInfoDTO> getListBestSellProducts(int limit);

    //Lấy sản phẩm được bán nhiều
    @Query(nativeQuery = true,name = "getListBestSellProducts")
    List<ProductInfoDTO> getListBestSellProducts(int limit);

    //Lấy sản phẩm mới nhất
    @Query(nativeQuery = true,name = "getListNewProducts")
    List<ProductInfoDTO> getListNewProducts(int limit);

    //Lấy sản phẩm được xem nhiều
    @Query(nativeQuery = true,name = "getListViewProducts")
    List<ProductInfoDTO> getListViewProducts(int limit);

    //Lấy sản phẩm liên quan
    @Query(nativeQuery = true, name = "getRelatedProducts")
    List<ProductInfoDTO> getRelatedProducts(String id, int limit);

}
