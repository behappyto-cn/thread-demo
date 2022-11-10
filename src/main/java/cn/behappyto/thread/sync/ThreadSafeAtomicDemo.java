package cn.behappyto.thread.sync;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadSafeAtomicDemo
 *
 * @author behappyto.cn
 * @date 2022/11/10 21:28
 */
@SpringBootTest
public class ThreadSafeAtomicDemo {

    /**
     * 线程数
     */
    private static final int THREADS = 100;

    /**
     * 用来验证是否执行完成
     */
    private final CountDownLatch countDownLatch = new CountDownLatch(THREADS);

    /**
     * 计数
     */
    private AtomicInteger num = new AtomicInteger(0);

    /**
     * 线程不安全
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < THREADS; i++) {
            executorService.submit(() -> {
                try {
                    // 延时, 增加覆盖的概率
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num.incrementAndGet();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        // --- 执行完成 ---
        System.out.println("num = " + num.get());
    }
}
