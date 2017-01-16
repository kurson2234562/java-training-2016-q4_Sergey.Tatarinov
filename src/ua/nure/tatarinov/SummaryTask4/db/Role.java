package ua.nure.tatarinov.SummaryTask4.db;

import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

/**
 * Role dto
 * @author Sergey Tatarinov
 */
public enum Role {
    ADMIN, STUDENT, LECTURER;

    public static Role getRole(UserDTO user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
