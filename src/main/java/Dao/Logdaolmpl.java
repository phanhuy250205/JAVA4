package Dao;

import entity.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import until.XJPA;

public class Logdaolmpl implements  Logsdao {
    private EntityManager em = XJPA.getEntityManager();


    @Override
    @Transactional
    public void create(Log log) {
        em.persist(log);
    }
}
