package com.example.projectadd.repository;

import com.example.projectadd.model.Ads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsRepository extends JpaRepository<Ads, Integer> {
}

