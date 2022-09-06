package com.sparta.week06.domain;

import com.sparta.week06.controller.request.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Member;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition  = "TEXT", nullable = false)
    private String nickname; //댓글 내용

    @Column(columnDefinition  = "TEXT", nullable = false)
    private String content; //댓글 내용



    @ManyToOne (fetch = FetchType.LAZY)//한 개의 게시글에는 여러 개의 댓글이 있을 수 있다
    @JoinColumn(name = "post_id", nullable = false)//외래키
    private Post post;

    @ManyToOne (fetch = FetchType.LAZY) //한 명의 사용자는 여러 개의 댓글을 작성할 수 있다
    @JoinColumn(name = "member_id", nullable = false)//외래키
    private Member member;


    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent(); }

//    public boolean validateMember(Member member) { return !this.member.equals(member); }
}
