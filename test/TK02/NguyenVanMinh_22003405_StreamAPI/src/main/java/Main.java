import util.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Giả sử ta cần lấy diễn viên của phim có movie_id = "11"
        String movieID = "11";

        // Gọi phương thức listCast để lấy danh sách cast
        List<String> castList = Util.listCast(movieID);

        // In tạm ra console
        System.out.println("Danh sách diễn viên của phim có movie_id = " + movieID + ":");
        castList.forEach(System.out::println);

        String outputFileName = "export/NguyenVanA_123456.txt";

        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write("Danh sách diễn viên của phim movie_id = " + movieID + ":\n");
            for (String actor : castList) {
                writer.write(actor + "\n");
            }
            System.out.println("Đã ghi kết quả vào file: " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
