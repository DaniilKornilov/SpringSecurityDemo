package ru.poly.infsecdemo.entity;

import lombok.*;
import ru.poly.infsecdemo.entity.enumeration.RoleEnum;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId() == role.getId() && getRoleEnum() == role.getRoleEnum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleEnum());
    }
}
