package util;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> listCast(String movieID) {
        List<String> result = new ArrayList<>();
        String jsonFilePath = "json/sample.json"; // Đường dẫn tới file JSON của bạn

        try (JsonParser parser = Json.createParser(new FileReader(jsonFilePath))) {
            // File sample.json có dạng mảng JSON (dấu [ ]), bên trong là các object
            // Ta sẽ duyệt qua các event để tìm object có movie_id == movieID.

            boolean foundMovie = false;      // Đánh dấu khi đã gặp đúng movie
            boolean inCastArray = false;     // Đánh dấu đang ở trong mảng "cast"

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();

                switch (event) {
                    case KEY_NAME:
                        // Lấy tên key hiện tại
                        String keyName = parser.getString();

                        // Nếu keyName == "movie_id", ta kiểm tra giá trị tiếp theo
                        if ("movie_id".equals(keyName)) {
                            // Di chuyển parser sang event VALUE_NUMBER hoặc VALUE_STRING
                            parser.next();
                            // So sánh với movieID (có thể cần parse sang int nếu movieID là int)
                            String actualId = parser.getString();
                            foundMovie = movieID.equals(actualId);
                            // Nếu khớp, ta đánh dấu đã tìm đúng movie
                        }

                        // Nếu keyName == "cast" và ta đã foundMovie == true,
                        // thì sự kiện tiếp theo có thể là START_ARRAY => ta sẽ duyệt mảng cast
                        if ("cast".equals(keyName) && foundMovie) {
                            // Di chuyển sang START_ARRAY
                            parser.next(); // Bắt đầu mảng cast
                            inCastArray = true;
                        }
                        break;

                    case VALUE_STRING:
                        // Nếu đang ở trong mảng cast thì ta lấy giá trị string (tên diễn viên)
                        if (inCastArray) {
                            result.add(parser.getString());
                        }
                        break;

                    case END_ARRAY:
                        // Nếu kết thúc một mảng và ta đang ở mảng cast -> thoát
                        if (inCastArray) {
                            inCastArray = false;
                            // Sau khi lấy xong cast, ta có thể break toàn bộ nếu muốn,
                            // vì bài chỉ cần lấy cast của 1 movie (giả sử unique).
                            // Nếu file có nhiều movie, ta dừng ở đây để tối ưu.
                            return result;
                        }
                        break;

                    default:
                        // Các event khác ta không quan tâm
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
