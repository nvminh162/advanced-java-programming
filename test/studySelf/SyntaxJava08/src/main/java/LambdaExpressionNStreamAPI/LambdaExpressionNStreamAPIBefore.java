package LambdaExpressionNStreamAPI;

import java.util.ArrayList;
import java.util.List;

public class LambdaExpressionNStreamAPIBefore {
    public List<Car> findCarsOldWay(List<Car> cars) {
        List<Car> selectedCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.kilometers < 50000) selectedCars.add(car);
        }
        return selectedCars;
    }
}
