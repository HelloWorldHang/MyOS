package com.os.device.funInterface.impl;

import com.os.device.FileModel;
import com.os.device.funInterface.CommandManage;
import com.os.device.funInterface.FileManage;

import java.util.Map;
import java.util.Set;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-12-09 17:30
 */
public class CommandManageImpl implements CommandManage {
    FileManage fileManage = new FileManageImpl();
    @Override
    public String cdCommand(String path, FileModel currentModel) {
        if (path.contains("/")){
            // 调用search方法查找
            String[] split = path.split("/");
            String s = split[2]; // root下的目录
            FileModel father = currentModel.getFather();
            FileModel fileLink = father.subFile.get(s);
            currentModel = fileLink;
            return s;
        }else{ // 沿着当前目录继续查找
            Map<String, FileModel> subFileModels = currentModel.subFile;
            FileModel file = subFileModels.get(path);
            if (file == null){
                System.out.println("-bash: cd: " + path + ": No such file or directory");
                return currentModel.getName();
            }else{
                if (file.getType()==0){
                    System.out.println("错误");
                    return currentModel.getName();
                }
                currentModel = file; // 修改当前文件为子文件
                // System.out.println(currentModel);
                return currentModel.getName();
            }

        }

    }

    @Override
    public boolean touchCommand(String fileName, FileModel currentFileModel) {
        Map<String, FileModel> subFiles = currentFileModel.subFile;
        if (subFiles.get(fileName) == null){
            // 调用底层接口
            FileModel fileModel = fileManage.createFile(fileName, "0775");
            // 添加目录文件
            subFiles.put(fileName, fileModel);
            fileModel.setFather(currentFileModel);
            System.out.println("创建成功");
            return true;
        }else{
            System.out.println(fileName + "已经存在");
            return false;
        }
    }

    @Override
    public void lsCommand(FileModel currentFileModel) {
        // System.out.println("测试-------"+currentFileModel);
        Map<String, FileModel> files = currentFileModel.subFile;
        // System.out.println("测试---------" + files);
        if (files.isEmpty()){
            System.out.println("没有文件或目录");
        }else{
            Set<String> strings = files.keySet();
            for (String str: strings
            ) {
                System.out.print(str + "   ");
            }
            System.out.println();
        }

    }

    @Override
    public boolean mkdirCommand(String fileName, FileModel currentFileModel) {
        Map<String, FileModel> subFiles = currentFileModel.subFile;
        if (subFiles.get(fileName) == null){
            // 调用底层接口
            FileModel fileModel = fileManage.createDirectory(fileName, "model");
            // 添加目录文件
            subFiles.put(fileName, fileModel);
            fileModel.setFather(currentFileModel);
            System.out.println("创建成功");
            return true;
        }else{
            System.out.println(fileName + "已经存在");
            return false;
        }
    }

    @Override
    public String vimCommand(FileModel currentFileModel, String name) {
        // 先打开文件返回filemodel
        FileModel fd = fileManage.open(name, "0775", currentFileModel);
        // System.out.println("读测试-----------" + fd);
        char[] buf = new char[100];
        if (fd != null){
            int read = fileManage.read(fd, buf, fd.getContent().length());

            if (read == -1){
                fileManage.close(fd);
                // currentFileModel = fd.getFather();
                return "";
            }
            fileManage.close(fd);
            return fd.getContent();
        }
        fileManage.close(fd);
        return "没有此文件或文件打开异常";
    }

    @Override
    public void writeCommand(FileModel currentFileModel, String name, String content) {
        FileModel fd = fileManage.open(name, "0775", currentFileModel);
        int write = fileManage.write(fd,content.toCharArray());
        fileManage.close(fd);
    }

    @Override
    public void link(FileModel currentFileModel, String name, String path) {
        FileModel file = currentFileModel.subFile.get(name);
        String[] split = path.split("/");
        String s = split[2]; // root下的目录
        FileModel father = currentFileModel.getFather();
        FileModel fileLink = father.subFile.get(s);
        Map<String, FileModel> fileLinkSubFile = fileLink.subFile;
        fileLinkSubFile.put(name, file);
        // System.out.println(fileLinkSubFile);
    }

    @Override
    public void unlink(FileModel currentFileModel, String name) {
        fileManage.unlinkFile(name,currentFileModel);
    }
}
