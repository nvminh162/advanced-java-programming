package fit;

public class ParkingGarage {
    public int capacity;
    public int availableSpace;

    public ParkingGarage(int capacity){
        this.capacity = capacity;
        this.availableSpace = capacity;
    }

    public synchronized void enter() throws Exception{
        while (availableSpace == 0){
            System.out.println("@== Garage is being full! Please wait");
            wait();
        }

        if(availableSpace > 0){
            System.out.println("==> Car parked successfully!");
            availableSpace--;
            notifyAll();
        }
    }

    public synchronized void leave() throws Exception{
        while (availableSpace == capacity){
            System.out.println("o== The garage is empty. Please wait!");
            wait();
        }
        if (availableSpace < capacity){
            System.out.println("<== Car un-parked successfully!");
            availableSpace++;
            notifyAll();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpace() {
        return availableSpace;
    }
}
