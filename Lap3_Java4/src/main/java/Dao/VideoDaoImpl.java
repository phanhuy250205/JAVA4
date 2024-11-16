package Dao;

import entity.Videos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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


    }

