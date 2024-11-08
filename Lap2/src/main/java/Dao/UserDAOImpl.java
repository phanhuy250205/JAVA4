package Dao;

import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import until.XJPA;


import java.util.List;

public class UserDAOImpl implements  UserDao{

    private EntityManager em  = until.XJPA.getEntityManager();
    @Override
    public List<User> findAll() {
        String jpql = "select u from User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void create(User entity) {
            try {

                em.getTransaction().begin();
                em.persist(entity);
                em.getTransaction().commit();

            }catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
    }

    @Override
    public void update(User item) {
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();

        }catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        User entity = em.find(User.class, id);
        if (entity != null) {
            try {
                em.getTransaction().begin();
                em.remove(entity);
                em.getTransaction().commit();
                System.out.println("User deleted with ID: " + id);
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }

    public static void main(String[] args) {
        UserDAOImpl dao = new UserDAOImpl();

        // Tạo một người dùng mới
        User newUser = new User();
        newUser.setFullname("John Doe");
        newUser.setEmail("johndoe@example.com");
        newUser.setPassword("12345");
        newUser.setAdmin(true);
        dao.create(newUser);

        // Tìm tất cả người dùng
        List<User> users = dao.findAll();
        System.out.println("Tất cả người dùng: " + users);

        // Tìm người dùng theo ID
        User foundUser = dao.findById(1);
        System.out.println("Người dùng tìm thấy: " + foundUser);

        // Cập nhật người dùng
        if (foundUser != null) {
            foundUser.setFullname("John Doe Updated");
            dao.update(foundUser);
        }

        // Xóa người dùng theo ID
        dao.deleteById(1);

        // Xác nhận xóa bằng cách thử tìm người dùng lại
        User deletedUser = dao.findById(1);
        if (deletedUser == null) {
            System.out.println("Đã xác nhận xóa. Không tìm thấy người dùng với ID: 1");
        } else {
            System.out.println("Lỗi: Người dùng vẫn tồn tại với ID: 1");
        }

        // Đóng EntityManager sau khi hoàn tất tất cả thao tác
        dao.em.close();
    }
}
