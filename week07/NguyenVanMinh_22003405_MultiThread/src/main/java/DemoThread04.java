public class DemoThread04 {
    public static void main(String[] args) throws Exception {
        Runnable task = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Task 1 - " + i);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(task); //NEW

        System.out.println("Thread state: " + thread.getState()); /*-> NEW*/

        thread.start();

        System.out.println("Thread state: " + thread.getState()); /*-> RUNNABLE*/

//        thread.join();
        while(thread.isAlive()) {}

        System.out.println("Thread state: " + thread.getState()); /*-> TERMINATED*/
    }
}
