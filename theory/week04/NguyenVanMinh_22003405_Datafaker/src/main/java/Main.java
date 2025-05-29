import jakarta.json.JsonArray;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.UserUtil;

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

        UserUtil userUtil = new UserUtil(em);
        JsonArray ja = userUtil.usersToJson();
        System.out.println(ja);

        userUtil.write2File(ja, "json/users.json");
    }
}
