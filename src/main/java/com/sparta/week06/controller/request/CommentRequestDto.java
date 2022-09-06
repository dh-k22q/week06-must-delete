package com.sparta.week06.controller.request;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private String content;
    private Long userId;
}
