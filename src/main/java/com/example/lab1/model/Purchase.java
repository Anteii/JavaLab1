package com.example.lab1.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Purchase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "buy_book_id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "book_id", nullable = true)
    private Integer bookId;
    @Basic
    @Column(name = "client_id", nullable = true)
    private Integer clientId;
    @Basic
    @Column(name = "amount", nullable = false)
    private Integer amount;

    public void update(int bookId, int clientId, int amount) {
        setBookId(bookId);
        setClientId(clientId);
        setAmount(amount);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer buyBookId) {
        this.id = buyBookId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (!Objects.equals(id, purchase.id)) return false;
        if (!Objects.equals(bookId, purchase.bookId)) return false;
        if (!Objects.equals(clientId, purchase.clientId)) return false;
        return Objects.equals(amount, purchase.amount);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
