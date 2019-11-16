package com.os.utils;

/**
 * 数组转化工具类
 */
public class ArrayTools {

    /**
     *  一维数组转换成二维数组,最后一行不满补0
     * @param oneArr    待转换的一维数组
     * @param n         二维数组的列数
     * @return
     */
    public static int[][] oneArrIntoTwoArr(int[] oneArr, int n) throws Exception{


        int len = oneArr.length;            //一维数组长度
        int row = (int) Math.ceil( (double)len / n );   //二维数组行数

        if (n < 1){
            throw new Exception("n的取值小于1,错误");
        }
        int[][] twoArr=new int[row][n];

        int flag = 0;

        while (true){
            if (len <= flag)
                break;
            twoArr[flag/n][flag%n] = oneArr[flag];
            flag++;
        }
        return twoArr;
    }

    /**
     * 获取二维数组行与列
     * @param arr
     * @return
     */
    public static int[] getTwoArrRowAndCol(int[][] arr){
        int rows = arr.length;//行数
        int columns = arr[0].length;//列数
        int[] res = {rows,columns};
        return res;
    }
}
