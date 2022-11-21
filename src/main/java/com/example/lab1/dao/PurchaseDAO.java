package com.example.lab1.dao;

import com.example.lab1.model.Purchase;
import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Model;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.util.List;

@Model
public class PurchaseDAO implements Serializable {
    @PersistenceContext(unitName = "default")
    private EntityManager em;
    @Resource
    UserTransaction utx;

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

    public int removeById(Integer id){
        try {
            utx.begin();
            em.remove(findByID(id));
            utx.commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int create(Purchase purchase){
        try {
            utx.begin();
            em.persist(purchase);
            utx.commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int update(Purchase purchase){
        return create(purchase);
    }
}
