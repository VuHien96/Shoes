package com.vuhien.application.controller.admin;

import com.vuhien.application.model.dto.StatisticDTO;
import com.vuhien.application.model.request.FilterDayByDay;
import com.vuhien.application.repository.StatisticRepository;
import com.vuhien.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private PostService postService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private StatisticRepository statisticRepository;

    @GetMapping("/admin")
    public String dashboard(Model model){
        return "admin/index";
    }

    @GetMapping("/api/admin/count/posts")
    public ResponseEntity<Object> getCountPost(){
        long countPosts = postService.getCountPost();
        return ResponseEntity.ok(countPosts);
    }

    @GetMapping("/api/admin/count/products")
    public ResponseEntity<Object> getCountProduct(){
        long countProducts = productService.getCountProduct();
        return ResponseEntity.ok(countProducts);
    }

    @GetMapping("/api/admin/count/orders")
    public ResponseEntity<Object> getCountOrders(){
        long countOrders = orderService.getCountOrder();
        return ResponseEntity.ok(countOrders);
    }

    @GetMapping("/api/admin/count/categories")
    public ResponseEntity<Object> getCountCategories(){
        long countCategories = categoryService.getCountCategories();
        return ResponseEntity.ok(countCategories);
    }

    @GetMapping("/api/admin/count/brands")
    public ResponseEntity<Object> getCountBrands(){
        long countBrands = brandService.getCountBrands();
        return ResponseEntity.ok(countBrands);
    }

    @GetMapping("/api/admin/statistics")
    public ResponseEntity<Object> getStatistic30Day(){
        List<StatisticDTO> statistics = statisticRepository.getStatistic30Day();
        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/api/admin/statistics")
    public ResponseEntity<Object> getStatisticDayByDay(@RequestBody FilterDayByDay filterDayByDay){
        List<StatisticDTO> statisticDTOS = statisticRepository.getStatisticDayByDay(filterDayByDay.getToDate(),filterDayByDay.getFromDate());
        return ResponseEntity.ok(statisticDTOS);
    }
}
