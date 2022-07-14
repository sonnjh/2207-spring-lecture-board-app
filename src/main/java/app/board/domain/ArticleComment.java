package app.board.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id; // 댓글 (ID)
    private Article article; // 게시글 (ID)
    private String content; // 본문

    private String createdBy; // 생성자
    private String updatedBy; // 수정자
    private LocalDateTime createdAt; // 생성 날짜
    private LocalDateTime updatedAt; // 수정 날짜
}