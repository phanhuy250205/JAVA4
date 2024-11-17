package Dao;

import entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(int id);
    void create (User item);

    void update (User item);
    void deleteById(int id);

    User findByUsernameAndPassword(String username, String password);

}
