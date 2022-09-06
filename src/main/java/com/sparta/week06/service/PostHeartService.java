package com.sparta.week06.service;

import static com.sparta.week06.controller.exception.ErrorCode.ALREADY_HEARTED;
import static com.sparta.week06.controller.exception.ErrorCode.Post_NOT_FOUND;
import static com.sparta.week06.controller.exception.ErrorCode.HEART_NOT_FOUND;
import static com.sparta.week06.controller.exception.ErrorCode.INVALID_JWT_TOKEN;
import static com.sparta.week06.controller.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.sparta.week06.controller.exception.ErrorCode.MISMATCH_JWT_USER;

import com.sparta.week06.controller.exception.CustomException;
import java.io.IOException;
import java.util.Optional;

import com.sparta.week06.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import com.sparta.week06.controller.request.PostHeartDto;
import com.sparta.week06.domain.Member;
import com.sparta.week06.domain.PostHeart;
import com.sparta.week06.repository.MemberRepository;
import com.sparta.week06.repository.PostHeartRepository;
import com.sparta.week06.repository.PostRepository;


@Slf4j
@RequiredArgsConstructor
@Service
public class PostHeartService {

    private final PostHeartRepository postHeartRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

//    private final JwtTokenProvider jwtTokenProvider;
    private Member member;

    public void plusHeart(PostHeartDto heartDto, String jwtToken) throws IOException {
        validateToken(heartDto, jwtToken);

        // 이미 좋아요 된 게시물일 경우 409 에러
        if (findPostHeartWithMemberAndPostId(heartDto).isPresent()) {
            throw new CustomException(ALREADY_HEARTED);
        }

        PostHeart heart = PostHeart.builder()
                .postId(heartDto.getPostId())
                .member(memberRepository.findMemberById(heartDto.getMemberId()).get())
                .build();
        postHeartRepository.save(heart);

        updateHeartCount(heartDto.getPostId(), 1);

    }

    public void minusHeart(PostHeartDto heartDto, String jwtToken) throws IOException {
        validateToken(heartDto, jwtToken);

        Optional<PostHeart> heartOpt = findPostHeartWithMemberAndPostId(heartDto);

        if (heartOpt.isEmpty()) {
            throw new CustomException(HEART_NOT_FOUND);
        }

        postHeartRepository.delete(heartOpt.get());

        updateHeartCount(heartDto.getPostId(), -1);
    }

    public void validateToken(PostHeartDto heartDto, String jwtToken) {
        // 유효한 토큰인지 검증
        if (!jwtTokenProvider.validateToken(jwtToken)) {
            throw new CustomException(INVALID_JWT_TOKEN);
        }

        // 해당 유저 존재하는지 검증
        Optional<Member> memberOpt = MemberRepository.staticFindMemberById(heartDto.getMemberId());
        if (memberOpt.isEmpty()) {
            throw new CustomException(MEMBER_NOT_FOUND);
        } else {
            member = memberOpt.get();
        }

        // 토큰 정보와 요청 userId 정보가 같은지 검증
        if (!jwtTokenProvider.getMemberPk(jwtToken).equals(memberOpt.get().getUsername())) {
            throw new CustomException(MISMATCH_JWT_USER);
        }
    }

    public Optional<PostHeart> findPostHeartWithMemberAndPostId(PostHeartDto heartDto) {
        return postHeartRepository
                .findPostHeartByMemberAndPostId(member, heartDto.getPostId());
    }



    public void updateHeartCount(Long postId, Integer plusOrMinus) throws IOException {

        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) {
            throw new CustomException(Post_NOT_FOUND);
        }


        if (findLike.isEmpty()){
            MemberEntity memberEntity = mr.findById(memberId).get();
            BoardEntity boardEntity = br.findById(boardId).get();

            LikeEntity likeEntity = LikeEntity.toLikeEntity(memberEntity, boardEntity);
            lr.save(likeEntity);
            br.plusLike(boardId);
            return 1;
        }else {
            lr.deleteByBoardEntity_IdAndMemberEntity_Id(boardId, memberId);
            br.minusLike(boardId);
            return 0;

        }
        UpdateRequest request = new UpdateRequest("posts", postId)
                .doc("heart_count", postOpt.get().getHeartCount() + plusOrMinus);

        UpdateResponse response = elasticsearchClient.update(request, RequestOptions.DEFAULT);
        log.info("ES heartCount update response = {}", response);
    }

}
