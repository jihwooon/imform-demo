//TODO
//1.전체 조회 -> getAll
//2.조회 -> findById
//3.저장 -> save
//4.변경 -> update
//5.삭제 -> delete
package com.imform.demo.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  List<Product> findAll();

  Optional<Product> findById(Long id);

  Product save(Product product);

  void delete(Product product);

  void deleteAll();

}
