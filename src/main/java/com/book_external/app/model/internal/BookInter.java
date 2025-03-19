package com.book_external.app.model.internal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_book")
public class BookInter {
    @Id
    @Column(name = "boo_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
}
