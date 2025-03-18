package com.book_external.app.model.internal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_book")
public class BookInter {
    @Id
    @Column(name = "boo_id")
    private Integer id;
}
