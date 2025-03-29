import java.util.stream.IntStream;

public class DemoThread02 extends Thread{
    @Override
    public void run() {
        int total = IntStream.range(1, 1000).sum();
        System.out.println(total);
    }

    public static void main(String[] args) {
        Thread thread = new DemoThread02();
        thread.start();
    }
}
