package dao;

import entities.Utente;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UtenteDAO {

    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(utente);
        transaction.commit();
        System.out.println("Utente salvato con successo");
    }

    public Utente findById(Long id) {
        Utente utente = em.find(Utente.class, id);
        if (utente == null) throw new NotFoundException("Utente non trovato");
        return utente;
    }

    public void findByIdAndDelete(Utente utente) {
        Utente found = this.findById(utente.getId());
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Utente eliminato con successo");
    }

    public List<Utente> findAll() {
        return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
    }
}

