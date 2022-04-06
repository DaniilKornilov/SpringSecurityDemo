package ru.poly.infsecdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poly.infsecdemo.entity.Role;
import ru.poly.infsecdemo.entity.enumeration.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleEnum(RoleEnum roleEnum);

    Boolean existsByRoleEnum(RoleEnum roleEnum);
}
