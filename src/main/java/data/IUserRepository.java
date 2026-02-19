package data;
import java.util.List;

public interface IUserRepository {
    void create(User user);
    List<User> read();
    void update(User user);
    void deactivate(int id);
    User findById(int id);
}