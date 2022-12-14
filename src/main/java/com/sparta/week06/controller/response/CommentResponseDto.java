package com.sparta.week06.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;

    private String content;

}
