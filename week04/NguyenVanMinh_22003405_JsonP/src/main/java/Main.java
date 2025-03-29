import entities.Person;
import utils.PersonUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = PersonUtil.fromJson("json/people.json");
        people.forEach(p -> System.out.println(p));
    }
}
