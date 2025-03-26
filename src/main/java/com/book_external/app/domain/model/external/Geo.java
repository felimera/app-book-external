package com.book_external.app.domain.model.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Geo {
    private String lat;
    private String lng;
}
