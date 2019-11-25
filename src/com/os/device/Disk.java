package com.os.device;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-23 20:28
 */
public class Disk {
    // 磁盘总块数，扇区大小，引导块位置，超级块位置，超级块大小，交换区大小，inode区位置，inode区大小，磁盘文件区位置、大小
    // 位示图位置、大小
     static final int surfaceCount = 256; // 盘面数
     static final int trackCount = 12800; // 每个盘面磁道数
     static final int physicalShanQuCount =64 ; // 物理扇区数
     static final long shanQuCount = 209715200;
     private static List<ShanQu> shanQuList = new ArrayList<>(209715199);
     static final int shanQuSize = 2; // 单位kb
     static final int inodeSize = 256; // 单位字节
     static final int superBlockSize = 2;//单位kb
     static final int inodeFlagSize = 1; // inode位示图大小,占用1个扇区
     static final int fileFlagSize  = 12799; // 文件位示图大小,单位扇区个数
     static final int inodeCount = 400; // inode数量
    static final int inodeStartAddr = 12801; // 第12801个扇区开始
     static final Swap swap = new Swap();
     static final int fileStartAddr = 29235; // 开始存文件的扇区号
    static final long fileShanQuCount = shanQuCount - fileStartAddr;

    public static List<ShanQu> getShanQuList() {
        return shanQuList;
    }

    public static void setShanQuList(List<ShanQu> shanQuList) {
        Disk.shanQuList = shanQuList;
    }
}
