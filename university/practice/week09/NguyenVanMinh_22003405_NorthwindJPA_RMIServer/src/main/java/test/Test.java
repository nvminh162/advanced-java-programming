package test;

import jakarta.persistence.EntityManager;
import util.JPAUtil;

public class Test {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
    }
}
