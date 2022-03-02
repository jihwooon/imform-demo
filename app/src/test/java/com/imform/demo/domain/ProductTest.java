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
        product = Product.builder()
            .id(1L)
            .content("내용")
            .description("설명")
            .build();
    }

    @DisplayName("Content 실수값")
    @Test
    void getWithCorrectTitle() {
        assertThat(product.getContent()).isEqualTo("내용");
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

    @Nested
    @DisplayName("String Description 값이 들어간다면")
    class Context_when_Existent_Content {
        @DisplayName("Description 값을 리턴한다.")
        @Test
        void it_return_get_Descirbe() {
            assertThat(product.getDescription()).isEqualTo("설명");
        }
    }
}
