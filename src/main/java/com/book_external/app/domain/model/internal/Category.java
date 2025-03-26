package com.book_external.app.domain.model.internal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "tbl_category")
public class Category {
    @Id
    @Column(name = "cat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cat_name", nullable = false)
    private String nameCategory;
    @Column(name = "cat_description")
    private String descriptionCategory;
}
