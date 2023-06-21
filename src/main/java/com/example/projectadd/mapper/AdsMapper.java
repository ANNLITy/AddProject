package com.example.projectadd.mapper;

import com.example.projectadd.DTO.AdsDTO;
import com.example.projectadd.DTO.CreateAdsDTO;
import com.example.projectadd.DTO.FullAdsDTO;
import com.example.projectadd.model.Ads;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdsMapper {
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "image.path", target = "image")
    FullAdsDTO toFullAdsDto(Ads ads);

    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "image.path", target = "image")
    AdsDTO toAdsDto(Ads ads);

    @Mapping(source = "authorFirstName", target = "user.firstName")
    @Mapping(source = "authorLastName", target = "user.lastName")
    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "phone", target = "user.phone")
    @Mapping(source = "image", target = "image.path")
    Ads toAds(FullAdsDTO fullAdsDTO);
    Ads toAds(CreateAdsDTO createAdsDTO);
}
