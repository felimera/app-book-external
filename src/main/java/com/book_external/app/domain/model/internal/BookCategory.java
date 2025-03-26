package com.book_external.app.domain.model.internal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "tbl_book_category")
public class BookCategory {
    @Id
    @Column(name = "boca_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "boca_id_book")
    private BookInter bookInter;
    @ManyToOne
    @JoinColumn(name = "boca_id_category")
    private Category category;
}
