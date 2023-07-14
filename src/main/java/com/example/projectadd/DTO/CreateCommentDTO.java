package com.example.projectadd.DTO;

import lombok.Data;

@Data
public class CreateCommentDTO {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

