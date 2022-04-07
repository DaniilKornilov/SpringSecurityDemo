package ru.poly.infsecdemo.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
    ROLE_USER("USER"), ROLE_ADMIN("ADMIN");
    public final String roleName;

    private static final Map<String, RoleEnum> lookup = new HashMap<>();

    static {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            lookup.put(roleEnum.getRoleName(), roleEnum);
        }
    }

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RoleEnum get(String roleName) {
        return lookup.get(roleName);
    }
}
