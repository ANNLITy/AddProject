package DTO;

import lombok.Data;

@Data
public class CommentDTO {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private long pk;
    private String text;
}
