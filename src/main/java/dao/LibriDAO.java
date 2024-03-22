package dao;

import entities.Libri;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class LibriDAO {

    private final EntityManager em;

    public LibriDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Libri libri) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(libri);
        transaction.commit();
        System.out.println("Libro salvato con successo");
    }

    public Libri findById(String codiceISBN) {
        Libri libri = em.find(Libri.class, codiceISBN);
        if (libri == null) throw new NotFoundException("Libro non trovato");
        return libri;
    }

    public void findByISBNAnddelete(Libri libri) {
        Libri found = this.findById(libri.getCodiceISBN());
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Libro eliminato con successo");
    }

    public List<Libri> findAll() {
        return em.createQuery("SELECT l FROM Libri l", Libri.class).getResultList();
    }
}


