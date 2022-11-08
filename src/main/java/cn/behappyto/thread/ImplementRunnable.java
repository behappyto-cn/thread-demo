package cn.behappyto.thread;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * ImplementCallable
 *
 * @author behappyto.cn
 * @date 2022/11/08 20:08
 */
public class ImplementRunnable {

    /**
     * 程序入口
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // 子线程1
        Thread thread = new Thread(new ChildThread("thread1 - "));
        thread.start();

        // 子线程2
        thread = new Thread(new ChildThread("thread2 - "));
        thread.start();

        // 打印主线程执行的结果
        System.out.println(MessageFormat.format("{0} 执行完成！", Thread.currentThread().getName()));
    }

    /**
     * 子线程
     */
    static class ChildThread implements Runnable {

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
            System.out.println(MessageFormat.format("{0}{1}", this.threadName, LocalDateTime.now()));
        }
    }
}
