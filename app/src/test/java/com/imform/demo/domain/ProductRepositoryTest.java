//TODO
//1.전체 조회 -> ListAll
//2.조회 -> findById
//3.저장 -> save
//4.변경 -> update
//5.삭제 -> delete

package com.imform.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("ProductRepository 클래스")
class ProductRepository_클래스 {

    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
            .id(1L)
            .content("HelloWorld")
            .build();
    }

    @Nested
    @DisplayName("productRepository 메소드")
    class Describe_Repository {

        @DisplayName("productRepository은 NotNull 리턴한다.")
        @Test
        void it_returns_productRepository_not_null() {

            assertThat(productRepository).isNotNull();

        }
    }

    @Nested
    @DisplayName("ListAll 메서드")
    class ListAll_메서드 {

        @BeforeEach
        void prepare() {
            productRepository.deleteAll();
        }

        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class Product_전체_조회_하면 {
            @Test
            void _0값을_리턴한다 () {
                List<Product> result = productRepository.findAll();

                assertThat(result).isEmpty();
            }
        }
    }

    @DisplayName("Product 저장 성공()")
    @Test
    void Product_저장_성공() {
        Product result = productRepository.save(product);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getContent()).isEqualTo("HelloWorld");
    }

    @DisplayName("Product 저장 실패()")
    @Test
    void Product_저장_실패_(){
        Product result = productRepository.save(product);

        assertThat(result.getId()).isNotEqualTo(100L);
        assertThat(result.getContent()).isNotEqualTo("HelloJavaScript");
    }
}
