import java.util.List;
import java.util.concurrent.*;

public class DemoCallable07 {
    public static void main(String[] args) throws Exception {
        // Chưa tách công việc
        /*int total = IntStream.range(0, 3000).sum();
        System.out.println("Total: " + total); // 4498500*/

        //Dùng callable tách công việc

        Callable<Integer> range01 = new SumTask02(0, 100);   //?
        Callable<Integer> range02 = new SumTask02(100, 200); //?
        Callable<Integer> range03 = new SumTask02(200, 300); //?
        Callable<Integer> range04 = new SumTask02(300, 400); //?
        Callable<Integer> range05 = new SumTask02(400, 500); //?

        List<Callable<Integer>> tasks = List.of(range01, range02, range03, range04, range05);
        ExecutorService es = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futures = es.invokeAll(tasks);

        Future<Integer> future = es.submit(new SumTask02(500, 600)); //?

        es.shutdown();

        while (!es.isTerminated()) {}

        int total = futures
                .stream()
                .mapToInt(f -> {
                    try {
                        return f.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).sum();
        total += future.get();
        System.out.println("Total: " + total);
    }
}
