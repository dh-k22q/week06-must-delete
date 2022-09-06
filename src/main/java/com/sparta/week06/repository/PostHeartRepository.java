package com.sparta.week06.repository;

import com.sparta.week06.domain.Member;
import com.sparta.week06.domain.PostHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostHeartRepository extends JpaRepository<PostHeart, Long> {

    Optional<PostHeart> findPostHeartByMemberAndPostId(Member member, String postId);
    Optional<List<PostHeart>> findPostHeartsByMemberId(String memberId);
}
