package com.sparta.week06.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 상세보기
    @GetMapping("/api/auth/post/{id}")
    public String 어쩌구 저쩌구

    //댓글관련
    if (comments != null && !comments.isEmpty()){
        model.addAttribute("comments", comments);
    }
}
