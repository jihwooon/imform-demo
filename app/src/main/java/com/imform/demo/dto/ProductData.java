package com.imform.demo.dto;

import com.imform.demo.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class ProductData {

  @NotNull
  private Long id;

  @NotEmpty
  private String userId;

  @NotEmpty
  private String content;

  @NotEmpty
  private String description;


    @Getter
    public static class ProductCreate {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String content;

    @NotEmpty
    private String description;

}

  @Getter
  public static class ProductUpdate {

    @Size(min = 6)
    @NotEmpty
    private String userId;

    @NotEmpty
    private String content;

  }

  @Getter
  public static class ProductUserId {

    @NotEmpty
    private String userId;

    public ProductUserId(final Product product) {
      this.userId = product.getUserId();
    }

    public static ProductUserId returnUserIdDto(final Product product) {
      return new ProductUserId(product);
    }

    public static List<ProductUserId> returnUserIdDto (final List<Product> products) {
      return products.stream().map(ProductUserId::returnUserIdDto).collect(Collectors.toList());
    }
  }
}

