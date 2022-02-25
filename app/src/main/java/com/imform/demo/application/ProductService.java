//TODO
//1. 전체조회 -> getProducts
//2. 조회 -> getProduct
//3. 생성 -> createProduct
//4. 수정 -> updateProduct
//5. 삭제 -> deleteProduct
package com.imform.demo.application;

import com.imform.demo.domain.Product;
import com.imform.demo.domain.ProductRepository;
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
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product source) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
        product.setContent(source.getContent());

        return product;
    }

    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);

        return product;
    }
}
