package data;
import java.util.List;

public interface IUserRepository {
    void save(User user);
    List<User> findAll();
    void update(User user);
    void deactivate(int id);
    User findById(int id);
}