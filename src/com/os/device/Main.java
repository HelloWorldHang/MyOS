package com.os.device;

import com.os.device.funInterface.impl.CommandManageImpl;

import java.util.Scanner;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-23 19:56
 */
public class Main {
    public static void main(String args[]){
        /*DeviceControll deviceControll = new DeviceControllImpl();
        deviceControll.format();
        PhysicalAddr physicalAddr = deviceControll.transtAddr(86799);
        System.out.println("盘面号 " + physicalAddr.getSurfaceId());
        System.out.println("磁道号 " + physicalAddr.getTrackId());
        System.out.println("扇区号 " + physicalAddr.getPhysicalShanQuId());*/
        CommandManageImpl commandManage = new CommandManageImpl();

        //用户交互
        System.out.println("-----------------------模拟文件管理系统--------------------------");
        Scanner sc = new Scanner(System.in);
        // 设置当前文件
        FileModel currentFile;
        currentFile = new FileModel("root", 1);
        String currentPath = currentFile.getName();
        System.out.print("[root@localhost " + currentPath + "]# ");
        while (sc.hasNext()){

            String str = sc.nextLine();
            String[] strArr = str.split(" ");
            String command = strArr[0];

            switch (command){
                case "ls":
                    commandManage.lsCommand(currentFile);
                    System.out.print("[root@localhost " + currentPath + "]# ");
                    break;
                case "touch":
                    commandManage.touchCommand(strArr[1],currentFile);

                    System.out.print("[root@localhost " + currentPath + "]# ");
                    break;
                case "vim":
                    System.out.println("vim");
                    System.out.print("[root@localhost " + currentPath + "]# ");
                    break;
                case "mkdir":
                    System.out.print("[root@localhost " + currentPath + "]# ");
                    break;
                case "cd":

                    System.out.print("[root@localhost " + currentPath + "]# ");
                    break;
            }

        }
    }

}
