package com.erikssonherlo.incident.domain.model.enums;

import java.util.Arrays;

public enum IncidentStatus {
    OPEN,
    CLOSED;

    public static String[] names() {
        return Arrays.stream(IncidentStatus.values()).map(Enum::name).toArray(String[]::new);
    }
}
