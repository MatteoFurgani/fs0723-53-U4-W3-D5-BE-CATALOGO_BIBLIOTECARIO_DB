package dao;

import entities.Prestito;
import exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class PrestitoDAO {

    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(prestito);
        transaction.commit();
        System.out.println("Prestito salvato con successo");
    }

    public Prestito findById(Long id) {
        Prestito prestito = em.find(Prestito.class, id);
        if (prestito == null) throw new NotFoundException("Prestito non trovato");
        return prestito;
    }

    public void findByIdAndDelete(Prestito prestito) {
        Prestito found = this.findById(prestito.getId());
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Prestito eliminato con successo");
    }

    public List<Prestito> findAll() {
        return em.createQuery("SELECT p FROM Prestito p", Prestito.class).getResultList();
    }
}

