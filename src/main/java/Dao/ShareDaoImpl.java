package Dao;

import Dao.ShareDao;
import entity.Share;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.List;

public class ShareDaoImpl implements ShareDao {
    private EntityManager em = XJPA.getEntityManager();

    @Override
    public List<Share> findAll() {
        return em.createQuery("SELECT s FROM Share s", Share.class).getResultList();
    }

    @Override
    public Share findById(int id) {
        return em.find(Share.class, id);
    }

    @Override
    public void create(Share share) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(share);
        transaction.commit();
    }

    @Override
    public void update(Share share) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(share);
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Share share = findById(id);
        if (share != null) {
            em.remove(share);
        }
        transaction.commit();
    }

    public static void main(String[] args) {
        VideoDaoImpl dao = new VideoDaoImpl();
        dao.deleteById(1222);
    }
    public List<Share> findSharesByVideoId(String videoId) {
        String jpql = "SELECT s FROM Share s WHERE s.video.id = :videoId";
        TypedQuery<Share> query = em.createQuery(jpql, Share.class);
        query.setParameter("videoId", videoId);
        return query.getResultList();
    }
    public List<Share> findSharedVideosIn2024() {
        // Câu truy vấn JPQL để lấy các video được chia sẻ trong năm 2024 và sắp xếp theo thời gian
        String jpql = "SELECT s FROM Share s WHERE YEAR(s.shareDate) = 2024 ORDER BY s.shareDate ASC";

        // Tạo truy vấn với kết quả là danh sách các đối tượng Share
        TypedQuery<Share> query = em.createQuery(jpql, Share.class);

        // Trả về danh sách các Share theo thời gian
        return query.getResultList();
    }
    public  List<Object[]> getshareSummmary(){
        String jpql = "SELECT s.video.title, COUNT(s), MIN(s.shareDate), MAX(s.shareDate) " +
                "FROM Share s GROUP BY s.video.title";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }
}
