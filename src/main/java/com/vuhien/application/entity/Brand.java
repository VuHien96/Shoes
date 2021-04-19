package com.vuhien.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vuhien.application.model.dto.ChartDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
@SqlResultSetMappings(
        value = {
                @SqlResultSetMapping(
                        name = "chartBrand",
                        classes = @ConstructorResult(
                                targetClass = ChartDTO.class,
                                columns = {
                                        @ColumnResult(name = "label",type = String.class),
                                        @ColumnResult(name = "value",type = Integer.class)
                                }
                        )
                )
        }
)
@NamedNativeQuery(
        name = "getListProductOrderByBrands",
        resultSetMapping = "chartBrand",
        query = "SELECT b.name as label, SUM(o.quantity) as value FROM brand b " +
                "INNER JOIN product p ON p.brand_id = b.id " +
                "INNER JOIN orders o ON o.product_id = p.id " +
                "WHERE o.status = 3 " +
                "GROUP BY b.id "
)

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "status",columnDefinition = "BOOLEAN")
    private boolean status;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "modified_at")
    private Timestamp modifiedAt;
    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private List<Product> products;
}
