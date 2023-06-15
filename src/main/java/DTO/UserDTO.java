package DTO;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}