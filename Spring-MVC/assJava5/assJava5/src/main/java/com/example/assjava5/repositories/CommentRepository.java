package com.example.assjava5.repositories;

import com.example.assjava5.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select  o from Comment o join Product p on p.id = o.product.id where p.id = :id")
    List<Comment> findByProduct(@Param("id") Integer id);

    @Query("select  count (o) from Comment o join Product p on p.id = o.product.id where p.id = :id")
    int countCommentByProduct(@Param("id") Integer id);

    int countByCustomer_IdAndProduct_Id(Integer customerID, Integer productID);
}
