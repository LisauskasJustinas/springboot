package com.klientai.model;



import com.klientai.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class KlientaiDAOImpl implements KlientaiDAO {
    public void insertEntity(Klientai klientai){
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(klientai);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Klientai finEntityByID(int id) {
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        List <Klientai> klientai=entityManager.
                createQuery("SELECT c FROM Klientai c WHERE c.id=:id ")
                .setParameter("id",id)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return klientai.get(0);
    }
    public List<Klientai>findEntities(){
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        List<Klientai>klientai=entityManager.
                createQuery("SELECT c FROM Klientai c ")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return klientai;
    }
    public void updateEntity(Klientai klientai){
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        Klientai klientai2 =entityManager.find(Klientai.class, klientai.getId());
        klientai2.setName(klientai.getName());
        klientai2.setSurname(klientai.getSurname());
        klientai2.setPriority(klientai.getPriority());
        klientai2.setCommentar(klientai.getCommentar());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void removeEntityByID(int id){
        EntityManager entityManager= JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();

        Klientai klientai =entityManager.find(Klientai.class,id);
        entityManager.remove(klientai);

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public List<Klientai> search(String name ) {
        if (name.equals("")) {
            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            List<Klientai> klientai = entityManager.
                    createQuery("SELECT c FROM Klientai c ")
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();
            return klientai;
        } else {
            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            List<Klientai> klientai = entityManager.
                    createQuery("SELECT c FROM Klientai  c WHERE c.name LIKE :name")

                    .setParameter("name", name)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return klientai;
        }
    }
    }


