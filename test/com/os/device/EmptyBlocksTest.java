package com.os.device;

import com.os.utils.Storage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmptyBlocksTest {

    @Test
    public void recycle() {
        for (long i =0; i<500; i++){
            EmptyBlocks.recycle( i );
        }
        System.out.println( Storage.getStorage().getEmptyBlocksStorage() );
//
//
//        for(long i=0; i < 500;i++){
//            EmptyBlocks.recycle(i);
//        }
//        for (int i=0; i<Storage.emptyBlocksStorage.size(); i++)
//        {
//            System.out.println( Storage.count );
//            List<Long> aList = Storage.emptyBlocksStorage.get( i );
//
//            for (int j=0; j<aList.size(); j++)
//            {
//                System.out.print( aList.get( j )+"\t");
//            }
//            System.out.println( );
//        }
//
////        List<List<Long>> listList = Storage.emptyBlocksStorage;
////        System.out.println(listList);

    }
    @Test
    public void distribution() {
        for (long i =0; i<500; i++){
            EmptyBlocks.recycle( i );
        }
        Inode inode = new Inode();
        inode.setSize( 1000 );
        EmptyBlocks.distribution( inode );
        for (long temp : inode.getArr()){
            System.out.print( temp+ "\t" );
        }


    }
}