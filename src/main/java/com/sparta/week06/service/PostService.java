package com.sparta.week06.service;

import com.sparta.week06.domain.Post;
import com.sparta.week06.domain.PostHeart;
import com.sparta.week06.repository.PostHeartRepository;
import com.sparta.week06.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostHeartRepository postHeartRepository;
    private final PostRepository postRepository;

    public Page<Post> findAllOfHeartList(String authorization, Pageable pageable) {
        String userId =
                authorization != null ? loginAuthenticationUtil.getMemberIdFromAuth(authorization)
                        : null;
        List<PostHeart> heartList = postHeartRepository.findPostHeartsByUserId(memberId).get();
        List<String> postIdListByHeart = heartList.stream().map(PostHeart::getPostId).collect(
                Collectors.toList());

        return heartFactory.getpostSearchList(
                heartFactory.createQuery(postIdListByPostHeart, pageable));
    }
}
