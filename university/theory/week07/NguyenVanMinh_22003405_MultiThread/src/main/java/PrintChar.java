public class PrintChar {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintCharTask('a', 100)); // Default thread - 0, state = NEW
        thread1.setName("Thread 1");
        thread1.start(); // state = RUNNABLE
    }
}

class PrintCharTask implements Runnable {
    private char ch;
    private int count;

    public PrintCharTask(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            //#1
            System.out.print(ch);

            //#2
            /*try {
                Thread.sleep(1000);
                System.out.print(ch);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            //#3
        }
    }
}
