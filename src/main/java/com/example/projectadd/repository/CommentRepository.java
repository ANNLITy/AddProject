package com.example.projectadd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "select * from comments where ads_id = :ads_id", nativeQuery = true)
    List<Comment> findByAdsId(@Param("ads_id") int adsId);
}

