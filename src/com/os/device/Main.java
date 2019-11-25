package com.os.device;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-23 19:56
 */
public class Main {
    public static void main(String args[]){
        DeviceControll deviceControll = new DeviceControllImpl();
        deviceControll.format();
        PhysicalAddr physicalAddr = deviceControll.transtAddr(86799);
        System.out.println("盘面号 " + physicalAddr.getSurfaceId());
        System.out.println("磁道号 " + physicalAddr.getTrackId());
        System.out.println("扇区号 " + physicalAddr.getPhysicalShanQuId());
    }

}
