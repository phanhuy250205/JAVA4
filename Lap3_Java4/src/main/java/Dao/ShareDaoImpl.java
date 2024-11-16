package Dao;

import Dao.ShareDao;
import entity.Share;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
}
