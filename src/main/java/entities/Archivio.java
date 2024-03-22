package entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class Archivio {
    private final EntityManagerFactory emf;
    public Archivio(EntityManagerFactory emf) {

        this.emf = emf;
    }

    //---------------------METODO AGGIUNGI ELEMENTO--------------------

    public void aggiungiElementoCatalogo(Pubblicazione pubblicazione) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            if (pubblicazione.getAutore() != null && pubblicazione.getAutore().getId() == null) {
                em.persist(pubblicazione.getAutore());
            }
            em.persist(pubblicazione);
            transaction.commit();
            System.out.println("Elemento aggiunto al catalogo con successo");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante l'aggiunta dell'elemento al catalogo: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    //---------------------METODO RIMUOVI ELEMENTO--------------------

    public void rimuoviElementoCatalogoByISBN(String codiceISBN) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            Pubblicazione pubblicazione = em.createQuery("SELECT p FROM Pubblicazione p WHERE p.codiceISBN = :isbn", Pubblicazione.class)
                    .setParameter("isbn", codiceISBN)
                    .getSingleResult();

            if (pubblicazione != null) {
                em.remove(pubblicazione);
                System.out.println("Elemento rimosso dal catalogo con successo");
            } else {
                System.out.println("Nessun elemento trovato nel catalogo con il codice ISBN specificato");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante la rimozione dell'elemento dal catalogo: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    //---------------------METODO RICERCA PER IBSN--------------------

    public Pubblicazione ricercaPerISBN(String codiceISBN) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pubblicazione.class, codiceISBN);
        } finally {
            em.close();
        }
    }

    //---------------------METODO RICERCA ANNO PUBBLICAZIONE--------------------

    public List<Pubblicazione> ricercaPerAnnoPubblicazione(int annoPubblicazione) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Pubblicazione p WHERE p.annoPubblicazione = :anno", Pubblicazione.class);
            query.setParameter("anno", annoPubblicazione);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    //---------------------METODO RICERCA PER AUTORI--------------------

    public List<Pubblicazione> ricercaPerAutore(String nomeAutore, String cognomeAutore) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pubblicazione p WHERE p.autore.nome = :nome AND p.autore.cognome = :cognome", Pubblicazione.class)
                    .setParameter("nome", nomeAutore)
                    .setParameter("cognome", cognomeAutore)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    //---------------------METODO RICERCA PER TITOLO--------------------

    public List<Pubblicazione> ricercaPerTitolo(String titolo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pubblicazione p WHERE p.titolo LIKE :titolo", Pubblicazione.class)
                    .setParameter("titolo", "%" + titolo + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }





}
