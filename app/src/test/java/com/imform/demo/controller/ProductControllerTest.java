package com.imform.demo.controller;

import com.imform.demo.application.ProductService;
import com.imform.demo.domain.Product;
import com.imform.demo.dto.ProductData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@DisplayName("ProductController 클래스")
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ProductController productController;

  @MockBean
  private ProductService productService;

  @BeforeEach
  void setUp() {
    List<Product> products = new ArrayList<>();
    Product product = Product.builder()
        .content("HelloWorld")
        .build();
    products.add(product);

    given(productService.getProducts()).willReturn(products);
    given(productService.getProduct(1L)).willReturn(product);
    given(productService.updateProduct(eq(1L), any(ProductData.class))).willReturn(product);
    given(productService.deleteProduct(eq(1L))).willReturn(product);

  }

  @Nested
  @DisplayName("listController 객체가 주어지면")
  class describe_list {
    @Test
    @DisplayName("주어진 객체를 전체 조회하고, 전체 조회된 객체를 리턴한다.")
    void listWithExitedProduct() throws Exception {
      mockMvc.perform(get("/products")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());

      verify(productService).getProducts();
    }
  }

  @Nested
  @DisplayName("detailController 객체가 주어지면")
  class describe_validId {
    @Test
    @DisplayName("주어진 객체를 조회하고, 조회된 객체를 리턴한다.")
    void detailWithValidId() throws Exception {
      mockMvc.perform(get("/products/1"))
          .andExpect(status().isOk());

      verify(productService).getProduct(1L);
    }
  }

  @Nested
  @DisplayName("createController 객체가 주어지면 ")
  class describe_createWithExistedContent {
    @Test
    @DisplayName("주어진 객체를 저장하고. 저장된 객체를 리턴한다.")
    void createWithExitedProduct() throws Exception {
      mockMvc.perform(post("/products")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"content\":\"HelloWorld\"}"))
          .andExpect(status().isCreated());

      verify(productService).createProduct(any(ProductData.class));
    }
  }

  @Nested
  @DisplayName("updateController 객체가 주어지면")
  class describe_update {
    @Test
    @DisplayName("주어진 객체를 변경하고, 변경된 객체를 리턴한다.")
    void updateWithExitedProduct() throws Exception {
      mockMvc.perform(patch("/products/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content("{\"content\":\"HelloJava\"}"))
          .andExpect(status().isOk());

      verify(productService).updateProduct(eq(1L), any(ProductData.class));
    }
  }

  @Nested
  @DisplayName("deleteController 객체가 주어지면")
  class describe_delete {
    @Test
    @DisplayName("주어진 객체를 삭제하고, 삭제된 객체를 리턴한다.")
    void deleteWithExitedProduct() throws Exception {
      mockMvc.perform(delete("/products/1"))
          .andExpect(status().isNoContent());

      verify(productService).deleteProduct(1L);
    }
  }
}
