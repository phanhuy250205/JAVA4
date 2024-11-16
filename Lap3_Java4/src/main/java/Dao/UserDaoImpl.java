package Dao;

import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.List;
public class UserDaoImpl implements  UserDao {

    private EntityManager em = XJPA.getEntityManager();
    @Override
    public List<User> findAll() {
        return em.createQuery("select  u from  User u", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
       return  em.find(User.class, id);
    }

    @Override
    public void create(User item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    @Override
    public void update(User item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(item);
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        User user = findById(id);
        if (user != null) {
            em.remove(user);
        }
         transaction.commit();
    }

    public User findByUsernameAndPassword(String username, String password) {
        // Tạo truy vấn JPQL để tìm kiếm user dựa trên username (email) và password
        String jpql = "SELECT u FROM User u WHERE u.email = :username AND u.password = :password";

        // Tạo TypedQuery và truyền tham số vào
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            // Thực hiện truy vấn và trả về kết quả
            return query.getSingleResult();  // Nếu không tìm thấy, sẽ ném ra NoResultException
        } catch (NoResultException e) {
            // Nếu không tìm thấy người dùng, trả về null
            return null;
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác nếu cần thiết
            e.printStackTrace();  // Có thể ghi log hoặc xử lý ngoại lệ cụ thể ở đây
            return null;
        }
    }

}
