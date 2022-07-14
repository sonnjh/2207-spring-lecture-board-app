package app.board.repository;

import app.board.config.JpaConfig;
import app.board.domain.Article;
import app.board.domain.ArticleComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@Import(value = JpaConfig.class)
@ActiveProfiles(value = "testdb")
@DisplayName(value = "JPA 연결 테스트")
@AutoConfigureTestDatabase(replace = NONE)
class JpaRepositoryTests {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTests(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    @DisplayName(value = "INSERT 테스트")
    protected void insert() {
        // Given
        long initCount = articleRepository.count();

        // When
        articleRepository.save(createArticle());

        // Then
        assertThat(articleRepository.count())
                .isEqualTo(initCount + 1);
    }

    @Test
    @DisplayName(value = "SELECT 테스트")
    protected void select() {
        // Given (mock 데이터 추천 -> https://www.mockaroo.com)
        long initCount = articleRepository.count();
        articleRepository.save(createArticle());

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize((int) (initCount + 1));
    }

    @Test
    @DisplayName(value = "UPDATE 테스트")
    protected void update() {
        // Given
        Article article = articleRepository.save(createArticle());
        article.setTitle("제목 수정");

        // When (UPDATE Query)
        Article updated = articleRepository.saveAndFlush(article);

        // Then
        assertThat(updated)
                .hasFieldOrPropertyWithValue("title", "제목 수정");
    }

    @Test
    @DisplayName(value = "DELETE 테스트")
    protected void delete() {
        // Given
        long initArticleCount = articleRepository.count();
        long initCommentCount = articleCommentRepository.count();
        Article article = articleRepository.save(createArticle());
        List<ArticleComment> comments = articleCommentRepository.saveAll(createArticleComments(article));

        // When
        articleCommentRepository.deleteAll(comments);
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(initArticleCount);
        assertThat(articleCommentRepository.count()).isEqualTo(initCommentCount);
    }

    private Article createArticle() {
        return Article.of("제목", "본문", "spring");
    }

    private Set<ArticleComment> createArticleComments(Article article) {
        return new LinkedHashSet<>(){{
            add(ArticleComment.of(article, "댓글 1"));
            add(ArticleComment.of(article, "댓글 2"));
            add(ArticleComment.of(article, "댓글 3"));
        }};
    }

    private Set<ArticleComment> createArticleComments() {
        Article article = createArticle();

        return new LinkedHashSet<>(){{
            add(ArticleComment.of(article, "댓글 1"));
            add(ArticleComment.of(article, "댓글 2"));
            add(ArticleComment.of(article, "댓글 3"));
        }};
    }
}