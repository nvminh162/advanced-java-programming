public class DemoThread05 {
    public static void main(String[] args) {
        Runnable task = () -> {
           System.out.println("Hello, world! " + Thread.currentThread().getName());

        };

        //Main thread
        System.out.println("PRIORITY: " + Thread.currentThread().getPriority());
        System.out.println("MIN_PRIORITY: " + Thread.MIN_PRIORITY);
        System.out.println("NORM_PRIORITY: " + Thread.NORM_PRIORITY);
        System.out.println("MAX_PRIORITY: " + Thread.MAX_PRIORITY);

        Thread thread01 = new Thread(task, "Thread 01"); /*-> 5*/
        System.out.println("PRIORITY THREAD 01: " + thread01.getPriority());

        Thread.currentThread().setPriority(7);
        Thread thread02 = new Thread(task, "Thread 02"); /*-> 7*/
        System.out.println("PRIORITY THREAD 02: " + thread02.getPriority());
    }
}
