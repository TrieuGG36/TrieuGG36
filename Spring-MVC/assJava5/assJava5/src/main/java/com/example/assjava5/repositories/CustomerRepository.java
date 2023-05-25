package com.example.assjava5.repositories;


import com.example.assjava5.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select o from Customer o where o.email = :email")
    public Customer findByEmail(@Param("email") String email);



}
