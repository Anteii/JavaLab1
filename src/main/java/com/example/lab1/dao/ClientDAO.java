package com.example.lab1.dao;

import com.example.lab1.model.Client;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Model;
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
public class ClientDAO implements Serializable {
    @PersistenceContext(unitName = "default")
    private EntityManager em;


    public Client findByID(Integer id) {
        return em.find(Client.class, id);
    }

    public List<Client> getAllClients(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> rootEntry = cq.from(Client.class);
        CriteriaQuery<Client> all = cq.select(rootEntry);
        TypedQuery<Client> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void removeById(Integer id){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(findByID(id));
        transaction.commit();
    }

    public void create(Client client){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(client);
        transaction.commit();
    }

    public void update(Client client){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(client);
        transaction.commit();
    }
}
