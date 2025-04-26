package fit;

import java.util.concurrent.*;

public class DemoThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService pool = Executors.newFixedThreadPool(5);
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<Integer> fu1 = pool.submit(new SumRangeTask2(0, 1000));
        Future<Integer> fu2 = pool.submit(new SumRangeTask2(1000, 2000));
        Future<Integer> fu3 = pool.submit(new SumRangeTask2(2000, 3000));
        Future<Integer> fu4 = pool.submit(new SumRangeTask2(3000, 4000));

        Thread.sleep(1000);

        int total = fu1.get() + fu2.get() + fu3.get() + fu4.get();

        Future<Integer> fu5 = pool.submit(new SumRangeTask2(3000, 4000));

        total += fu5.get();

        System.out.println("Total: " + total);
        pool.shutdown();

    }
}
