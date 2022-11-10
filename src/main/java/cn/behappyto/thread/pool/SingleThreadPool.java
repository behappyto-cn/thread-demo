package cn.behappyto.thread.pool;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * aa
 *
 * @author behappyto.cn
 * @date 2022/11/09 22:12
 */
@SpringBootTest
public class SingleThreadPool {

    /**
     * 线程数
     */
    private static final int THREADS = 100;

    /**
     * countDownLatch
     */
    private final CountDownLatch countDownLatch = new CountDownLatch(THREADS);

    /**
     * singleThreadPool execute
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        System.out.println("---- begin ----");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < THREADS; i++) {
            singleThreadExecutor.execute(() -> {
                this.printThreadInfo();
            });
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * singleThreadPool submit
     *
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        System.out.println("---- begin ----");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < THREADS; i++) {
            singleThreadExecutor.submit(new Thread(() -> {
                this.printThreadInfo();
            }));
        }
        countDownLatch.await();
        System.out.println("---- end ----");
    }

    /**
     * 打印线程信息
     */
    private void printThreadInfo() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(50);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            countDownLatch.countDown();
        }
    }
}
