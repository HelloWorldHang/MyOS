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

            return null;
        }else{ // 沿着当前目录继续查找
            Map<String, FileModel> subFileModels = currentModel.subFile;
            FileModel file = subFileModels.get(path);
            if (file == null){
                System.out.println("-bash: cd: " + path + ": No such file or directory");
                return currentModel.getName();
            }else{
                currentModel = file; // 修改当前文件为子文件
                return currentModel.getName();
            }

        }

    }

    @Override
    public boolean touchCommand(String fileName, FileModel currentFileModel) {
        Map<String, FileModel> subFiles = currentFileModel.subFile;
        if (subFiles.get(fileName) == null){
            // 调用底层接口
            FileModel fileModel = fileManage.createFile(fileName, "model");
            // 添加目录文件
            subFiles.put(fileName, fileModel);
            System.out.println("创建成功");
            return true;
        }else{
            System.out.println(fileName + "已经存在");
            return false;
        }
    }

    @Override
    public void lsCommand(FileModel currentFileModel) {
        Map<String, FileModel> files = currentFileModel.subFile;
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
}
