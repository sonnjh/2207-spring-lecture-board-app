package app.board.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt")})
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(value = AuditingEntityListener.class)
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id; // 댓글 (ID)

    @Setter
    @ManyToOne(fetch = LAZY, optional = false)
    private Article article; // 게시글 (ID)

    @Setter
    @Column(nullable = false, length = 100)
    private String content; // 본문

    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100)
    private String createdBy; // 생성자

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String updatedBy; // 수정자

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성 날짜

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt; // 수정 날짜

    @Builder
    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {
        return ArticleComment.builder()
                .article(article)
                .content(content)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment entity)) return false;
        return id != null && id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}