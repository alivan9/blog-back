package mdeis.module1.blog.usecase.user;

import mdeis.module1.blog.domain.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetListRole {

    private final GetRoleById getRoleById;

    public GetListRole(GetRoleById getRoleById) {
        this.getRoleById = getRoleById;
    }

    public List<Role> invoke(List<Integer> roleIdList) {
        ArrayList<Role> rolesList = new ArrayList<>();
        roleIdList.forEach(it -> {
            Role role = getRoleById.invoke(it);
            rolesList.add(role);
        });
        return rolesList;
    }

}
