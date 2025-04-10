package fit;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;

public class SumRangeTask2 implements Callable<Integer> {
    private int from;
    private int to;

    public SumRangeTask2(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        int total = 0;
        for (int i = from; i < to ; i++) {
               if(Thread.currentThread().isInterrupted()) {
                   Thread.interrupted();
                   return 0;
               }
//               Thread.sleep(1000);
               total += i;
        }
        return total;
//        return IntStream.range(from, to).sum();
    }
}
