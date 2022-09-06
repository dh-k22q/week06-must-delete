package com.sparta.week06.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    private String id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "member",
            fetch = FetchType.LAZY)
    @OrderBy("id asc")//댓글 오름차순 정렬
    private List<Comment> comments;


    @OneToMany(mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<PostHeart> hearts;


}


