package mdeis.module1.blog.data;

import mdeis.module1.blog.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
