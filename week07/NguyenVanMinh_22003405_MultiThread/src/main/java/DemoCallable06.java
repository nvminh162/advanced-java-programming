import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class DemoCallable06 {
    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = new SumTask02(0, 100);
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        thread.start();

        while (thread.isAlive()) {}

        Integer total = task.get();
        System.out.println("Sum from 0 to 100: " + total);
    }
}

class SumTask02 implements Callable<Integer> {
    private int from, to;

    public SumTask02(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Integer call() throws Exception {
        //Cach 01
        /*int total = 0;
        for (int i = from; i <= to; i++) {
            total += i;
        }
        return total;*/

        //Cach 02
        return IntStream.range(from, to).sum();
    }
}

//1:30:00