package app.board.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id; // 게시글 (ID)
    private String title; // 제목
    private String content; // 본문
    private String hashtag; // 해시태그

    private String createdBy; // 생성자
    private String updatedBy; // 수정자
    private LocalDateTime createdAt; // 생성 날짜
    private LocalDateTime updatedAt; // 수정 날짜
}