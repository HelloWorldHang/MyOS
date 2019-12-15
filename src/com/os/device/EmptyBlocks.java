package com.os.device;

import com.os.utils.Storage;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.List;

public class EmptyBlocks {

    public final static int LEN = 100;
    static int count = 0;  //空闲块数，充当指针
    static List<List<Long>> emptyBlockList = new ArrayList<>();
    //初始化
    static {

    }

    /**
     * 分配
     *
     * @return  返回分配的空闲块的逻辑地址
     */
    static int distribution(Inode inode){
        Storage storage = Storage.getStorage();
        long[] arr = new long[inode.getSize()];
        long addrTemp;
        for (int i=0; i<inode.getSize(); i++){
            addrTemp = storage.popEBS();
            if (addrTemp > 0){
                arr[i] = addrTemp;
            }else {
                System.out.println( "磁盘剩余空间不足！" );
                return -1;
            }
        }
        inode.setArr( arr );
        System.out.println("INFO:   为"+inode.getId()+"号Inode分配了"+inode.getSize()+"个物理块");
        return 0;
    }

    /**
     * 回收
     * @param logicAddr
     * @return
     */
    static int recycle(long logicAddr){
        Storage storage = Storage.getStorage();
        storage.pushEBS( logicAddr );
        return 0;
    }


}
