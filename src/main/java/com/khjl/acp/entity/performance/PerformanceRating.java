package com.khjl.acp.entity.performance;

public enum PerformanceRating {

    ALL("전연령"),
    FOUR("4세 이상"),
    FIVE("5세 이상"),
    EIGHT("8세 이상"),
    FIFTEEN("15세 이상");

    private final String displayName;

    PerformanceRating(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
