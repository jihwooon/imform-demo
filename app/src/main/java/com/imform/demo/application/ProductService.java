//TODO
//1. 전체조회 -> getProducts
//2. 조회 -> getProduct
//3. 생성 -> createProduct
//4. 수정 -> updateProduct
//5. 삭제 -> deleteProduct
package com.imform.demo.application;

import com.imform.demo.domain.Product;
import com.imform.demo.domain.ProductRepository;
import com.imform.demo.dto.ProductData;
import com.imform.demo.error.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<ProductData.ProductUserId> getProducts() {
    return ProductData.ProductUserId.returnUserIdDto(productRepository.findAll());
  }

  public ProductData.ProductUserId getProduct(Long id) {
    Product detail = findProduct(id);
    return ProductData.ProductUserId.returnUserIdDto(detail);
  }

  public ProductData.ProductUserId createProduct(ProductData.ProductCreate productCreate) {
    Product product = Product.builder()
        .userId(productCreate.getUserId())
        .content(productCreate.getContent())
        .description(productCreate.getDescription())
        .build();

    Product create = productRepository.save(product);

    return ProductData.ProductUserId.returnUserIdDto(create);
  }

  public ProductData.ProductUserId updateProduct(Long id, ProductData.ProductUpdate productUpdate) {
    Product product = findProduct(id);
    product.change(Product.builder()
        .userId(productUpdate.getUserId())
        .content(productUpdate.getContent())
        .build());
    Product update = productRepository.save(product);

    return ProductData.ProductUserId.returnUserIdDto(update);
  }

  public Product deleteProduct(Long id) {
    Product product = findProduct(id);
    productRepository.delete(product);

    return product;
  }

  private Product findProduct(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));
  }
}
