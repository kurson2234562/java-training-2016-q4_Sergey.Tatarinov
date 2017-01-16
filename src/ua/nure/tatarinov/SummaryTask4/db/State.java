package ua.nure.tatarinov.SummaryTask4.db;

import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

public enum State {
    UNLOCKED, LOCKED;
    public static Role getState(UserDTO user) {
        int stateId = user.getStateId();
        return Role.values()[stateId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
