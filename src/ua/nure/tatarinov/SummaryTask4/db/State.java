package ua.nure.tatarinov.SummaryTask4.db;

import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

/**
 * State dto
 * @author Sergey Tatarinov
 */
public enum State {
    UNLOCKED, LOCKED;

    /**
     * @param user user
     * @return state
     */
    public static Role getState(UserDTO user) {
        int stateId = user.getStateId();
        return Role.values()[stateId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
