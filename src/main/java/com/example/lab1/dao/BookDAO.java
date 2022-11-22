package com.example.lab1.dao;

import com.example.lab1.model.Book;
import jakarta.ejb.Stateless;
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
import java.util.function.Consumer;


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

    public void removeById(Integer id) {
        em.remove(findByID(id));
    }

    public void create(Book book){
        em.persist(book);
    }

    public void update(Book book){
        em.merge(book);
    }
}
