package com.example.assjava5.repositories;

import com.example.assjava5.entity.OrderDetail;
import com.example.assjava5.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetilsRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("select o.product from OrderDetail o join Order d on o.order.id = d.id join Customer c on c.id = d.customer.id where c.id =:id ")
    public List<Product> getProductByCustomers(@Param("id") Integer id);
}
