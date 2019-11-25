package com.os.device;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-23 19:47
 */
public class SuperBlock {
    private String dev;
    private final int BLOCK_SIZE = 2048; // 单位字节

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public int getBLOCK_SIZE() {
        return BLOCK_SIZE;
    }
}
