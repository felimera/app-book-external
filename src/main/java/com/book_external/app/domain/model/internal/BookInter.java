package com.book_external.app.domain.model.internal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_book")
public class BookInter {
    @Id
    @Column(name = "boo_id")
    private Integer id;
    @Column(name = "boo_name")
    private String name;
}
