package com.book_external.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppBookExternalApplicationTests {

    @Test
    void contextLoads() {
        AppBookExternalApplication myClass = new AppBookExternalApplication();
        Assertions.assertThat(myClass).isNotNull();
    }

}
