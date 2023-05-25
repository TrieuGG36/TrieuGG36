package com.example.assjava5.repositories;


import com.example.assjava5.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Cart, Integer> {

    @Query("select p.id, p.photo, o.quantity, p.nameProduct, o.price , o.id, p.price, p.quantity from Cart  o join Product p on o.product.id = p.id where o.customer.id =:id")
     List<Object[]> getCartAndProduct(@Param("id") Integer id);

    @Query("select count (o) from Cart  o  where o.customer.id =:id")
     int getCountCard(@Param("id") Integer id);

    @Query("select o from Cart  o  where o.customer.id =:id")
     List<Cart> getCard(@Param("id") Integer id);

    int countByCustomer_Id(Integer id);


    Optional<Cart> findById(Integer id);
}
