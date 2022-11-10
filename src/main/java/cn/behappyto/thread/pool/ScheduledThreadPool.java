package cn.behappyto.thread.pool;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

/**
 * aa
 *
 * @author behappyto.cn
 * @date 2022/11/09 22:16
 */
@SpringBootTest
public class ScheduledThreadPool {

    /**
     * 线程数
     */
    private static final int THREADS = 10;

    /**
     * 用于计数线程是否执行完成
     */
    CountDownLatch countDownLatch = new CountDownLatch(THREADS);

    /**
     * newScheduledThreadPool execute
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        System.out.println("---- start ----");
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            scheduledThreadPool.execute(() -> {
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
     * newScheduledThreadPool submit submit
     */
    @Test
    public void test2() throws InterruptedException {
        System.out.println("---- start ----");
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            // Callable 带返回值
            scheduledThreadPool.submit(new Thread(() -> {
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
     * newScheduledThreadPool submit Callable
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        System.out.println("---- start ----");
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            //  Runnable 带返回值
            FutureTask<?> futureTask = new FutureTask<>(() -> Thread.currentThread().getName());
            scheduledThreadPool.submit(new Thread(futureTask));
            System.out.println(futureTask.get());
        }
        System.out.println("---- end ----");
    }

    /**
     * newScheduledThreadPool scheduleAtFixedRate Callable
     *
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        System.out.println("---- start ----");
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            scheduledThreadPool.scheduleAtFixedRate(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // countDownLatch.countDown();
                }
            }, 0, 3, TimeUnit.SECONDS);
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * newScheduledThreadPool submit Callable
     *
     * @throws InterruptedException
     */
    @Test
    public void test5() throws InterruptedException {
        System.out.println("---- start ----");
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(THREADS);
        for (int i = 0; i < THREADS; i++) {
            scheduledThreadPool.scheduleWithFixedDelay(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // countDownLatch.countDown();
                }
            }, 0, 3, TimeUnit.SECONDS);
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }
}
