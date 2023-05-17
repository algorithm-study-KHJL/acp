package com.khjl.acp.entity.performance;

public enum PerformanceType {
    RENTAL("대관공연"),
    PLAN("기획공연"),
    CITY("시립공연");

    private final java.lang.String displayName;

    PerformanceType(java.lang.String displayName) {
        this.displayName = displayName;
    }

    @Override
    public java.lang.String toString() {
        return displayName;
    }
}
