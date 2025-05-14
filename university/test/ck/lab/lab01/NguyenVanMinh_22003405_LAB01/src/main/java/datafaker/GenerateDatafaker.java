package datafaker;

import dao.GroupDAO;
import dao.UserDAO;
import dao.impl.GroupDAOImpl;
import dao.impl.UserDAOImpl;
import model.Group;
import model.User;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateDatafaker {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private static final UserDAO userDAO = new UserDAOImpl(User.class);
    private static final GroupDAO groupDAO = new GroupDAOImpl(Group.class);

    public static void main(String[] args) {
        List<User> users = generateUsers(10);
        List<Group> groups = generateGroups(5);
        assignUsersToGroups(users, groups);
    }

    public static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            String lastName = faker.name().lastName();
            user.setUsername(lastName.toLowerCase());
            user.setPassword(faker.internet().password(8, 20));
            user.setEmail(lastName.toLowerCase() + "@" + faker.internet().domainName());
            users.add(user);
            userDAO.save(user);
        }
        return users;
    }

    public static List<Group> generateGroups(int count) {
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Group group = new Group();
            group.setName(faker.company().buzzword());
            groups.add(group);
            groupDAO.save(group);
        }
        return groups;
    }

    public static void assignUsersToGroups(List<User> users, List<Group> groups) {
        for (Group group : groups) {
            int userCount = random.nextInt(5) + 1;
            userCount = Math.min(userCount, users.size());

            for (int i = 0; i < userCount; i++) {
                User randomUser = users.get(random.nextInt(users.size()));
                group.addUser(randomUser);
            }

            groupDAO.update(group);
        }
    }
}
