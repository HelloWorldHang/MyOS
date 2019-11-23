package com.os.device;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-23 20:28
 */
public class Disk {
    // 磁盘总块数，扇区大小，引导块位置，超级块位置，超级块大小，交换区大小，inode区位置，inode区大小，磁盘文件区位置、大小
    // 位示图位置、大小
    static final int surfaceCount = 256; // 盘面数
    static final int trackCount = 12800; // 磁道数
    static final int physicalShanQuCount =64 ; // 物理扇区数
    static final long shanQuCount = 209715200;
    static final int shanQuSize = 2; // 单位kb
    static final int inodeSize = 256; // 单位字节
    static final int superBlockSize = 2;//单位kb
    static final int inodeFlagSize = 0; // inode位示图大小
    static final int shanQuFlagSize  = 25; // 扇区位示图大小，MB
    static final int inodeStartSize = 2; // inode表开始扇区号
    static final int inodeCount = 50;
    static final int fileStartAddr = 0; // 开始存文件的扇区号

    public static int getSurfaceCount() {
        return surfaceCount;
    }

    public static int getTrackCount() {
        return trackCount;
    }

    public static int getPhysicalShanQuCount() {
        return physicalShanQuCount;
    }

    public static long getShanQuCount() {
        return shanQuCount;
    }

    public static int getShanQuSize() {
        return shanQuSize;
    }

    public static int getInodeSize() {
        return inodeSize;
    }

    public static int getSuperBlockSize() {
        return superBlockSize;
    }

    public static int getInodeFlagSize() {
        return inodeFlagSize;
    }

    public static int getShanQuFlagSize() {
        return shanQuFlagSize;
    }

    public static int getInodeStartSize() {
        return inodeStartSize;
    }

    public static int getInodeCount() {
        return inodeCount;
    }

    public static int getFileStartAddr() {
        return fileStartAddr;
    }

    // 格式化方法
    public static void format(){
        // 位示图置0
        System.out.println("磁盘已格式化");
        System.out.println("磁盘大小为" + "共" +shanQuCount + "个扇区");
        System.out.println("超级块大小为");
        System.out.println("inode位示图大小为");
        System.out.println("扇区位示图大小为");
        System.out.println("交换区大小为");
        System.out.println("文件存储区大小为");
        System.out.println("inode区大小为");
    }
    public static PhysicalAddr transtAddr(long logicId){
        PhysicalAddr physicalAddr = new PhysicalAddr();
        physicalAddr.setSurfaceId((int) (logicId/trackCount*physicalShanQuCount));
        physicalAddr.setTrackId((int) ((logicId%trackCount*physicalShanQuCount)/physicalShanQuCount));
        physicalAddr.setPhysicalShanQuId((int) ((logicId%trackCount*physicalShanQuCount)%physicalShanQuCount)-1);
        return physicalAddr;
    }

}
