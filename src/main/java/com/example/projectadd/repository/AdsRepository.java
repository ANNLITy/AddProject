package com.example.projectadd.repository;

import com.example.projectadd.model.Ads;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ads, Integer> {
    List<Ads> findAdsByUser_Email(String email);
}

