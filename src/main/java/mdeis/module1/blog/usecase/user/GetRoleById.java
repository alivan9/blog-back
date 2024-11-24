package mdeis.module1.blog.usecase.user;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.data.RoleRepository;
import mdeis.module1.blog.domain.Role;
import org.springframework.stereotype.Component;

@Component
public class GetRoleById {

    private final RoleRepository roleRepository;

    public GetRoleById(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role invoke(Integer roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new EntityNotFoundException("Role not found with id: " + roleId));
    }

}
