package com.cafe24.websample.domain.role;

import com.cafe24.websample.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

}