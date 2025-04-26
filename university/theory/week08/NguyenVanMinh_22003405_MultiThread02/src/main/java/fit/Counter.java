package fit;

public class Counter {
    private int count = 0;
    public synchronized void increase(){
        count++;
    }

    public int getCount() {
        return count;
    }

}
