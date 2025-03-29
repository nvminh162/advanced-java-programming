package LambdaExpressionNStreamAPI;

import java.util.List;

import static LambdaExpressionNStreamAPI.LambdaExpressionNStreamAPIAfter.*;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Car(30000),
                new Car(60000),
                new Car(30000),
                new Car(90000),
                new Car(45000)
        );

        System.out.println("*** Cars under 50,000 km: " + findCarsUsingFilter(cars).size());
        System.out.println("*** Car kilometers: " + getKilometerList(cars));
        System.out.println("*** Print car kilometers: "); printCarKilometers(cars);
        System.out.println("*** Sorted cars: " + sortCarsByKilometers(cars));
        System.out.println("*** Max km car: " + findCarWithMaxKilometers(cars).kilometers);
        System.out.println("*** Min km car: " + findCarWithMinKilometers(cars).kilometers);
        System.out.println("*** Cars under 50,000 km: " + countCarsUnder50000Km(cars));
        System.out.println("*** Total kilometers: " + getTotalKilometers(cars));

        System.out.println("*** Kiểm tra có ít nhất một xe dưới 50,000 km: "+ hasAnyCarUnder50000Km(cars));
        System.out.println("*** Kiểm tra tất cả xe đều dưới 50,000 km: "+ areAllCarsUnder50000Km(cars));
        System.out.println("*** Kiểm tra không có xe nào dưới 50,000 km: "+ areNoCarsUnder50000Km(cars));
    }
}

