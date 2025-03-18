package com.book_external.app.model.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "tbl_sub_category")
public class SubCategory {
    @Id
    @Column(name = "sub_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sub_name", nullable = false)
    private String nameSubCategory;
    @Column(name = "sub_description")
    private String descriptionSubCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_id_category")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
}
