package com.os.device.funInterface;

import com.os.device.FileModel;

public interface CommandManage {
    /**
     *
     * @param path 传入要进入的目录和当前fileModel
     * @return currentPath(只是一个文件夹)
     */
    String cdCommand(String path, FileModel currentFile);

    /**
     *
     * @param fileName 要创建的文件名
     * @param currentFileModel 当前文件
     * @return 是否创建成功
     */
    boolean touchCommand(String fileName, FileModel currentFileModel);

    void lsCommand(FileModel currentFileModel);

    boolean mkdirCommand(String fileName, FileModel currentFileModel);

    String vimCommand(FileModel currentFileModel, String name);

    void writeCommand(FileModel currentFileModel, String name, String content);

    void link(FileModel currentFileModel, String name, String path);

    void unlink(FileModel currentFileModel, String name);
}
