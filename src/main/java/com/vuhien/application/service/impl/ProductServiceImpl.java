package com.vuhien.application.service.impl;

import com.vuhien.application.entity.Product;
import com.vuhien.application.entity.ProductSize;
import com.vuhien.application.exception.BadRequestException;
import com.vuhien.application.exception.InternalServerException;
import com.vuhien.application.exception.NotFoundException;
import com.vuhien.application.model.dto.DetailProductInfoDTO;
import com.vuhien.application.model.dto.ProductInfoDTO;
import com.vuhien.application.model.mapper.ProductMapper;
import com.vuhien.application.model.request.CreateProductRequest;
import com.vuhien.application.model.request.CreateSizeCountRequest;
import com.vuhien.application.repository.ProductRepository;
import com.vuhien.application.repository.ProductSizeRepository;
import com.vuhien.application.service.ProductService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static com.vuhien.application.config.Contant.*;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Override
    public Page<Product> adminGetListProduct(String id, String name, String category, String brand, Integer page) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, LIMIT_PRODUCT, Sort.by("created_at").descending());
        return productRepository.adminGetListProducts(id, name, category, brand, pageable);
    }

    @Override
    public Product createProduct(CreateProductRequest createProductRequest) {
        //Kiểm tra có danh muc
        if (createProductRequest.getCategoryIds().isEmpty()) {
            throw new BadRequestException("Danh mục trống!");
        }
        //Kiểm tra có ảnh sản phẩm
        if (createProductRequest.getImages().isEmpty()) {
            throw new BadRequestException("Ảnh sản phẩm trống!");
        }
        //Kiểm tra tên sản phẩm trùng
        Product product = productRepository.findByName(createProductRequest.getName());
        if (product != null) {
            throw new BadRequestException("Tên sản phẩm đã tồn tại trong hệ thống, Vui lòng chọn tên khác!");
        }

        product = ProductMapper.toProduct(createProductRequest);
        //Sinh id
        String id = RandomStringUtils.randomAlphanumeric(6);
        product.setId(id);
        product.setTotalSold(0);
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            productRepository.save(product);
        } catch (Exception ex) {
            throw new InternalServerException("Lỗi khi thêm sản phẩm");
        }
        return product;
    }

    @Override
    public void updateProduct(CreateProductRequest createProductRequest, String id) {
        //Kiểm tra sản phẩm có tồn tại
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Không tìm thấy sản phẩm!");
        }

        //Kiểm tra tên sản phẩm có tồn tại
        Product rs = productRepository.findByName(createProductRequest.getName());
        if (rs != null) {
            if (!createProductRequest.getId().equals(rs.getId()))
                throw new BadRequestException("Tên sản phẩm đã tồn tại trong hệ thống, Vui lòng chọn tên khác!");
        }

        //Kiểm tra có danh muc
        if (createProductRequest.getCategoryIds().isEmpty()) {
            throw new BadRequestException("Danh mục trống!");
        }

        //Kiểm tra có ảnh sản phẩm
        if (createProductRequest.getImages().isEmpty()) {
            throw new BadRequestException("Ảnh sản phẩm trống!");
        }

        Product result = ProductMapper.toProduct(createProductRequest);
        result.setId(id);
        result.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        try {
            productRepository.save(result);
        } catch (Exception e) {
            throw new InternalServerException("Có lỗi khi sửa sản phẩm!");
        }
    }

    @Override
    public Product getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Không tìm thấy sản phẩm trong hệ thống!");
        }
        return product.get();
    }

    @Override
    public void deleteProduct(String[] ids) {
        for (String id : ids) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public List<ProductInfoDTO> getListBestSellProducts() {
        return productRepository.getListBestSellProducts(LIMIT_PRODUCT_SELL);
    }

    @Override
    public List<ProductInfoDTO> getListNewProducts() {
        return productRepository.getListNewProducts(LIMIT_PRODUCT_NEW);
    }

    @Override
    public List<ProductInfoDTO> getListViewProducts() {
        return productRepository.getListViewProducts(LIMIT_PRODUCT_VIEW);
    }

    @Override
    public DetailProductInfoDTO getDetailProductById(String id) {
        Optional<Product> rs = productRepository.findById(id);
        if (rs.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }
        Product product = rs.get();

        if (product.getStatus() != 1) {
            throw new NotFoundException("Sản phâm không tồn tại");
        }
        DetailProductInfoDTO dto = new DetailProductInfoDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getSalePrice());
        dto.setSlug(product.getSlug());
        dto.setTotalSold(product.getTotalSold());
        dto.setDescription(product.getDescription());
        dto.setBrand(product.getBrand());
        dto.setProductImages(product.getImages());

        return dto;
    }

    @Override
    public List<ProductInfoDTO> getRelatedProducts(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }

        List<ProductInfoDTO> products = productRepository.getRelatedProducts(id, LIMIT_PRODUCT_RELATED);
        return products;
    }

    @Override
    public List<Integer> getListAvailableSize(String id) {
        return productSizeRepository.findAllSizeOfProduct(id);
    }

    @Override
    public void createSizeCount(CreateSizeCountRequest createSizeCountRequest) {

        //Kiểm trả size
        boolean isValid = false;
        for (int size : SIZE_VN) {
            if (size == createSizeCountRequest.getSize()) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new BadRequestException("Size không hợp lệ");
        }

        //Kiểm trả sản phẩm có tồn tại
        Optional<Product> product = productRepository.findById(createSizeCountRequest.getProductId());
        if (product.isEmpty()) {
            throw new NotFoundException("Không tìm thấy sản phẩm trong hệ thống!");
        }

        ProductSize productSize = new ProductSize();
        productSize.setProductId(createSizeCountRequest.getProductId());
        productSize.setSize(createSizeCountRequest.getSize());
        productSize.setQuantity(createSizeCountRequest.getCount());

        productSizeRepository.save(productSize);
    }

    @Override
    public List<ProductSize> getListSizeOfProduct(String id) {
        return productSizeRepository.findByProductId(id);
    }
}
