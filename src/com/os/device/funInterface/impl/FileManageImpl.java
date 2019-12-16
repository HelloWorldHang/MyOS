package com.os.device.funInterface.impl;

import com.os.device.FileModel;
import com.os.device.Inode;
import com.os.device.funInterface.FileManage;

import java.util.*;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-12-09 16:09
 */
public class FileManageImpl implements FileManage {
    public static int[][] arrInode = new int[50][8]; // inode位示图
    public static Map<Long,Inode> inodeAllMap = new HashMap<>(); // 所有inode表
    public static Map<Long,Inode> inodeMap = new HashMap<>(); // inode活动表
    public static Map<String,FileModel> fileModelMap = new HashMap<>(); // 用户打开文件表

    public static List<List<Integer>> list;

    public FileManageImpl() {
        list = init();
    }

    /**
     * 该方法只根据文件名创建文件，不设置subFile和father
     * 该方法兼具文件打开功能,所以要向用户打开文件表中加入
     * @param name
     * @param mode
     * @return 文件对象供上层修改
     */
    @Override
    public FileModel createFile(String name, String mode) {
        FileModel fileModel = new FileModel(name, 0);

        // 修改inode位示图将数组下标作为inodeID,new 一个inode 并将inode号写入fileModel
        int id = magBitMap(arrInode);
        Inode inode = new Inode();
        inode.setId(id);
        inode.setMode(mode);
        inode.setLink(1);
        // 把inodeId写入mileModel中
        fileModel.setInodeId(id);
        inodeMap.put(inode.getId(), inode);
        inodeAllMap.put(inode.getId(), inode);
        // System.out.println(inodeAllMap);
        // 加入用户打开文件表
        fileModelMap.put(fileModel.getName(), fileModel);

        // 创建完关闭
        close(fileModel);

        // 组成新的目录项，在该程序中即为在subFile里添加文件,以在上层实现
        // 先不设置其父亲，待返回后在上一层设置其父亲
        return fileModel;
    }

    @Override
    public boolean unlinkFile(String name, FileModel currentFileModel) {
        Map<String, FileModel> subFile = currentFileModel.subFile;
        // 位示图置0
        FileModel fd = subFile.get(name);
        long inodeId = fd.getInodeId();
        Inode inode = inodeAllMap.get(inodeId);
        // 回收
        recycle(inode,list);
        delArrInode(inodeId);
        subFile.remove(name);

        return true;
    }

    @Override
    public FileModel open(String name, String mode, FileModel currentModel) {
        if (currentModel.subFile.containsKey(name) && currentModel.subFile.get(name).getType()==0){
            FileModel fd = currentModel.subFile.get(name);
            long inodeId = fd.getInodeId();
            Inode inode = inodeAllMap.get(inodeId);
            // 往inode内存活动表中添加
            inodeMap.put(inodeId, inode);
            // 往用户打开文件表中添加
            fileModelMap.put(fd.getName(), fd);
            return fd;
        }
        return null;
    }

    @Override
    public boolean close(FileModel fd) {
        // 删除内存活动inode表
        inodeMap.remove(fd.getInodeId());
        // 删除用户打开文件表
        fileModelMap.remove(fd.getName());
        return true;
    }

    @Override
    public FileModel createDirectory(String name, String mode) {
        FileModel fileModel = new FileModel(name, 1);

        // 修改inode位示图将数组下标作为inodeID,new 一个inode 并将inode号写入fileModel
        int id = magBitMap(arrInode);
        Inode inode = new Inode();
        inode.setId(id);
        inodeMap.put((long)id, inode);
        // 组成新的目录项，在该程序中即为在subFile里添加文件,以在上层实现
        // 先不设置其父亲，待返回后在上一层设置其父亲
        return fileModel;
    }

    @Override
    public boolean unlinkDirectory(String name) {
        return false;
    }

    @Override
    public int read(FileModel fd, char[] buf, int count) {
        if (count <= 0){
            return -1;
        }
        String content = fd.getContent();
        String substring = content.substring(0, count);
        buf = substring.toCharArray();
        return buf.length;
    }

    @Override
    public int write(FileModel fd, char[] buf) {
        // 在commandManage中打开文件并返回fd
        fd.setContent(String.valueOf(buf));
        long inodeId = fd.getInodeId();
        Inode inode = inodeMap.get(inodeId);
        // inode.setSize(buf.length*2/1024);
        // distribution(inode, list);
        // System.out.println("写测试---------" + fd);
        return 0;
    }

