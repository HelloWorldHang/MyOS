package com.os.utils;

import com.os.device.EmptyBlocks;

import java.util.*;

/**
 * 存储类
 */
public class Storage {
    private static Storage storage;
    /**
     * 存放成组链接表
     */
    private int count;
    private List<List<Long>> emptyBlocksStorage = new ArrayList<>();

    public List<List<Long>> getEmptyBlocksStorage() {
        return emptyBlocksStorage;
    }

    static{
        storage = new Storage();
    }
    private Storage(){

    }
    public static Storage getStorage(){
        return storage;
    }

    /**
     * 返回最后一个元素，然后删除
     * @return
     */
    public long popEBS(){

        count = emptyBlocksStorage.size()-1;
        if (count < 0){
            System.out.println( "ERROR:剩余空间已用尽" );
            return -1;
        }
        List<Long> listTemp = this.emptyBlocksStorage.get(count);
        Long res = listTemp.get( listTemp.size()-1 );
        listTemp.remove( listTemp.size()-1 );
        if (listTemp.size()>0)
        {
            this.emptyBlocksStorage.set( count, listTemp );
        }else {
            this.emptyBlocksStorage.remove( count );
        }

        return res;
    }

    /**
     * 将元素添加到末尾
     * @param logicAddr
     */
    public void pushEBS(long logicAddr){
        List<Long> listTemp;
        count = emptyBlocksStorage.size()-1;

        if (count > -1) {
            listTemp = this.emptyBlocksStorage.get( count );
        }else {
            listTemp = new ArrayList<>(  );
            count = 0;
        }
            if(listTemp.size() < EmptyBlocks.LEN){
                listTemp.add( logicAddr );
                if (emptyBlocksStorage.size() > 0){
                    emptyBlocksStorage.set( count, listTemp );
                }else{
                    emptyBlocksStorage.add( listTemp );
                }

            }else {
                listTemp = new ArrayList<Long>();
                listTemp.add( logicAddr );
                emptyBlocksStorage.add( listTemp );
            }
    }


}
