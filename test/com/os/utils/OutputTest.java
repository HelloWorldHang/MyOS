package com.os.utils;

import org.junit.Test;

import java.util.Scanner;

public class OutputTest {

    @Test
    public void bitmapImageOutput() throws Exception {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        Output.bitmapImageOutput( arr );
        System.out.println("\n是否退出程序：Y/N");
        Scanner input = new Scanner(System.in);
        String Y_N = input.nextLine();
        if(Y_N.equals('Y')||Y_N.equals('y')){
            System.exit(0);
        }
    }
}