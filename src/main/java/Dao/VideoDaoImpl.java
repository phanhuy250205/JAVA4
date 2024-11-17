package Dao;

import entity.Share;
import entity.Videos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.List;

public class VideoDaoImpl implements  Videosdao{
    private EntityManager em = XJPA.getEntityManager();
    @Override
    public List<Videos> findAll() {
        return  em.createQuery("select  v From Videos  v", Videos.class).getResultList();
    }

    @Override
    public Videos findById(String id) {
       return em.find(Videos.class, id);
    }

    @Override
    public void create(Videos item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    @Override
    public void update(Videos item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(item);
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Videos video = findById(String.valueOf(id));
        if (video != null) {
            em.remove(video);
        }
        transaction.commit();
    }
        public  List<Videos> findVideosByTitleKeyword(String keyword) {
        String jpql  = "from Videos where title like :keyword";
            TypedQuery<Videos> query = em.createQuery(jpql, Videos.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        }

    public List<Videos> searchByTitle(String keyword) {
        String jpql = "SELECT v FROM Videos v WHERE v.title LIKE :keyword";  // Tìm kiếm video có title chứa từ khóa
        TypedQuery<Videos> query = em.createQuery(jpql, Videos.class);
        query.setParameter("keyword", "%" + keyword + "%");  // Thêm dấu % để tìm kiếm chứa từ khóa
        return query.getResultList();
    }


    public List<Object[]> getShareSummary() {
        String jpql = "SELECT s.video.title, COUNT(s), MIN(s.shareDate), MAX(s.shareDate) " +
                "FROM Share s GROUP BY s.video.title";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }

    public static void main(String[] args) {
        VideoDaoImpl videoDao = new VideoDaoImpl();

        // Từ khóa tìm kiếm (có thể thay đổi từ khóa để kiểm tra)
        String keyword = "Âm Nhạc";  // Ví dụ từ khóa tìm kiếm

        // Gọi phương thức tìm kiếm video theo từ khóa
        List<Videos> result = videoDao.searchByTitle(keyword);

        // In kết quả tìm kiếm
        if (result.isEmpty()) {
            System.out.println("Không có video nào với từ khóa: " + keyword);
        } else {
            System.out.println("Kết quả tìm kiếm cho từ khóa: " + keyword);
            for (Videos video : result) {
                System.out.println("Video: " + video.getTitle() + " | ID: " + video.getId() + " | Mô tả: " + video.getDescription());
            }
        }
    }
}

