package com.book_external.app.model.internal;

import com.book_external.app.model.external.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String nombreCategoria;
    @Column(name = "description")
    private String descripcionCategoria;
    @ManyToOne
    @JoinColumn(name = "id_father_cat")
    private Category categoriaPadre;
    @OneToMany(mappedBy = "id_father_cat")
    private List<Category> subcategories;
    @ManyToMany(mappedBy = "id_book")
    private List<Book> books;
}
