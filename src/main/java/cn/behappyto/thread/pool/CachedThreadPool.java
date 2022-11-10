package cn.behappyto.thread.pool;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

/**
 * CachedThreadPool
 *
 * @author behappyto.cn
 * @date 2022/11/09 21:50
 */
@SpringBootTest
public class CachedThreadPool {

    /**
     * 线程数
     */
    private static final int THREADS = 10;

    /**
     * 用于计数线程是否执行完成
     */
    CountDownLatch countDownLatch = new CountDownLatch(THREADS);

    /**
     * newCachedThreadPool execute
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS; i++) {
            cachedThreadPool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * newCachedThreadPool submit submit
     */
    @Test
    public void test2() throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS; i++) {
            // Callable 带返回值
            cachedThreadPool.submit(new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }));
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * newCachedThreadPool submit Callable
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS; i++) {
            // Runnable 带返回值
            FutureTask<?> futureTask = new FutureTask<>(() -> Thread.currentThread().getName());
            cachedThreadPool.submit(new Thread(futureTask));
            System.out.println(futureTask.get());
        }
        System.out.println("---- end ----");
    }
}
