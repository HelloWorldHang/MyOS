package com.os.device;

/**
 * Created by lyx on 2019/12/9.
 */
public class SysFile {
    private String f_flags;//读写标志
    private int f_count;//共享个数
    private int f_offset;//文件位移量
    private int f_inode;//文件inode

    public SysFile() {
        super();
    }

    public String getF_flags() {
        return f_flags;
    }

    public void setF_flags(String f_flags) {
        this.f_flags = f_flags;
    }

    public int getF_count() {
        return f_count;
    }

    public void setF_count(int f_count) {
        this.f_count = f_count;
    }

    public int getF_offset() {
        return f_offset;
    }

    public void setF_offset(int f_offset) {
        this.f_offset = f_offset;
    }

    public int getF_inode() {
        return f_inode;
    }

    public void setF_inode(int f_inode) {
        this.f_inode = f_inode;
    }

    @Override
    public String toString() {
        return "SysFile{" +
                "f_flags='" + f_flags + '\'' +
                ", f_count=" + f_count +
                ", f_offset=" + f_offset +
                ", f_inode=" + f_inode +
                '}';
    }
}
