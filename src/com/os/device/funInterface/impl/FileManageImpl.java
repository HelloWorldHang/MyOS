package com.os.device.funInterface.impl;

import com.os.device.FileModel;
import com.os.device.Inode;
import com.os.device.funInterface.FileManage;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-12-09 16:09
 */
public class FileManageImpl implements FileManage {
    public static int[][] arrInode = new int[50][8];
    public static List<Inode> inodeList = new ArrayList<Inode>();
    /**
     * 该方法只根据文件名创建文件，不设置subFile和father
     * @param name
     * @param mode
     * @return 文件对象供上层修改
     */
    @Override
    public FileModel createFile(String name, String mode) {
        FileModel fileModel = new FileModel(name, 0);

        // 修改inode位示图将数组下标作为inodeID,new 一个inode 并将inode号写入fileModel

        // new Inode()
        // 组成新的目录项，在该程序中即为在subFile里添加文件

        // 先不设置其父亲，待返回后在上一层设置其父亲
        return fileModel;
    }

    @Override
    public boolean unlinkFile(String name) {
        return false;
    }

    @Override
    public FileModel open(String name, String mode) {
        return null;
    }

    @Override
    public boolean close(FileModel fd) {
        return false;
    }

    @Override
    public boolean createDirectory(String name, String mode) {
        return false;
    }

    @Override
    public boolean unlinkDirectory(String name) {
        return false;
    }

    @Override
    public int read(FileModel fd, char[] buf, int count) {
        return 0;
    }

    @Override
    public int write(FileModel fd, char[] buf) {
        return 0;
    }

}
