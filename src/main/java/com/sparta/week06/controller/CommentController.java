package com.sparta.week06.controller;

import com.sparta.week06.controller.request.CommentRequestDto;
import com.sparta.week06.controller.response.ResponseDto;
import com.sparta.week06.domain.Post;
import com.sparta.week06.repository.CommentRepository;
import com.sparta.week06.repository.PostRepository;
import com.sparta.week06.service.CommentService;
import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    //댓글 목록조회
    @GetMapping("/replies/{postId}")
    public List<Comment> showReply(@PathVariable Long postId) {
        return commentRepository.findAllByPostIdOrderByModifiedAtDesc(postId);
    }

    //댓글 작성
    @PostMapping("/replies/{postId}")
    public Reply writeReply(@PathVariable Long postId, @RequestBody ReplyRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("아이디가 없습니다.")
        );
        //post자체를 넣어야함
        Reply reply = new Reply(post, requestDto);
        return replyRepository.save(reply);
    }

    //댓글 수정
    @PutMapping("/replies/{replyId}")
    public Long editReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto requestDto) {
        return replyService.editReply(replyId, requestDto);
    }

    //댓글 삭제
    @DeleteMapping("/replies/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
