package entities;

import jakarta.persistence.*;

@Entity
public class Riviste extends Pubblicazione {

    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Riviste() {}

    public Riviste(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita, Autore autore) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine, autore);
        this.periodicita = periodicita;
        this.autore = autore;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                "codiceISBN='" + getCodiceISBN() + '\'' +
                ", titolo='" + getTitolo() + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                ", periodicita=" + periodicita +
                '}';
    }
}

