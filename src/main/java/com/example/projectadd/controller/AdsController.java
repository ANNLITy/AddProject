package com.example.projectadd.controller;

import com.example.projectadd.DTO.*;
import com.example.projectadd.service.AdsService;
import com.example.projectadd.service.CommentService;
import com.example.projectadd.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления", description = "AdsController")
public class AdsController {
    private final AdsService adsService;
    private final CommentService commentService;
    private final ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(AdsController.class);

    public AdsController(AdsService adsService, CommentService commentService, ImageService imageService) {
        this.adsService = adsService;
        this.commentService = commentService;
        this.imageService = imageService;
    }


    @Operation(
            summary = "Получить все объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseWrapperAds.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping()
    public ResponseEntity<ResponseWrapperAds<AdsDTO>> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @Operation(
            summary = "Добавить объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201", description = "Created",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = AdsDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDTO> addAds(@RequestPart("image") MultipartFile image,
                                         @RequestPart("properties") CreateAdsDTO properties,
                                         Authentication authentication) {
        printLogInfo("/ads/", "post", "/ads/");
        AdsDTO adsDTO = adsService.addAd(properties, image, authentication);
        return ResponseEntity.ok(adsDTO);
    }


    @Operation(
            summary = "Получить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = FullAdsDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDTO> getAds(@PathVariable("id") int id) {
        FullAdsDTO fullAdsDTO = adsService.getAdInfo(id);
        return ResponseEntity.ok(fullAdsDTO);
    }


    @Operation(
            summary = "Удалить объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id, Authentication authentication) {
        if (adsService.deleteById(id, authentication)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @Operation(
            summary = "Обновить информацию об объявлении",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CreateAdsDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDTO> updateAds(@PathVariable int id,
                                            @RequestBody CreateAdsDTO createAds,
                                            Authentication authentication) {
        return ResponseEntity.ok(adsService.update(id, createAds, authentication));
    }

    @Operation(
            summary = "Получить объявления авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseWrapperAds.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds<AdsDTO>> getAdsMe(Authentication authentication) {
        ResponseWrapperAds<AdsDTO> response = adsService.getAllUserAds(authentication.getName());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Обновить картинку объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/octet-stream"
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @PatchMapping("/{id}/image")
    public ResponseEntity<AdsDTO> updateImage(@PathVariable int id, @RequestParam MultipartFile image, Authentication authentication) {
        return ResponseEntity.ok(adsService.updateImage(id, image, authentication));
    }

    @Operation(
            summary = "Получить комментарии объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseWrapperCommentDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping("{id}/comments")
    public ResponseEntity<ResponseWrapperCommentDTO> getComments(@PathVariable("id") int id) {
        ResponseWrapperCommentDTO responseWrapperCommentDTO = commentService.getComments(id);
        return ResponseEntity.ok(responseWrapperCommentDTO);
    }

    @Operation(
            summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CommentDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping("{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable("id") int id, @RequestBody CreateCommentDTO createComment, Authentication authentication) {
        return ResponseEntity.ok(commentService.addComment(id, createComment, authentication));
    }

    @Operation(
            summary = "Удалить комментарий",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable int adId, @PathVariable int commentId, Authentication authentication) {
        if (commentService.deleteComment(adId, commentId, authentication)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Operation(
            summary = "Обновить комментарий",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CommentDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("adId") int adId,
                                                    @PathVariable("commentId") int commentId,
                                                    @RequestBody CommentDTO comment,
                                                    Authentication authentication) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, comment, authentication));
    }

    @Operation(summary = "Получить картинку объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })
    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        return ResponseEntity.ok(imageService.loadImage(id));
    }

    private void printLogInfo(String name, String requestMethod, String path) {
        logger.info("Вызван метод " + name + ", адрес "
                + requestMethod.toUpperCase() + " запроса " + path);
    }

}
