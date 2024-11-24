package mdeis.module1.blog.data;

import mdeis.module1.blog.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

}
