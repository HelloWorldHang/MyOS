package com.os.device;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-12-09 11:17
 */
public class FileModel {
    //FileModel类用来记录文件或目录的相关属性

    public Map<String, FileModel> subFile = new HashMap<String, FileModel>();
    private String name; //文件名或目录名
    private int type; //用来识别是文件还是目录,0表示文件 1表示目录
    private FileModel father = null; //该文件或目录的上级目录
    private long inodeId;

    public long getInodeId() {
        return inodeId;
    }

    public void setInodeId(long inodeId) {
        this.inodeId = inodeId;
    }

    public FileModel(String name, int type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAttr() {
        return type;
    }
    public void setAttr(int type) {
        this.type = type;
    }

    public FileModel getFather() {
        return father;
    }

    public void setFather(FileModel father) {
        this.father = father;
    }
}
