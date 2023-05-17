package com.khjl.acp.entity.performance;

public enum PerformanceHall {

    ART("아트홀"),
    ENSEMBLE("앙상홀"),
    CIRCLE("원형극장");

    private final String displayName;

    PerformanceHall(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
