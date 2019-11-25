package com.os.device;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-23 20:53
 */
public class PhysicalAddr {
    private int surfaceId;
    private int trackId;
    private int physicalShanQuId;

    public int getSurfaceId() {
        return surfaceId;
    }

    public void setSurfaceId(int surfaceId) {
        this.surfaceId = surfaceId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getPhysicalShanQuId() {
        return physicalShanQuId;
    }

    public void setPhysicalShanQuId(int physicalShanQuId) {
        this.physicalShanQuId = physicalShanQuId;
    }
}
