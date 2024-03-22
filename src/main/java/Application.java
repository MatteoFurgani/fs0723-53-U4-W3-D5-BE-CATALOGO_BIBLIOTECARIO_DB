import dao.AutoreDAO;
import dao.LibriDAO;
import dao.RivisteDAO;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliotecario");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        AutoreDAO ad = new AutoreDAO(em);
        LibriDAO ld = new LibriDAO(em);
        RivisteDAO rd = new RivisteDAO(em);

        Autore autore1 = new Autore("Mario", "Rossi");
        Autore autore2 = new Autore("Carla", "Verdi");
        Autore autore3 = new Autore("Luigi", "Bianchi");
        ad.save(autore1);
        ad.save(autore2);
        ad.save(autore3);

        Libri libro1 = new Libri("1", "Libro 1", 2022, 300, autore1, "Fiction");
        Libri libro2 = new Libri("2", "Libro 2", 2020, 250, autore2, "Fantasy");
        Libri libro3 = new Libri("3", "Libro 3", 2019, 400, autore3, "Mystery");
        ld.save(libro1);
        ld.save(libro2);
        ld.save(libro3);


        Riviste rivista1 = new Riviste("4", "Rivista 1", 2021, 50, Periodicita.MENSILE, autore1);
        Riviste rivista2 = new Riviste("5", "Rivista 2", 2018, 60, Periodicita.SETTIMANALE, autore3);
        Riviste rivista3 = new Riviste("6", "Rivista 3", 2017, 70, Periodicita.MENSILE, autore3);
        rd.save(rivista1);
        rd.save(rivista2);
        rd.save(rivista3);

        //-----------------------------AGGIUNTA ELEMENTO------------------------------------
        try {

            Autore autore = new Autore("Marco", "Marchi");


            Libri libro = new Libri("L123", "Titolo Libro", 2024, 200, autore, "Genere");


            Archivio archivio = new Archivio(emf);


            archivio.aggiungiElementoCatalogo(libro);

            System.out.println("Elemento aggiunto al catalogo con successo");
        } catch (Exception e) {
            System.err.println("Errore durante l'aggiunta dell'elemento al catalogo: " + e.getMessage());
        } finally {
            emf.close();
        }

       //-----------------------------RIMOZIONE ELEMENTO------------------------------------

        try {
            Archivio archivio = new Archivio(emf);

            String codiceISBN = "L123";

            System.out.println("Rimozione dell'elemento con codice ISBN: " + codiceISBN);

            archivio.rimuoviElementoCatalogoByISBN(codiceISBN);
        } catch (Exception e) {
            System.err.println("Errore durante la rimozione dell'elemento: " + e.getMessage());
        } finally {
            emf.close();
        }

        //-----------------------------RICERCA ELEMENTO PER IBSN------------------------------------

        try {
            Archivio archivio = new Archivio(emf);

            String codiceISBN = "6";

            Pubblicazione elementoTrovato = archivio.ricercaPerISBN(codiceISBN);

            if (elementoTrovato != null) {
                System.out.println("Elemento trovato nel catalogo:");
                System.out.println(elementoTrovato);
            } else {
                System.out.println("Nessun elemento trovato nel catalogo con il codice ISBN specificato.");
            }
        } finally {
            emf.close();
        }

        //-----------------------------RICERCA ELEMENTO PER ANNO PUBBLICAZIONE-----------------------------

        try {
            Archivio archivio = new Archivio(emf);

            int annoPubblicazione = 2022;

            List<Pubblicazione> pubblicazioniPerAnno = archivio.ricercaPerAnnoPubblicazione(annoPubblicazione);

            if (!pubblicazioniPerAnno.isEmpty()) {
                System.out.println("Pubblicazioni trovate per l'anno " + annoPubblicazione + ":");
                for (Pubblicazione pubblicazione : pubblicazioniPerAnno) {
                    System.out.println(pubblicazione);
                }
            } else {
                System.out.println("Nessuna pubblicazione trovata per l'anno " + annoPubblicazione);
            }
        } finally {
            // emf.close();
        }

        //-----------------------------RICERCA ELEMENTO PER AUTORE------------------------------------

        try {

            Archivio archivio = new Archivio(emf);

            String nomeAutore = "Mario";
            String cognomeAutore = "Rossi";

            List<Pubblicazione> pubblicazioniPerAutore = archivio.ricercaPerAutore(nomeAutore, cognomeAutore);

            if (!pubblicazioniPerAutore.isEmpty()) {
                System.out.println("Pubblicazioni trovate per l'autore " + nomeAutore + " " + cognomeAutore + ":");
                for (Pubblicazione pubblicazione : pubblicazioniPerAutore) {
                    System.out.println(pubblicazione);
                }
            } else {
                System.out.println("Nessuna pubblicazione trovata per l'autore " + nomeAutore + " " + cognomeAutore);
            }
        } finally {
            //emf.close();
        }

        //-----------------------------RICERCA ELEMENTO PER TITOLO------------------------------------

        try {
            Archivio archivio = new Archivio(emf);

            String titolo = "Libro 1";

            List<Pubblicazione> risultati = archivio.ricercaPerTitolo(titolo);

            if (risultati.isEmpty()) {
                System.out.println("Nessuna pubblicazione trovata con il titolo: " + titolo);
            } else {
                System.out.println("Pubblicazioni trovate per il titolo: " + titolo);
                for (Pubblicazione pubblicazione : risultati) {
                    System.out.println(pubblicazione);
                }
            }
        } finally {
            emf.close();
        }





        System.out.println("Hello World");


        em.close();
        emf.close();
    }
}
