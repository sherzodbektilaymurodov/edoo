package auf.group.edu.repository;

import auf.group.edu.entity.Role;
import auf.group.edu.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
