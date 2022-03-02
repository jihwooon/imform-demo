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

  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  public Product getProduct(Long id) {
    return findProduct(id);
  }

  public ProductData.ProductUserId createProduct(ProductData.ProductCreate productCreate) {
    Product product = Product.builder()
        .userId(productCreate.getUserId())
        .content(productCreate.getContent())
        .description(productCreate.getDescription())
        .build();

    Product test = productRepository.save(product);

    return ProductData.ProductUserId.returnUserIdDto(test);
  }

  public Product updateProduct(Long id, ProductData.ProductUpdate productUpdate) {
    Product product = findProduct(id);
    product.change(Product.builder()
        .userId(productUpdate.getUserId())
        .content(productUpdate.getContent())
        .build());
    return productRepository.save(product);
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
