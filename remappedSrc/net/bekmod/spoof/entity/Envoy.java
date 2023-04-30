package net.bekmod.spoof.entity;

import java.util.Date;

public class Envoy {
    private int coordX;
    private int coordY;
    private int coordZ;
    private int proximity;
    private int index;
    private Long createdAt;
    private boolean flag;

    public Envoy(int coordX, int coordY, int coordZ, int index, Long createdAt, boolean flag) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordZ = coordZ;
        this.index = index;
        this.createdAt = createdAt;
        this.flag = flag;
    }

    public void setProximity(int proximity) {
        this.proximity = proximity;
    }

    public int getProximity() {
        return proximity;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public int getCoordZ() {
        return coordZ;
    }

    public int getIndex() {
        return index;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "distance =" + proximity + "; coordinates: X =" + coordX + " Z =" + coordZ;
    }
}
