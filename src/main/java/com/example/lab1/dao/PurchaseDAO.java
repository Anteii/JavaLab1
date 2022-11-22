package com.example.lab1.dao;

import com.example.lab1.model.Purchase;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

@Stateless
public class PurchaseDAO implements Serializable {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public Purchase findByID(Integer id) {
        return em.find(Purchase.class, id);
    }

    public List<Purchase> getAllPurchases(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Purchase> cq = cb.createQuery(Purchase.class);
        Root<Purchase> rootEntry = cq.from(Purchase.class);
        CriteriaQuery<Purchase> all = cq.select(rootEntry);
        TypedQuery<Purchase> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void removeById(Integer id){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(findByID(id));
        transaction.commit();
    }

    public void create(Purchase purchase){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(purchase);
        transaction.commit();
    }

    public void update(Purchase purchase){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(purchase);
        transaction.commit();
    }
}
