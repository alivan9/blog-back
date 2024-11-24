package mdeis.module1.blog.data;

import mdeis.module1.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
