package com.example.projectadd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
 @Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long size;
    private String mediaType;
    private byte[] image;
    @ManyToOne()
    @JoinColumn(name = "ads_id")
    private Ads ads;
}

