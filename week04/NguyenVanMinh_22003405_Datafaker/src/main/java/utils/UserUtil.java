package utils;

import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import model.Group;
import model.User;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class UserUtil {

    private EntityManager em;

    public JsonArray usersToJson() {
        JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
        JsonObjectBuilder joBuilder = Json.createObjectBuilder();

        String query = "from User u"; //Get all User
        String query2 = "select u.groups from User u where u.id = :uid";

        List<User> users = em.createQuery(query, User.class).getResultList();
        users.stream()
                .map(user -> {
                    joBuilder
                            .add("id", user.getId())
                            .add("username", user.getUsername())
                            .add("password", user.getPassword())
                            .add("email", user.getEmail());

                    List<Group> groups = em.createQuery(query2, Group.class)
                            .setParameter("uid", user.getId())
                            .getResultList();

                    joBuilder.add("groups", toJson(groups));

                    return joBuilder.build(); // Sau khi build nó sẽ tra ve từng user trong List (từng JsonObject) -> dùng Foreach để duyệt từng JsonObject
                }).forEach(jo -> jaBuilder.add(jo));
        return jaBuilder.build();
    }

    public JsonArray toJson(List<Group> groups) {
        JsonArrayBuilder jaBuilder = Json.createArrayBuilder();
        JsonObjectBuilder joBuilder = Json.createObjectBuilder();

        groups.stream()
                .map(group -> {
                    return joBuilder
                            .add("group_id", group.getId())
                            .add("name", group.getName())
                            .build();
                }).forEach(jo -> jaBuilder.add(jo));
        return jaBuilder.build();
    }

    public void write2File(JsonArray ja, String fileName) {
        Map<String, Boolean> conf = new HashMap<>();
        conf.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory factory = Json.createWriterFactory(conf);

        try(JsonWriter jw = factory.createWriter(new FileWriter(fileName))) {
            jw.writeArray(ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
