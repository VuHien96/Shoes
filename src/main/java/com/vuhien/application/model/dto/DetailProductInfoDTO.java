package com.vuhien.application.model.dto;

import com.vuhien.application.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailProductInfoDTO {
    private String id;

    private String name;

    private String slug;

    private long price;

    private int views;

    private long totalSold;

    private ArrayList<String> productImages;

    private long promotionPrice;

    private String couponCode;

    private String description;

    private Brand brand;
}
