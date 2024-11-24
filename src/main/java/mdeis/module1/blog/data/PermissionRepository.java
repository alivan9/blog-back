package mdeis.module1.blog.data;

import mdeis.module1.blog.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
