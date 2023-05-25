package com.example.assjava5.repositories;

import com.example.assjava5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select o from User o where o.email = ?1 and  o.pass = ?2")
    public User login(String email, String pass);


    @Query("select o from User o where o.email = :email ")
    public User findByEmail(@Param("email") String email);

}
