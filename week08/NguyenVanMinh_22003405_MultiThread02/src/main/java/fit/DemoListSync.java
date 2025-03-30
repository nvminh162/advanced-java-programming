package fit;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoListSync {
    private static ArrayList<Integer> list = new ArrayList<>();
//    private static Vector<Integer> list = new Vector<>();

    public static void main(String[] args) {
        Random rd = new Random();
        Runnable task = () -> {
            for (int i = 0; i < 1000 ; i++) {
                synchronized (list) {
                    list.add(rd.nextInt(1000));
                }
            }
        };

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(task);
        pool.submit(task);
        pool.submit(task);

        pool.shutdown();

        while (!pool.isTerminated()){}

        System.out.println("Size: " + list.size());
    }
}
