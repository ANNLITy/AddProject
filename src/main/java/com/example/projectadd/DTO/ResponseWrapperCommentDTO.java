package com.example.projectadd.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapperCommentDTO<T> {
    private int count;
    private Collection<T> results;

    public static <T> ResponseWrapperCommentDTO<T> of(Collection<T> results) {
        ResponseWrapperCommentDTO<T> responseWrapperCommentDTO = new ResponseWrapperCommentDTO<>();
        if (results == null) {
            return responseWrapperCommentDTO;
        }
        responseWrapperCommentDTO.results = results;
        responseWrapperCommentDTO.count = results.size();
        return responseWrapperCommentDTO;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Collection<T> getResults() {
        return results;
    }

    public void setResults(Collection<T> results) {
        this.results = results;
    }
}