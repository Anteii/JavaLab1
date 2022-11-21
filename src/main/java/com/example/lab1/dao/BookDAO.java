package com.example.lab1.dao;

import com.example.lab1.model.Book;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Model;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;


@Stateless
public class BookDAO implements Serializable {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public Book findByID(Integer id) {
        return em.find(Book.class, id);
    }

    public List<Book> getAllBooks(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> rootEntry = cq.from(Book.class);
        CriteriaQuery<Book> all = cq.select(rootEntry);
        TypedQuery<Book> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public int removeById(Integer id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(findByID(id));
        transaction.commit();

        return 1;
    }

    public int create(Book book){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(book);
        transaction.commit();
        return 1;
    }

    public int update(Book book){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(book);
        transaction.commit();
        return 1;
    }
}
