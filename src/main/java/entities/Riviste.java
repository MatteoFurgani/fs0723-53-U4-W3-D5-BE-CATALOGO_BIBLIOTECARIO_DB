package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Riviste extends Pubblicazione {

    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Riviste() {}

    public Riviste(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
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


