//package com.sparta.week06.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.week06.domain.Post;
//import com.sparta.week06.repository.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.MediaType;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//@Import(JpaConfig.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class PostHeartControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private PostRepository postRepository;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    Map<String, String> input = new HashMap<>();
//
//    @BeforeEach
//    void setBody() {
//        Optional<Post> post = postRepository.findDistinctBySiteType("happybean");
//        if (post.isEmpty()) {
//            throw new ResourceNotFoundException("캠페인을 찾을 수 없음");
//        }
//
//        input.put("postId", post.get().getId());
//        input.put("userId", "550e8400-e29b-41d4-a716-446655440000"); // testUser
//    }
//
//    @Test
//    @Order(100)
//    @DisplayName("좋아요 테스트 - 성공")
//    public void doHeart() throws Exception {
//
//        mockMvc
//                .perform(post("/api/heart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(input)))
//
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @Order(101)
//    @DisplayName("좋아요 테스트 - 실패 :: 이미 좋아요 된 캠페인")
//    public void doHeartFailDuplicate() throws Exception {
//
//        mockMvc
//                .perform(post("/api/heart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(input)))
//
//                .andExpect(status().isConflict())
//                .andExpect(jsonPath("$.code").value("ALREADY_HEARTED"))
//                .andExpect(jsonPath("$.message").value("이미 좋아요 된 캠페인 입니다."));
//    }
//
//    @Test
//    @Order(200)
//    @DisplayName("좋아요 취소 테스트 - 성공")
//    public void unHeart() throws Exception {
//
//        mockMvc
//                .perform(delete("/api/heart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(input)))
//
//                .andExpect(status().isOk());
//
//    }
//
//    @Test
//    @Order(201)
//    @DisplayName("좋아요 취소 테스트 - 실패 :: 없는 좋아요 취소 시도")
//    public void unHeartFailNotFound() throws Exception {
//
//        mockMvc
//                .perform(delete("/api/heart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(input)))
//
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.code").value("HEART_NOT_FOUND"))
//                .andExpect(jsonPath("$.message").value("해당 좋아요 정보를 찾을 수 없습니다."));
//
//
//    }
//}
