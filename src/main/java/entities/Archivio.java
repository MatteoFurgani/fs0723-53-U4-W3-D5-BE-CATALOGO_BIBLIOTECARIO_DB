package entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Archivio {
    private final EntityManagerFactory emf;
    public Archivio(EntityManagerFactory emf) {

        this.emf = emf;
    }

    public void aggiungiElementoCatalogo(Pubblicazione pubblicazione) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pubblicazione);
        em.getTransaction().commit();
        em.close();
    }

}
