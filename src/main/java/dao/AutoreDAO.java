package dao;


import entities.Autore;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class AutoreDAO {

    private final EntityManager em;

    public AutoreDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Autore autore) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(autore);
        transaction.commit();
        System.out.println("Autore salvato con successo");
    }

    public Autore findById(Long id) {
        Autore autore = em.find(Autore.class, id);
        if (autore == null) throw new NotFoundException("Autore non trovato");
        return autore;
    }

    public void findByIdAndDelete(Autore autore) {
        Autore found = this.findById(autore.getId());
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Autore eliminato con successo");
    }

    public List<Autore> findAll() {
        return em.createQuery("SELECT a FROM Autore a", Autore.class).getResultList();
    }
}
