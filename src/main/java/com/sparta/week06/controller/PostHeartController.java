package com.sparta.week06.controller;

import com.sparta.week06.controller.request.PostHeartDto;
import com.sparta.week06.service.PostHeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/heart")
public class PostHeartController {
    private final PostHeartService heartService;

    @PutMapping //put으로 바꿔야할듯!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public ResponseEntity<PostHeartDto> heart(@RequestBody PostHeartDto heartDto) {
        heartService.plusHeart(heartDto);
        return new ResponseEntity<>(heartDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<PostHeartDto> unHeart(@RequestBody  PostHeartDto heartDto) {
        heartService.minusHeart(heartDto);
        return new ResponseEntity<>(heartDto, HttpStatus.OK);
    }
}
