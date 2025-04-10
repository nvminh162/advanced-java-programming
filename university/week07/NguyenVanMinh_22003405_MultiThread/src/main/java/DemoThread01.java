/*
* + start(): void => Khi gọi thread.start(), Java tạo một luồng mới (new thread) và thực thi phương thức run() trong luồng đó.
* Đây là cách đúng để bắt đầu một luồng mới.
* Luồng chính (main thread) tiếp tục chạy song song với luồng mới.
*
* + run(): void => Khi gọi thread.run(), phương thức run() chỉ chạy như một hàm bình thường trong luồng hiện tại (luồng main).
* Không tạo luồng mới, mọi thứ vẫn chạy trong luồng chính.
*
* + isAlive(): boolean => Phương thức này kiểm tra xem luồng có đang chạy hay không.
*
* + setPriority(int priority): void => Phương thức này thiết lập độ ưu tiên cho luồng.
*   Độ ưu tiên là một số nguyên từ 1 đến 10, với 1 là độ ưu tiên thấp nhất và 10 là độ ưu tiên cao nhất.
*   Lập lịch dựa 2 cơ che: - dựa vào độ ưu tiên các thread.
*                          - round robin.
*   by default, độ ưu tiên của thread là 5.
*   Ví dụ: set thành độ ưu tiên 7: những thread con tạo ra từ main sẽ có độ ưu tiên 7.
*
* + join(): void => Phương thức này cho phép một luồng chờ cho một luồng khác hoàn thành trước khi tiếp tục. => Nhuờng cho đến khi join() hoàn thành.
* + yield(): void => Phương thức này cho phép luồng hiện tại tạm dừng và cho phép các luồng khác có cơ hội chạy. => Nhương trong khoảng thời gian nhất định sau khi hết thì yield() phải trả lại sau khi nhường.
*
* + interrupt(): void => Phương thức này được sử dụng để ngắt một luồng đang chạy. Khi một luồng bị ngắt,
*   nó sẽ ném ra một ngoại lệ InterruptedException nếu nó đang trong trạng thái chờ hoặc ngủ.
*
* + sleep(long millis): void => Phương thức này tạm dừng luồng hiện tại trong một khoảng thời gian nhất định (millis).
* */

public class DemoThread01 {
    public static void main(String[] args) {
        System.out.println("Main: " + Thread.currentThread().getName());
        SumTask task1 = new SumTask(0, 100);
        Thread thread = new Thread(task1, "Thread 01"); /*-> NEW*/
        thread.start(); /*-> run: Thread 01*/
//        thread.run(); /*-> run: main*/
    }
}

class SumTask implements Runnable {
    private int from, to;

    public SumTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        int total = 0;
        for (int i = from; i <= to; i++) {
            total += i;
        }
        System.out.println("run: " + Thread.currentThread().getName());
        System.out.println("Sum from " + from + " to " + to + " is: " + total);
    }
}

