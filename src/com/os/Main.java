package com.os;

import com.os.bean.MyProcess;
import com.os.bean.PageFrame;
import com.os.bean.PageTab;

import java.io.*;
import java.util.*;

import static com.os.utils.ArrayTools.oneArrIntoTwoArr;
import static com.os.utils.Output.bitmapImageOutput;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-11-11 16:53
 */
public class Main {
    // 页面大小
    static float pageSize = 2;

    // 页框List
    static ArrayList<PageFrame> pageFrameList = new ArrayList<>();

    // 位示图,初始全为0
    static int[] arr = new int[8192];

    public static void main(String args[]) throws Exception {
        String path = "res/进程.txt";
        List<MyProcess> proList = readFromFile(path);
        outputProcess(proList);
        sch(proList);

    }

    // 根据文件名读取文件
    public static List<MyProcess> readFromFile(String path) throws IOException {
        List<String> strList = new ArrayList<>();
        File file = new File( path );
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        // 读取第一行，第一行没用
        line = br.readLine();
        while (line != null){
            strList.add(line);
            line = br.readLine();
        }
        int n = strList.size();
        List<MyProcess> proList = new ArrayList<>();
        for (int i = 0; i < n; i++){
            String str = strList.get(i);
            String[] s = str.split(" ");
            MyProcess pro = new MyProcess();
            pro.setName(s[0]);
            pro.setTimeOfArrival(Integer.valueOf(s[1]));
            pro.setTimes(Integer.valueOf(s[2]));
            pro.setPriority(Integer.valueOf(s[3]));
            pro.setRequiredMemorySize(Integer.valueOf(s[4]));
            proList.add(pro);

        }

        return proList;
    }

    // 按优先级排序
    public static void sortProcessByPriority(List<MyProcess> proList) {
        Collections.sort(proList,new Comparator<MyProcess>() {

            @Override
            public int compare(MyProcess o1, MyProcess o2) {
                if(o1.getPriority()>o2.getPriority()) {
                    return 1;
                }else if (o1.getPriority() == o2.getPriority()) {
                    return 0;
                }else {
                    return -1;
                }
            }

        });


    }

    // 按到达时间排序
    public static List<MyProcess> sortProcessByTime(List<MyProcess> proList) {


        Collections.sort( proList, new Comparator<MyProcess>() {
            @Override
            public int compare(MyProcess m1, MyProcess m2) {
                int diff = m1.getTimeOfArrival() - m2.getTimeOfArrival();
                if (diff > 0) {
                    return 1;
                } else if (diff < 0) {
                    return -1;
                }
                return 0; //相等为0
            }
        } ); // 按到达时间排序
        return proList;
    }

    /**
     * 进程调度
     * @param proList
     */
    public static void sch(List<MyProcess> proList) throws Exception {

        // 一开始就分配两个页框给OS内核
        for (int i = 0; i < 2; i++){
            arr[i] = 1;
        }
        outPutArr(arr);
        int current = 0; // 当前时间从0开始
        System.out.println("时间\t" + "进程名\t" + "进程状态\t" + "优先级\t" + "剩余时间\t");
        while (proList.size() != 0){
            sortProcessByPriority(proList);
            sortProcessByTime(proList);

            // 得到首进程
            MyProcess process = proList.get(0);

            // 没有装入内存则装入内存
            if (!process.isFlag()){
                allocateMemory(process);
                System.out.println(process.getName() + "装入内存");
            }

            // CPU执行该进程
            if (current >= process.getTimeOfArrival()){
                // 更改进程状态为运行态
                process.setStatus(2);

                System.out.println(current+"\t" + process.getName()+"\t\t" + process.getStatus()+"\t\t" + process.getPriority()+"\t\t" + process.getTimes());
                // 降低优先级
                process.setPriority(process.getPriority()+2);
                current+=2;
                // 剩余运行时间
                int runTime = process.getTimes() - 2;
                if (runTime <= 0){
                    if (runTime != 0){
                        current-=1; // 因为上面加2多加了1
                    }
                    process.setTimes(0);
                    process.setStatus(0);
                    proList.remove(process); // 删除该进程
                    System.out.println("时间为" + current + "时 " + process.getName() + "进程运行结束");
                    // 释放内存
                    unloadMemory(process);
                }else{
                    process.setTimes(runTime);
                }

            }else{

                System.out.println("时间为" + current + "时CPU空闲");
                current++;
            }

        }

    }

    /**
     * 传入进程，分配内存
     * @param process
     */
    private static void allocateMemory(MyProcess process) throws Exception {
        process.setFlag(true);
        // 计算该进程需要的页框数
        int pageFrameCount = (int)Math.ceil((double) process.getRequiredMemorySize() / pageSize);

        // 更新位示图
        int pageId = 0;

        for(int i = 0;pageId < pageFrameCount && i < arr.length;i++){

            //此页框未占用
            if(arr[i] == 0){
                arr[i] = 1;     //标记占用
                PageTab pageTab = new PageTab(pageId, i);   //页面-页框
                process.addPageTab( pageTab );
                pageId++;
            }
        }
        outPutArr(arr);
        outPutPageTable(process);
    }

    private static void unloadMemory(MyProcess process) throws Exception {
        for (PageTab pageTab: process.getPageTabList()){
            arr[pageTab.getPageFrameId()] = 0;
        }
        outPutArr(arr);
    }


    public static void outputProcess(List<MyProcess> proList) {
        System.out.println("进程ID\t进程名\t进程状态\t进程到达时间\t进程运行时间\t进程所需内存大小");
        for (MyProcess pro:proList
             ) {
            System.out.println(pro.getId()+"\t\t"+pro.getName()+"\t\t"+pro.getStatus()+"\t\t"+pro.getTimeOfArrival()
            +"\t\t\t"+pro.getTimes()+"\t\t\t"+pro.getRequiredMemorySize());
        }
    }

    /**
     * 输出位示图
     * @param arr
     */
    public static void outPutArr(int[] arr) throws Exception {
        System.out.println("当前位示图");
        bitmapImageOutput(oneArrIntoTwoArr(arr, 10));
    }

    /**
     * 输出进程页表
     * @param pro
     */
    public static void outPutPageTable(MyProcess pro){
        List<PageTab> pageTabList = pro.getPageTabList();
        System.out.println(pro.getName() + "进程页表");
        for (PageTab tab:pageTabList
             ) {
            System.out.println(tab.getPageId() + "  " + tab.getPageFrameId());
        }
        System.out.println();
    }



}
