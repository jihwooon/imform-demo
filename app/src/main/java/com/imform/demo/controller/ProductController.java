//TODO
//1. 전체 조회 -> list
//2. 조회 -> detail
//3. 생성 -> create
//4. 변경 -> update or patch
//5. 삭제 -> delete
package com.imform.demo.controller;

import com.imform.demo.application.ProductService;
import com.imform.demo.domain.Product;
import com.imform.demo.dto.ProductData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> list() {
    return productService.getProducts();
  }

  @GetMapping("{id}")
  public Product detail(@PathVariable Long id) {
    return productService.getProduct(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductData.ProductUserId create(@RequestBody ProductData.ProductCreate productCreate) {
    return productService.createProduct(productCreate);
  }

  @PatchMapping("{id}")
  public Product update(@PathVariable Long id, @RequestBody ProductData.ProductUpdate productUpdate) {
    return productService.updateProduct(id, productUpdate);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
