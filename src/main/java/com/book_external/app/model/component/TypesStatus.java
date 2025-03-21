package com.book_external.app.model.component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypesStatus {
    SUCCESS("success"), ERROR("error");
    private String name;
}
