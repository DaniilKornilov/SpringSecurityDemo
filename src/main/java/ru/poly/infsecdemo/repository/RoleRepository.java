package ru.poly.infsecdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poly.infsecdemo.entity.Role;
import ru.poly.infsecdemo.entity.enumeration.RoleEnum;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}
