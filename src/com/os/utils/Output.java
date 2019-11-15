package com.os.utils;


import javax.swing.*;
import java.awt.*;

;


/**
 * 输出类
 */
public class Output {

    /**
     * 位示图图像输出
     * @param arr
     */
    public static void bitmapImageOutput(int[][] arr) {

        JFrame jf = new JFrame( "位示图窗口" );
        jf.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        // 创建内容面板
        JPanel panel = new JPanel();

        int rows = arr.length;//行数
        int columns = arr[0].length;//列数
        // 表头（列名）
        String[] columnNames = new String[columns];
        for (int i=0; i<columnNames.length;i++){
            columnNames[i] = " ";
        }


        int count = 0;
        // 表格所有行数据
        Object[][] rowData = new Object[rows][columns];
        for (int i=0; i<rows; i++){
            for (int j = 0; j<columns;j++){

                rowData[i][j] = new String(count+"号页框"+
                        (arr[i][j]== 1?"已分配":"空闲中"));
                count++;
            }
        }
        {

            // 创建一个表格，指定 表头 和 所有行数据
            JTable table = new JTable( rowData, columnNames );

            // 设置表格内容颜色
            table.setForeground( Color.BLACK );                   // 字体颜色
            table.setFont( new Font( null, Font.PLAIN, 14 ) );      // 字体样式
            table.setSelectionForeground( Color.DARK_GRAY );      // 选中后字体颜色
            table.setSelectionBackground( Color.LIGHT_GRAY );     // 选中后字体背景
            table.setGridColor( Color.GRAY );                     // 网格颜色

            // 设置表头
            table.getTableHeader().setFont( new Font( null, Font.BOLD, 14 ) );  // 设置表头名称字体样式
            table.getTableHeader().setForeground( Color.RED );                // 设置表头名称字体颜色
            table.getTableHeader().setResizingAllowed( true );               // 设置不允许手动改变列宽
            table.getTableHeader().setReorderingAllowed( false );             // 设置不允许拖动重新排序各列

            // 设置行高
            table.setRowHeight( 30 );

            // 第一列列宽设置为40
//            table.getColumnModel().getColumn( 0 ).setPreferredWidth( 40 );

            // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
            table.setPreferredScrollableViewportSize( new Dimension( 1200, 700 ) );

            // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
            JScrollPane scrollPane = new JScrollPane( table );

            // 添加 滚动面板 到 内容面板
            panel.add( scrollPane );

            // 设置 内容面板 到 窗口
            jf.setContentPane( panel );

            jf.pack();
            jf.setLocationRelativeTo( null );
            jf.setVisible( true );

        }
    }
}
