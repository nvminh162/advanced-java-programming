import java.util.stream.IntStream;

public class DemoThread03 {
    public static void main(String[] args) {
        //Cach 1: Bieu thuc lambda
        Runnable task = () -> {
            int total = IntStream.range(1, 10000).sum();
            System.out.println("Total 1: " + total);
        };
        Thread thread = new Thread(task);
        thread.start();

        //Cach 2
        Thread thread2 = new Thread( new MyTask());
        thread2.start();

        //Cach 3
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                int total = IntStream.range(1, 1000000).sum();
                System.out.println("Total 3: " + total);
            }
        });
        thread3.start();
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        int total = IntStream.range(1, 1000000).sum();
        System.out.println("Total 2: " + total);
    }
}
