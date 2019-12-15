package com.os.device.funInterface;

import com.os.device.FileModel;

public interface FileManage {
    FileModel createFile(String name,String mode);
    boolean unlinkFile(String name); // 删除文件，要求用户有写权限
    FileModel open(String name,String mode);
    boolean close(FileModel fd);

    /**
     * 创建文件夹
     * @param name
     * @param mode
     * @return
     */
    boolean createDirectory(String name,String mode);

    /**
     * 删除文件夹
     * @param name
     * @return
     */
    boolean unlinkDirectory(String name);

    /**
     *
     * @param fd FileModel 对象
     * @param buf 读出内容到该字符数组中
     * @param count 需要读多少个字节
     * @return 返回读出的字节数
     */
    int read(FileModel fd, char buf[], int count);

    /**
     * 省去count参数，没有必要
     * @param fd
     * @param buf 要写的东西
     * @return 写入多少字符
     */
    int write(FileModel fd, char buf[]);




}
