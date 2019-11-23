package com.os.device;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-23 21:21
 */
public class Swap {
    static final int swapSize = 32; // 单位MB
    static final int swapCount =16384; // 占用的扇区数
    static final int swapStartNum = 53;
    static final int swapStopNum=16437;

    public static int getSwapSize() {
        return swapSize;
    }

    public static int getSwapCount() {
        return swapCount;
    }

    public static int getSwapStartNum() {
        return swapStartNum;
    }

    public static int getSwapStopNum() {
        return swapStopNum;
    }
}
