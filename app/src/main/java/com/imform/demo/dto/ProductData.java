package com.imform.demo.dto;

import com.imform.demo.domain.Product;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class ProductData {

  @Getter
  public class ProductCreate {
    @NotNull
    @Size(min = 6)
    private String userId;

    @NotBlank
    private String content;

    @NotBlank
    private String description;

  }

  @Getter
  public static class ProductUpdate {
    @NotNull
    @Size(min = 6)
    private String userId;

    @NotBlank
    private String content;

  }

  @Getter
  public static class ProductUserId {
    private String userId;

    public ProductUserId(Product product) {
      this.userId = product.getUserId();
    }

    public static ProductUserId returnUserIdDto(Product product) {
      return new ProductUserId(product);

    }
  }
}
