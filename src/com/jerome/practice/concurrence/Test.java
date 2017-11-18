package com.jerome.practice.concurrence;


import java.io.IOException;
import java.util.logging.*;

public class Test {
    private volatile int inc = 0;
    private void increase() {
        inc++;
    }
    public static void main(String[] args) throws IOException {
        final Test test = new Test();
        final Logger logger = Logger.getLogger("Concurrence test");
        ConsoleHandler allConsoleHandler = new ConsoleHandler();
        //同Log一样，高等级的Handler会处理不高于此等级的log文本
        allConsoleHandler.setLevel(Level.ALL);
        FileHandler fileHandler = new FileHandler("D:/Log.txt");
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new XMLFormatter());

        logger.addHandler(allConsoleHandler);
        logger.addHandler(fileHandler);
        // 设置logger显示log的最低等级，默认值为Level.INFO
        logger.setLevel(Level.FINER);

        for (int i = 0; i < 10; i++) {
            final int x = i;
            new Thread(() -> {
                logger.log(Level.FINE, "Thread " + x + " starts.");
                for (int j = 0; j < 1000; j++) {
                    test.increase();
                    // 如果加上下面这行，会令结果恒为10000
                    // System.out.println("Thread " + x + ": add " + j);
                }
            }).start();
        }
        //保证前面的线程都执行完
        // IDEA 启动了一个Monitor守护线程，会导致线程数多1
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        String result = String.valueOf(test.inc);
        // 以下写法等价
        logger.log(Level.INFO, result);
        logger.info(result);
    }
}
