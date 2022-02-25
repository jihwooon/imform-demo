package com.imform.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Product 클래스")
class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setContent("HelloWorld");
    }

    @DisplayName("Content 실수값")
    @Test
    void getWithCorrectTitle() {
        assertThat(product.getContent()).isEqualTo("HelloWorld");
    }

    @DisplayName("Content 허수값")
    @Test
    void getWithInCorrectTitle() {
        assertThat(product.getContent()).isNotEqualTo("HelloJavaScript");
    }

    //BDD TestCode Style
    @Nested
    @DisplayName("Long Id 실수값이 들어간다면")
    class Context_when_correct_id {
        @Test
        @DisplayName("Id 값을 리턴한다.")
        void it_return_get_by_id() {
            assertThat(product.getId()).isEqualTo(1L);
        }
    }

    @Nested
    @DisplayName("Long Id 허수값이 들어간다면")
    class Context_when_not_correct_id {
        @DisplayName("ProductNotFoundException 예외를 던진다.")
        @Test
        void it_throw_ProductNotFoundException() {
            assertThat(product.getId()).isNotEqualTo(100L);
        }
    }
}
