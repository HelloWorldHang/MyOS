package com.os;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MainTest {

    Main main = new Main();
    @Test
    public void readFromFile() throws IOException {
        String path = "res/进程.txt";
        main.readFromFile(path);
    }

    @Test
    public void main() {
    }

    @Test
    public void sortProcessByPriority() {
    }

    @Test
    public void sortProcessByTime() {
    }

    @Test
    public void sch() {
    }

    @Test
    public void outputProcess() {
    }

    @Test
    public void outPutArr() {
    }

    @Test
    public void outPutPageTable() {
    }
}