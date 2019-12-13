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
    public static int count = 2;
    public static List<List<Long>> emptyBlocksStorage = new ArrayList<List<Long>>();
    
    static{
        storage = new Storage();
    }
    private Storage(){

    }
    public static Storage getStorage(){
        return storage;
    }
}
