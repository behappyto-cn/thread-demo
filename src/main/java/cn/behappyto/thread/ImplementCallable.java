package cn.behappyto.thread;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ImplementCallable
 *
 * @author behappyto.cn
 * @date 2022/11/08 20:08
 */
public class ImplementCallable {

    /**
     * 程序入口
     *
     * @param args 参数
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new ChildThread("thread1 - "));
        Thread thread = new Thread(futureTask);
        thread.start();
        // 获取返回值，同步等待
        Object obj = futureTask.get();
        System.out.println(obj);

        futureTask = new FutureTask<>(new ChildThread("thread2 - "));
        thread = new Thread(futureTask);
        thread.start();
        // 获取返回值，同步等待
        obj = futureTask.get();
        System.out.println(obj);

        // 打印主线程执行的结果
        System.out.println(MessageFormat.format("{0} 执行完成！", Thread.currentThread().getName()));
    }

    /**
     * 子线程
     */
    static class ChildThread implements Callable<String> {

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
        public String call() {
            // 返回值
            return MessageFormat.format("{0}{1}", this.threadName, LocalDateTime.now());
        }
    }
}
