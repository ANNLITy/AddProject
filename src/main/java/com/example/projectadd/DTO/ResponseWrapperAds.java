package com.example.projectadd.DTO;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperAds<T> {
    private int count;
    private Collection<T> results;
    public static <T> ResponseWrapperAds<T> of(Collection<T> results) {
        ResponseWrapperAds<T> responseWrapper = new ResponseWrapperAds<>();
        if (results == null) {
            return responseWrapper;
        }
        responseWrapper.results = results;
        responseWrapper.count = results.size();
        return responseWrapper;
    }
}
