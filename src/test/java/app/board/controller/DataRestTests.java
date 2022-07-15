package app.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest // Controller 슬라이스 테스트는 Data REST 컴포넌트를 가져오지 않음.
@Transactional // Rollback 트랜잭션을 적용한다. (테스트는 자동으로 Rollback)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = MOCK)
@DisplayName(value = "Data REST 테스트")
public class DataRestTests {
    private final MockMvc mvc;

    public DataRestTests(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName(value = "[API] 게시글 상세 조회")
    protected void article() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @Test
    @DisplayName(value = "[API] 게시글 목록 조회")
    protected void articleList() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
                // .andDo(print());
    }
}