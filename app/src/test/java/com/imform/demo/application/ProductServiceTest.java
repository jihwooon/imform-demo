//1. 전체조회 -> getProducts
//2. 조회 -> getProduct
//3. 생성 -> createProduct
//4. 수정 -> updateProduct
//5. 삭제 -> deleteProduct

// Mock -> 가짜 객체 만들기
package com.imform.demo.application;

import com.imform.demo.domain.Product;
import com.imform.demo.domain.ProductRepository;
import com.imform.demo.dto.ProductData;
import com.imform.demo.error.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("ProductService 클래스")
@SpringBootTest
class ProductServiceTest {

  private ProductService productService;
  private ProductRepository productRepository;
  private final String CONTENT = "HelloWorld";

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    productService = new ProductService(productRepository);

    Product product = Product.builder()
        .id(1L)
        .userId("안지환")
        .content("내용")
        .description("설명")
        .build();

    given(productRepository.findAll()).willReturn(List.of(product));
    given(productRepository.findById(1L)).willReturn(Optional.of(product));
    given(productRepository.findById(100L)).willReturn(Optional.empty());

    given(productRepository.save(any(Product.class))).will(invocation -> {
      Product source = invocation.getArgument(0);
      return Product.builder()
          .id(2L)
          .userId("안지환")
          .content("내용")
          .description("설명")
          .build();
    });

  }

  @Test
  @DisplayName("ProductService 전체 조회 성공()")
  void getTasks() {
    List<Product> products = productService.getProducts();
    assertThat(products).hasSize(1);

    verify(productRepository).findAll();

  }

  @Nested
  @DisplayName("getTask 메서드")
  class Decribe_getTask {
    @Nested
    @DisplayName("실수값이 주어지면")
    class Context_with_real {
      @Test
      @DisplayName("조회된 실수값을 리턴한다.")
      void it_returns_a_valid_getTask() {
        Product product = productService.getProduct(1L);

        verify(productRepository).findById(1L);
      }
    }

    @Nested
    @DisplayName("허수값이 주어지면")
    class Context_with_un_real {
      @Test
      @DisplayName("ProductNotFoundException 예외를 던진다.")
      void it_return_a_Invalid_getTask() {
        assertThatThrownBy(() -> productService.getProduct(100L))
            .isInstanceOf(ProductNotFoundException.class);

        verify(productRepository).findById(100L);
      }
    }
  }

  @Test
  @DisplayName("ProductService 등록 성공()")
  void save() {
    ProductData productData = ProductData.builder()
        .content("설명")
        .build();

    Product product = productService.createProduct(productData);

    verify(productRepository, atLeastOnce()).save(any(Product.class));

  }

  @Test
  @DisplayName("ProductService 변경 성공()")
  void upadte() {
    ProductData source = ProductData.builder()
        .userId("안중환")
        .content("Hello World")
        .build();

    Product product = productService.updateProduct(1L, source);
    assertThat(product.getUserId()).isEqualTo("안지환");
    assertThat(product.getContent()).isEqualTo("내용");

    verify(productRepository).findById(1L);

  }

  @Test
  @DisplayName("ProductService 예외 처리()")
  void upadteWithNotFoundException() {
    ProductData source = ProductData.builder()
        .userId("안중환")
        .content("Hello World")
        .build();

    assertThatThrownBy(() -> productService.updateProduct(1000L, source))
        .isInstanceOf(ProductNotFoundException.class);

  }

  @Test
  @DisplayName("ProductService 삭제 성공()")
  void delete() {
    productService.deleteProduct(1L);

    verify(productRepository).delete(any(Product.class));
  }

  @Test
  @DisplayName("ProductService 삭제 실패()")
  void deleteNotExisted() {
    assertThatThrownBy(() -> productService.deleteProduct(1000L))
        .isInstanceOf(ProductNotFoundException.class);

    verify(productRepository).findById(1000L);
  }
}
