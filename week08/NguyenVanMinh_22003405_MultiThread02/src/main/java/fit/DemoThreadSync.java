package fit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoThreadSync {

    private static Counter counter = new Counter();

    public static void main(String[] args) {
//        ArrayList --> unthread safe
//        Vector --> thread safe

//        HashMap --> unthread safe
//        Hashtable --> thread safe

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increase();
            }
        };

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(task);
        pool.submit(task);
        pool.submit(task);

        pool.shutdown();

        while(!pool.isTerminated()){}

        System.out.println("Counter: " + counter.getCount());

    }
}
