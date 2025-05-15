package test;

import dao.GroupDAO;
import dao.UserDAO;
import dao.impl.GroupDAOImpl;
import dao.impl.UserDAOImpl;
import model.Group;
import model.User;
import util.JPAUtil;

import java.util.LinkedHashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
//        System.out.println("Hello, world!");

//        JPAUtil.getEntityManager();
//        System.out.println("Create entity successfully!");

        /*TestDAO*/
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("admin@admin.com");

        Group group = new Group();
        group.setName("Group 1");
        group.addUser(user);

        UserDAO userDAO = new UserDAOImpl(User.class);
        userDAO.save(user);
        GroupDAO groupDAO = new GroupDAOImpl(Group.class);
        groupDAO.save(group);
    }
}
