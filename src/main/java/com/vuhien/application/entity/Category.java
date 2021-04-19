package com.vuhien.application.entity;

import com.vuhien.application.model.dto.ChartDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SqlResultSetMappings(
        value = {
                @SqlResultSetMapping(
                        name = "chartDTO",
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
        name = "getListProductOrderByCategory",
        resultSetMapping = "chartDTO",
        query = "SELECT c.name as label, SUM(o.quantity) as value FROM category c " +
                "INNER JOIN product_category pc ON pc.category_id = c.id " +
                "INNER JOIN product p ON pc.product_id = p.id " +
                "INNER JOIN orders o ON o.product_id = p.id " +
                "WHERE o.status = 3 " +
                "GROUP BY c.id"
)

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",nullable = false,length = 300)
    private String name;
    @Column(name = "slug",nullable = false)
    private String slug;
//    @Column(name = "description")
//    private String description;
    @Column(name = "orders")
    private int order;
    @Column(name = "status",columnDefinition = "BOOLEAN")
    private boolean status;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "modified_at")
    private Timestamp modifiedAt;

//    @ManyToMany(mappedBy = "categories")
//    private List<Product> products;
}
