package mdeis.module1.blog.data;

import mdeis.module1.blog.domain.Post;
import mdeis.module1.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p " +
            "WHERE (:categories IS NULL OR p.category.id IN :categories) " +
            "AND (:tags IS NULL OR EXISTS (" +
            "   SELECT t FROM p.tags t WHERE t.id IN :tags))" +
            "AND (p.deletedAt IS NULL)"+
            "AND (p.status = 'CREATED')")
    List<Post> findByCategoryAndList(
            @Param("categories") List<Integer> categories,
            @Param("tags") List<Integer> tags
    );
}
