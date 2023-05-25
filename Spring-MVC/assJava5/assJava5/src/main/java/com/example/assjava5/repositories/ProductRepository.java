package com.example.assjava5.repositories;


import com.example.assjava5.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select  o from Product o join Category c on c.id = o.category.id where c.id =:id ")
    public List<Product> getProductByCapacity(@Param("id") Integer id, Pageable pageable);

    @Query("select o from  Product o where concat(o.nameProduct, o.price, o.quantity, o.category) LIKE %?1%")
    public List<Product> getProductLikeName(String find);


    @Query("select  o from Product o where o.category.id =:id")
    public List<Product> getProductByCategory(@Param("id") Integer id , Pageable pageable);

    @Query("select  o from Product o where o.active = 1")
    public List<Product> getProductByActive(Pageable pageable) ;

    @Query("select  o from Product o where o.price between ?1 and ?2")
    public List<Product> findProductsByPrice(int min, int max, Pageable pageable);


    @Query("select  o from Product o join Category  c on c.id = o.category.id where c.id= ?1 and  o.price between ?2 and ?3")
    public List<Product> findProductsByPriceaAndCategory(Integer id, int min, int max, Pageable pageable);
}
