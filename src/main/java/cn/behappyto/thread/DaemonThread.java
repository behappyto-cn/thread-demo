package cn.behappyto.thread;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * Daemon
 *
 * @author behappyto.cn
 * @date 2022/11/08 19:45
 */
public class DaemonThread {

    /**
     * 程序入口
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // 子线程1
        ChildThread childThread = new ChildThread("线程1 - ");
        // 标记为守护线程
        childThread.setDaemon(true);
        childThread.start();

        // 子线程2
        childThread = new ChildThread("线程2 - ");
        // 标记为守护线程
        childThread.setDaemon(true);
        childThread.start();

        // 打印主线程执行的结果
        System.out.println(MessageFormat.format("{0} 执行完成！", Thread.currentThread().getName()));
    }

    /**
     * 子线程
     */
    static class ChildThread extends Thread {

        /**
         * 线程名称
         */
        private final String threadName;

        /**
         * 有参构造函数
         *
         * @param threadName 线程名称
         */
        public ChildThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(MessageFormat.format("{0}{1}", this.threadName, LocalDateTime.now()));
        }
    }
}
