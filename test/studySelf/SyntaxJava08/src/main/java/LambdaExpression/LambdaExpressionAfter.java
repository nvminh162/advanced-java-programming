package LambdaExpression;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExpressionAfter {
    /*Tìm xe có số km nhỏ hơn 50,000.*/
    public static List<Car> findCarsUsingFilter(List<Car> cars) {
        return cars
                .stream()                                           /* Dùng stream() để xử lý danh sách một cách hiệu quả*/
                .filter(car -> car.kilometers < 50000) /* Lọc dữ liệu với filter(), thay vì duyệt từng phần tử*/
                .collect(Collectors.toList());              /* Thu thập kết quả thành danh sách với collect()*/
    }

    /*Lấy danh sách số km của tất cả xe.*/
    public static List<Integer> getKilometerList(List<Car> cars) {
        return cars
                .stream()                               /*Dùng stream() để duyệt danh sách xe*/
                .map(car -> car.kilometers) /*Chuyển đổi đối tượng Car thành giá trị kilometers*/
                .collect(Collectors.toList());  /*Thu thập kết quả thành danh sách số nguyên*/
    }

    /*In ra số km của từng xe.*/
    public static void printCarKilometers(List<Car> cars) {
        cars.forEach(car -> System.out.println("    + " + car.kilometers));
    }

    /*Sắp xếp danh sách xe theo số km tăng dần.*/
    public static List<Car> sortCarsByKilometers(List<Car> cars) {
        return cars
                .stream()                                                                      /* Dùng stream() để xử lý danh sách xe. */
                .sorted(Comparator.comparingInt(Car::getKilometers))     /* Sắp xếp tăng dần theo kilometers. */
                .collect(Collectors.toList());                                         /* Thu thập kết quả thành danh sách xe đã sắp xếp. */
    }

    /*Tìm xe có số km cao nhất.*/
    public static Car findCarWithMaxKilometers(List<Car> cars) {
        return cars
                .stream()                                                                      /* Dùng stream() để duyệt danh sách xe. */
                .max(Comparator.comparingInt(car -> car.kilometers)) /* Tìm xe có số km lớn nhất. */
                .orElse(null);                                                            /* Trả về null nếu danh sách rỗng. */
    }

    /*Tìm xe có số km thấp nhất.*/
    public static Car findCarWithMinKilometers(List<Car> cars) {
        return cars
                .stream()                                                                         /* Dùng stream() để duyệt danh sách xe. */
                .min(Comparator.comparingInt(car -> car.kilometers))    /* Tìm xe có số km nhỏ nhất. */
                .orElse(null);                                                               /* Trả về null nếu danh sách rỗng. */
    }

    /*Đếm số lượng xe thỏa mãn điều kiện*/
    public static long countCarsUnder50000Km(List<Car> cars) {
        return cars
                .stream()                                             /* Dùng stream() để duyệt danh sách xe. */
                .filter(car -> car.kilometers < 50000)  /* Lọc các xe có số km nhỏ hơn 50,000. */
                .count();                                            /* Đếm số lượng xe thỏa mãn điều kiện. */
    }

    /*anyMatch(), allMatch(), noneMatch() - Kiểm tra điều kiện*/
    /*Kiểm tra có ít nhất một xe dưới 50,000 km.*/
    public static boolean hasAnyCarUnder50000Km(List<Car> cars) {
        return cars
                .stream()                                               /* Dùng stream() để duyệt danh sách xe. */
                .anyMatch(car -> car.kilometers < 50000);  /* Kiểm tra nếu có ít nhất 1 xe dưới 50,000 km. */
    }
    /*Kiểm tra tất cả xe đều dưới 50,000 km.*/
    public static boolean areAllCarsUnder50000Km(List<Car> cars) {
        return cars
                .stream()                                              /* Dùng stream() để duyệt danh sách xe. */
                .allMatch(car -> car.kilometers < 50000); /* Kiểm tra nếu tất cả xe đều dưới 50,000 km. */
    }
    /*Kiểm tra không có xe nào dưới 50,000 km.*/
    public static boolean areNoCarsUnder50000Km(List<Car> cars) {
        return cars
                .stream()                                               /* Dùng stream() để duyệt danh sách xe. */
                .noneMatch(car -> car.kilometers < 50000); /* Kiểm tra nếu không có xe nào dưới 50,000 km. */
    }

    /*Tính toán tổng số km*/
    public static int getTotalKilometers(List<Car> cars) {
        return cars
                .stream()                                     /* Dùng stream() để duyệt danh sách xe. */
                .map(car -> car.kilometers)       /* Chuyển đổi danh sách xe thành danh sách số km. */
                .reduce(0, Integer::sum);   /* Cộng dồn tổng số km của tất cả xe. */
    }
}
