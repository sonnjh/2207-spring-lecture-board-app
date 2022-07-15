package app.board.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@ToString
@Table(indexes = {
    @Index(columnList = "title"),
    @Index(columnList = "hashtag"),
    @Index(columnList = "createdBy"),
    @Index(columnList = "createdAt")})
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(value = AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id; // 게시글 (ID)

    @Setter
    @Column(nullable = false, length = 100)
    private String title; // 제목

    @Setter
    @Column(nullable = false, length = 1000)
    private String content; // 본문

    @Setter
    @Column(length = 100)
    private String hashtag; // 해시태그

    @ToString.Exclude
    @OrderBy(value = "id")
    @OneToMany(mappedBy = "article")
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

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
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return Article.builder()
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article entity)) return false;
        // 영속화되지 않은 엔티티(new 상태)는 id 값이 없으므로 null 체크한다.
        // id 값이 없을 경우 두 엔티티를 서로 다른 객체로 취급한다.
        return id != null && id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}