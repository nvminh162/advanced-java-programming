package fit;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class DemoCallable {
    public static void main(String[] args) throws Exception{
        Callable<Integer> task1 = new SumRangeTask2(0, 1000);
        Callable<Integer> task2 = new SumRangeTask2(1000, 2000);
        Callable<Integer> task3 = new SumRangeTask2(2000, 3000);

        FutureTask<Integer> f1 = new FutureTask<>(task1);
        FutureTask<Integer> f2 = new FutureTask<>(task2);
        FutureTask<Integer> f3 = new FutureTask<>(task3);

        Thread thread1 = new Thread(f1);
        Thread thread2 = new Thread(f2);
        Thread thread3 = new Thread(f3);

        thread1.start();
        thread2.start();
        thread3.start();


        int total = f1.get(1000, TimeUnit.MICROSECONDS);
        System.out.println(total);

//        int total = f1.get() + f2.get() + f3.get();
//        System.out.println("Total: " + total);

    }
}
