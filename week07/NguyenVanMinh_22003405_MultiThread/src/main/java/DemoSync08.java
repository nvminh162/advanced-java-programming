import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoSync08 {
    private static List<Integer> list = new Vector<>(); //Sharing object
//    private static List<Integer> list = new ArrayList<>(); //Sharing object

    public static void main(String[] args) {
        Random rd = new Random();

        Runnable task = () -> {
            int n = rd.nextInt(100);
            list.add(n);
        };

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            es.execute(task);
        }

        for (int i = 0; i < 1000; i++) {
            es.execute(task);
        }

        es.shutdown();

        while (!es.isTerminated()) {}

        System.out.println("List size: " + list.size());
    }
}