    public int magBitMap(int[][] bitMap){
        int id = 0;
        int rowLlen = bitMap.length;
        int colLen = bitMap[0].length;
        for (int i = 0; i < rowLlen; i++){
            for (int j = 0; j < colLen; j++){
                if (bitMap[i][j] == 0){
                    if (i == 0){
                        id = j+1;
                        return id;
                    }else {
                        id = (i)*8+j+1;
                        return id;
                    }
                }
            }
        }
        return -1;
    }

    public void delArrInode(long id){
        int m = (int)id / 8;
        int n = (int)id % 8;
        arrInode[m][n] = 0;
    }

    /**
     * 初始化成组链接块
     * @return
     */
    public static List<List<Integer>> init(){
        List<List<Integer>> emptyBlockList= new ArrayList();
        int recyclingNum=102;//文件区的开始盘块号
        for(int i = 0; i<4;i++){
            List<Integer> tempList = new ArrayList<>();
            int count = 0;
            tempList.add(0,count);
            for (int j = recyclingNum; j <500;j++){//假设有500个盘块
                if (tempList.get(0) <100){
                    count++;
                    tempList.remove(0);
                    tempList.add(0,count);
                    tempList.add(recyclingNum);
                    recyclingNum+=1;
                }
                else{
                    break;
                }
            }
            if (i == 0){
                tempList.add(1,0);
            }else {
                int next = emptyBlockList.get(0).get(emptyBlockList.get(0).size()-1)+1;
                tempList.remove(1);
                tempList.add(1,next);
            }
            emptyBlockList.add(0,tempList);
        }
//        myprint(emptyBlockList);
        return emptyBlockList;
    }

    /**
     * 打印
     * @param emptyBlockList
     */
    public void myprint(List<List<Integer>> emptyBlockList){
        for (int i = 0; i < emptyBlockList.size(); i++){
            System.out.println(emptyBlockList.get(i));
        }
    }

    /**
     * 成组链接分配
     * @param inode
     * @param emptyBlockList
     * @return
     */
    public long[] distribution(Inode inode,List<List<Integer>> emptyBlockList){
        int size = inode.getSize();//获得文件大小，单位为kb
        long arr[] = new long[size+1];//记录已分配的块号
        int disBlockIndex=0;//被分配的盘块所在的下标
        int disBlock = 0;//初始化被分配的盘块号
        for (int i = 0; i < size+1; i++){
            int count = emptyBlockList.get(0).get(0);//获得专用块的空闲盘块数
            count-=1;
            if (count == 0){//如果当前块被分配 出去后，count=0
                disBlock=emptyBlockList.get(0).get(1);//记录将要分配的盘块号
                emptyBlockList.get(0).remove(1);//从专用块中删除
                emptyBlockList.get(0).addAll(emptyBlockList.get(1));//加载下一块到专用块
                emptyBlockList.get(0).remove(0);
                emptyBlockList.remove(1);
                arr[i]=disBlock;//将此盘块号分配出去
            }else {
                emptyBlockList.get(0).set(0,count);//修改count
                disBlockIndex=emptyBlockList.get(0).size()-1;
                arr[i]=emptyBlockList.get(0).get(disBlockIndex);//分配空闲块
                emptyBlockList.get(0).remove(disBlockIndex);//从专用块中删除此盘块号
            }
        }
        inode.setArr(arr);
//        System.out.println("-------------------------------所分配的块------------------------------");
//        System.out.println(Arrays.toString(arr));
        return arr;
    }

    /**
     * 成组链接回收
     * @param inode
     * @param emptyBlockList
     */
    public void recycle(Inode inode,List<List<Integer>> emptyBlockList){
        List<Integer> tempList = new ArrayList<>();
        long[] arr = inode.getArr();
        tempList.add(1);
        for (int i = 0; i < arr.length;i++){
            int count = emptyBlockList.get(0).get(0);
            count++;
            if (count>100){
                long next = arr[i];
                emptyBlockList.add(0,tempList);
                emptyBlockList.get(0).add((int) next);
            }else {
                emptyBlockList.get(0).add((int) arr[i]);
                emptyBlockList.get(0).set(0,count);
            }
        }
    }

}
