package mdeis.module1.blog.data;

import mdeis.module1.blog.domain.Follows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowsRepository extends JpaRepository<Follows, Integer> {

}
