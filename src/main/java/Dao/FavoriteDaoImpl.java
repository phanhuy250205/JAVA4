package Dao;

import entity.Favorites;
import entity.User;
import entity.Videos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.List;

public class FavoriteDaoImpl implements  FavoritesDao{
    private EntityManager em = XJPA.getEntityManager();
    @Override
    public List<Favorites> findAll() {
        return em.createQuery("SELECT f FROM Favorites f", Favorites.class).getResultList();
    }

    @Override
    public Favorites findById(int id) {
        return em.find(Favorites.class, id);
    }

    @Override
    public void create(Favorites item) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            // Lấy User và Video từ cơ sở dữ liệu trước khi tạo đối tượng Favorites
            User user = item.getUser();  // Đảm bảo user đã được thiết lập
            Videos video = item.getVideo();  // Đảm bảo video đã được thiết lập

            // In ra thông tin User và Video để kiểm tra
            System.out.println("User Name: " + user.getFullname());  // Lấy tên người dùng
            System.out.println("Video Title: " + video.getTitle());  // Lấy tên video

            em.persist(item);  // Lưu đối tượng favorite vào cơ sở dữ liệu
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Error adding favorite", e);
        }
    }



    @Override
    public void update(Favorites item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(item);
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Favorites favorite = findById(id);
        if (favorite != null) {
            em.remove(favorite);
        }
        transaction.commit();
    }
    public List<Favorites> findFavoritesByUser(User user) {
        String jpql = "SELECT f FROM Favorites f WHERE f.user = :user";
        TypedQuery<Favorites> query = em.createQuery(jpql, Favorites.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
    }

