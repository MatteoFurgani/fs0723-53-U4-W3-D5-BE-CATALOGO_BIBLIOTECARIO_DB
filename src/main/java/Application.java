import dao.AutoreDAO;
import dao.LibriDAO;
import dao.RivisteDAO;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
            System.out.println("Aggiunta di un elemento al catalogo...");

            Autore autore = new Autore("Marco", "Marchi");


            Riviste rivista = new Riviste("R22", "Titolo Rivista", 2024, 100, Periodicita.MENSILE, autore);
            rivista.getAutore().add(autore);

            Archivio archivio = new Archivio(emf);
            archivio.aggiungiElementoCatalogo(rivista);

            System.out.println("Elemento aggiunto al catalogo con successo");
        } catch (Exception e) {
            System.err.println("Errore durante l'aggiunta dell'elemento al catalogo: " + e.getMessage());
        }



        System.out.println("Hello World");


        em.close();
        emf.close();
    }
}
