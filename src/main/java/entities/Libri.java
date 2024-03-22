package entities;

import jakarta.persistence.Entity;


@Entity
public class Libri extends Pubblicazione {
    private Autore autore;
    private String genere;

    public Libri(){}

    public Libri(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Autore autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine, autore);
        this.autore = autore;
        this.genere = genere;
    }

    public Autore getAutore() {
        return autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libri{" +
                "codiceISBN='" + getCodiceISBN() + '\'' +
                ", titolo='" + getTitolo() + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }
}
