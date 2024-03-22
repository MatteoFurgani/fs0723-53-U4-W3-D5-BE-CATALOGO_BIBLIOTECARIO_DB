package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Pubblicazioni")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pubblicazione {
    @Id
    protected String codiceISBN;

    protected String titolo;
    protected int annoPubblicazione;
    protected int numeroPagine;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    protected Autore autore;

    public Pubblicazione() {
    }

    public Pubblicazione(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Autore autore) {
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
        this.autore = autore;
    }

    public String getCodiceISBN() {
        return codiceISBN;
    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public Autore getAutore() {
        return autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }

    @Override
    public String toString() {
        return "Pubblicazione{" +
                "codiceISBN='" + codiceISBN + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", autore=" + autore +
                '}';
    }
}
