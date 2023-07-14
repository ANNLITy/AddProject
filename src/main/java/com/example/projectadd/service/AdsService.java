package com.example.projectadd.service;

import com.example.projectadd.DTO.AdsDTO;
import com.example.projectadd.DTO.CreateAdsDTO;
import com.example.projectadd.DTO.FullAdsDTO;
import com.example.projectadd.DTO.ResponseWrapperAds;
import com.example.projectadd.model.Ads;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface AdsService {
    void save(Ads ads);
    boolean deleteById(int id, Authentication authentication);
    Ads getById(int id);
    AdsDTO addAd(CreateAdsDTO createAds, MultipartFile file, Authentication authentication);
    ResponseWrapperAds<AdsDTO> getAllAds();
    FullAdsDTO getAdInfo(int id);
    AdsDTO update(int id, CreateAdsDTO createAds, Authentication authentication);
    ResponseWrapperAds<AdsDTO> getAllUserAds(String userName);
    AdsDTO updateImage(int id, MultipartFile file, Authentication authentication);
}
