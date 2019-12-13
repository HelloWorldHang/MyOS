package com.os.device;

import com.os.utils.Storage;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.List;

public class EmptyBlocks {

    final static int LEN = 100;
    static int count = 0;  //空闲块数，充当指针
    static List<Long> emptyBlockList = new ArrayList<Long>();

    /**
     * 分配
     *
     * @return  返回分配的空闲块的逻辑地址
     */
    static List<Long> distribution(int n){
        List<Long> resList = new ArrayList<>(  );
        while (n>0){
            if (count < 1) {
                if (Storage.emptyBlocksStorage.size() == 0) {
                    System.out.println( "剩余空间已用尽" );
                    break;
                }

                emptyBlockList = Storage.emptyBlocksStorage.get( emptyBlockList.get( 0 ).intValue() );
                count = 99;
            }
            System.out.println( "emptyBlockList"+emptyBlockList.toString() );
            resList.add(emptyBlockList.get(count));
            count--;
            n--;

        }
        return resList;
    }
    //回收
    static int recycle(long LogicAddr){
        if (count > LEN){
            Storage.emptyBlocksStorage.add(emptyBlockList );
            emptyBlockList = new ArrayList<>(  );
            emptyBlockList.add( (long)Storage.count);
            Storage.count++;
            count = 1;
        }
        if (emptyBlockList.size() == 0)
            emptyBlockList.add((long)1);
        emptyBlockList.add(LogicAddr );
        count++;
        return 0;
    }


}
