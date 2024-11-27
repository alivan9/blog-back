package mdeis.module1.blog.data;

import mdeis.module1.blog.domain.Post;
import mdeis.module1.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p " +
            "WHERE (:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:categories IS NULL OR p.category.id IN :categories) " +
            "AND (:tags IS NULL OR EXISTS (" +
            "   SELECT t FROM p.tags t WHERE t.id IN :tags))")
    List<Post> findByTitleAndCategoryAndList(
            @Param("title") String title,
            @Param("categories") List<Integer> categories,
            @Param("tags") List<Integer> tags
    );
}
