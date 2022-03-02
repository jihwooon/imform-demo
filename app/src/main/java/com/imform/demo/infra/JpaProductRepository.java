package com.imform.demo.infra;

    import com.imform.demo.domain.Product;
    import com.imform.demo.domain.ProductRepository;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends ProductRepository, CrudRepository<Product, Long> {

}
