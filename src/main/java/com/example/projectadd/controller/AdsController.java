package com.example.projectadd.controller;

import DTO.*;
import com.example.projectadd.service.AdsService;
import com.example.projectadd.service.CommentService;
import com.example.projectadd.service.ImageService;
import com.example.projectadd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    private AdsService adsService;
    private ImageService imageService;
    private CommentService CommentService;
    private UserService userService;


    @GetMapping("/")
    @Operation(summary ="Получить все объявления" )
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok(new ResponseWrapperAds());
    }



    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary ="Добавить объявление" )
    public ResponseEntity<AdsDTO> addAds(@RequestParam("properties") CreateAdsDTO createAds, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(new AdsDTO());
    }



    @GetMapping("/{id}")
    @Operation(summary =" Получить информацию об объявлении" )
    public ResponseEntity<FullAdsDTO> getAds(@PathVariable int id) {
        return ResponseEntity.ok(new FullAdsDTO());
    }



    @DeleteMapping("/{id}")
    @Operation(summary ="Удалить объявление" )
    public ResponseEntity<AdsDTO> removeAd(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}")
    @Operation(summary ="Обновить информацию" )
    public ResponseEntity<AdsDTO> updateAds(@PathVariable int id, @RequestBody CreateAdsDTO createAds) {
        return ResponseEntity.ok(new AdsDTO());
    }


    @GetMapping("/me")
    @Operation(summary ="Получить объявление по пользователю" )
    public ResponseEntity<ResponseWrapperAds> getAdsMe() {
        return ResponseEntity.ok(new ResponseWrapperAds());
    }



    @PatchMapping("/{id}/image")
    @Operation(summary ="Обновить изображение" )
    public ResponseEntity<AdsDTO> updateImage(@PathVariable int id, @RequestParam MultipartFile image) {
        return ResponseEntity.ok(new AdsDTO());
    }



    @GetMapping("{id}/comments")
    @Operation(summary ="Получить комментарии" )
    public ResponseEntity<ResponseWrapperCommentDTO> getComments(@PathVariable int id) {
        return ResponseEntity.ok(new ResponseWrapperCommentDTO());
    }



    @PostMapping("{id}/comments")
    @Operation(summary ="Добавить комментарий" )
    public ResponseEntity<CreateCommentDTO> addComment(@PathVariable int id) {
        return ResponseEntity.ok(new CreateCommentDTO());
    }


    @DeleteMapping("{adId}/comments/{commentId}")
    @Operation(summary ="Удалить комментарий" )
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        return ResponseEntity.ok().build();
    }



    @PatchMapping("{adId}/comments/{commentId}")
    @Operation(summary ="Обновить комментарий" )
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int adId, @PathVariable int commentId,
                                                    @RequestBody CommentDTO comment) {
        return ResponseEntity.ok(comment);
    }

}
