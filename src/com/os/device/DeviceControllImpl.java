package com.os.device;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-25 10:12
 */
public class DeviceControllImpl implements DeviceControll {
    Disk disk = new Disk();
    /**
     * 磁盘格式化
     */
    @Override
    public void format() {
        // 位示图置0
        System.out.println("-----------磁盘物理信息---------");
        System.out.println("磁盘大小为400G" + "共" + Disk.surfaceCount + "个盘面，每个盘面" + Disk.trackCount + " 个磁道\n每个磁道" +
                Disk.physicalShanQuCount + "个扇区共" + Disk.shanQuCount + " 个扇区，每个扇区大小为2 KB");
        System.out.println("-----------磁盘已格式化---------");
        System.out.println("超级块大小为 " + Disk.superBlockSize + " KB");
        System.out.println("inode位示图大小为 " + Disk.inodeFlagSize + " 个扇区");
        System.out.println("文件存储区位示图大小为" + Disk.fileFlagSize + " 个扇区");
        System.out.println("inode节点大小为 " + Disk.inodeSize + " 字节,共 " + Disk.inodeCount + " 个inode");
        System.out.println("inode区大小为" + Disk.inodeCount/8 + " 个扇区,从第 " + Disk.inodeStartAddr + " 个扇区开始");
        System.out.println("交换区大小为 " + Disk.swap.swapSize + " MB");
        System.out.println("文件存储区从第 " + Disk.fileStartAddr + " 个扇区开始，大小为 " + Disk.fileShanQuCount + " 个扇区");
        /*List<ShanQu> shanQuList = new ArrayList<>();
        for (int i = 0; i < 209715199; i++){
            ShanQu shanQu = new ShanQu();
            PhysicalAddr physicalAddr = transtAddr(i);
            shanQu.setAddr(physicalAddr);
            shanQuList.add(shanQu);
        }
        disk.setShanQuList(shanQuList);
        disk.getShanQuList().forEach(System.out::println);*/
    }

    /**
     * 逻辑地址转物理地址
     * @param logicId
     * @return
     */
    @Override
    public PhysicalAddr transtAddr(long logicId) {
        PhysicalAddr physicalAddr = new PhysicalAddr();
        int trackId,DiskId,shanquId;
        trackId = (int) (logicId/(Disk.surfaceCount*Disk.physicalShanQuCount));
        DiskId = (int) ((logicId%(Disk.surfaceCount*Disk.physicalShanQuCount))/Disk.physicalShanQuCount);
        shanquId= (int) ((logicId%(Disk.surfaceCount*Disk.physicalShanQuCount))%Disk.physicalShanQuCount);
        physicalAddr.setTrackId(trackId);
        physicalAddr.setSurfaceId(DiskId);
        physicalAddr.setPhysicalShanQuId(shanquId);
        return physicalAddr;
    }
}
