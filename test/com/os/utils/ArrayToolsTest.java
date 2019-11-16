package com.os.utils;

import org.junit.Test;

public class ArrayToolsTest {

    @Test
    public void oneArrIntoTwoArr() throws Exception {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        ArrayTools.oneArrIntoTwoArr(arr, 4);
    }

    @Test
    public void getTwoArrRowAndCol(){
        int[][] arr = {{1,2,3},{4,45,6,6}};
        ArrayTools.getTwoArrRowAndCol( arr );
    }
}