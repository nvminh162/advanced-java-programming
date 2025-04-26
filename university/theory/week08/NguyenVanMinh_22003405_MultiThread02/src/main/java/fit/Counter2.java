package fit;

import java.util.concurrent.locks.ReentrantLock;

public class Counter2 {
    private static final ReentrantLock LOCK =  new ReentrantLock();
    private int count = 0;
    public void increase(){
        LOCK.lock();
        try{
            count++;
        }finally {
            LOCK.unlock();
        }

    }

    public int getCount() {
        return count;
    }

}
