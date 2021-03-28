package com.vuhien.application.service;

import com.vuhien.application.entity.Product;
import com.vuhien.application.entity.ProductSize;
import com.vuhien.application.model.dto.DetailProductInfoDTO;
import com.vuhien.application.model.dto.ProductInfoDTO;
import com.vuhien.application.model.request.CreateProductRequest;
import com.vuhien.application.model.request.CreateSizeCountRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    //Lấy sản phẩm
    Page<Product> adminGetListProduct(String id, String name, String category, String brand, Integer page);

    //Tạo sản phẩm
    Product createProduct(CreateProductRequest createProductRequest);

    //Sửa sản phẩm
    void updateProduct(CreateProductRequest createProductRequest, String id);

    //Lấy chi tiết sản phẩm
    Product getProductById(String id);

    //Xóa sản phẩm theo id
    void deleteProduct(String[] ids);

    //Lấy sản phẩm bán nhiều nhất
    List<ProductInfoDTO> getListBestSellProducts();

    //Lấy sản phẩm mới nhất
    List<ProductInfoDTO> getListNewProducts();

    //Lấy sản phẩm xem nhiều
    List<ProductInfoDTO> getListViewProducts();

    //Lấy chi tiết sản phẩm theo id
    DetailProductInfoDTO getDetailProductById(String id);

    //Lấy sản phẩm liên quan
    List<ProductInfoDTO> getRelatedProducts(String id);

    //Lấy size có sẵn
    List<Integer> getListAvailableSize(String id);

    //Nhập số lượng theo size
    void createSizeCount(CreateSizeCountRequest createSizeCountRequest);

    //Lấy size của sản phẩm
    List<ProductSize> getListSizeOfProduct(String id);

}
