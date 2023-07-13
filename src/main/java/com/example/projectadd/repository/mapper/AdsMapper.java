package com.example.projectadd.repository.mapper;

import com.example.projectadd.DTO.AdsDTO;
import com.example.projectadd.DTO.CreateAdsDTO;
import com.example.projectadd.DTO.FullAdsDTO;
import com.example.projectadd.model.Ads;
import com.example.projectadd.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AdsMapper {
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "image", target = "image", qualifiedByName = "imageMapper")
    @Mapping(source = "id", target = "pk")
    FullAdsDTO toFullAdsDto(Ads ads);

    @Mapping(source = "user.id", target = "authorId")
    @Mapping(source = "image", target = "image", qualifiedByName = "imageMapper")
    @Mapping(source = "id", target = "pk")
    AdsDTO toAdsDto(Ads ads);

    @Named("imageMapper")
    default String imageMapper(Image image) {
        if (image == null) {
            return null;
        }
        return "/ads/image/"+image.getId();
    }
}
