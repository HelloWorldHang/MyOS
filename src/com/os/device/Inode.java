package com.os.device;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-22 20:15
 */
public class Inode {
    private long id;
    private int count;
    private int size; // 文件大小
    private int link; // 硬链接数
    private String mode; // 权限
    private long[] arr = new long[16]; // block的位置

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public long[] getArr() {
        return arr;
    }

    public void setArr(long[] arr) {
        this.arr = arr;
    }


}
