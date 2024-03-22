package dao;

import entities.Riviste;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class RivisteDAO {

    private final EntityManager em;

    public RivisteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Riviste rivista) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(rivista);
        transaction.commit();
        System.out.println("Rivista salvata con successo");
    }

    public Riviste findByCodiceISBN(String codiceISBN) {
        Riviste rivista = em.find(Riviste.class, codiceISBN);
        if (rivista == null) throw new NotFoundException("Rivista non trovata");
        return rivista;
    }

    public void findByISBNAndDelete(Riviste rivista) {
        Riviste found = this.findByCodiceISBN(rivista.getCodiceISBN());
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Rivista eliminata con successo");
    }

    public List<Riviste> findAll() {
        return em.createQuery("SELECT r FROM Riviste r", Riviste.class).getResultList();
    }
}

