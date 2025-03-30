package fit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoParkingGarage {
    private static final int CAPACITY = 5;
    private static ParkingGarage parkingGarage = new ParkingGarage(CAPACITY);
    public static void main(String[] args) {
        Runnable enterTask = () -> {
            try {
                parkingGarage.enter();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        Runnable leaveTask = () -> {
            try {
                Thread.sleep(4000);
                parkingGarage.leave();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

//        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool = Executors.newFixedThreadPool(6);

        for (int i = 0; i < CAPACITY; i++) {
            pool.submit(enterTask);
        }
        for (int i = 0; i < CAPACITY; i++) {
            pool.submit(leaveTask);
        }
        pool.submit(enterTask);
        pool.submit(enterTask);
        pool.submit(enterTask);
        pool.submit(enterTask);
        pool.shutdown();

    }

}
