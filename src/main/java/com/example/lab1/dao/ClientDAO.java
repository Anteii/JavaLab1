package com.example.lab1.dao;

import com.example.lab1.model.Client;
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
public class ClientDAO implements Serializable {
    @PersistenceContext(unitName = "default")
    private EntityManager em;
    @Resource
    UserTransaction utx;

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

    public int create(Client client){
        try {
            utx.begin();
            em.persist(client);
            utx.commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int update(Client client){
        return create(client);
    }
}
