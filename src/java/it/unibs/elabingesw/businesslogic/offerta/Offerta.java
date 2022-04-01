package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.gestione.Manageable;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.io.Serializable;

/**
 * @author Elia
 */
public class Offerta implements Manageable, Serializable {
    private final String nomeArticolo;
    private final Utente autore;
    private final Categoria categoriaDiAppartenenza;
    private final ListaCampiCompilati listaCampiCompilati;
    private StatoOfferta statoOfferta;

    public Offerta(String nomeArticolo, Utente autore, ListaCampiCompilati listaCampiCompilati, Categoria categoriaDiAppartenenza) {
        this.nomeArticolo = nomeArticolo;
        this.autore = autore;
        this.categoriaDiAppartenenza = categoriaDiAppartenenza;
        this.listaCampiCompilati = listaCampiCompilati;
        this.statoOfferta = StatoOfferta.APERTA;
    }

    @Override
    public boolean isStessoNome(String nomeArticolo) {
        return this.nomeArticolo.equals(nomeArticolo);
    }

    public boolean isOffertaAperta() {
        return this.statoOfferta == StatoOfferta.APERTA;
    }

    public boolean isStessoAutore(Utente autore) {
        return this.autore.equals(autore);
    }

    public void ritiraOfferta() {
        this.statoOfferta = StatoOfferta.RITIRATA;
    }

    @Override
    public String toString() {
        return "Offerta{" +
                "nomeArticolo='" + nomeArticolo + '\'' +
                ", autore=" + autore +
                ", listaCampiCompilati=" + listaCampiCompilati +
                ", categoriaDiAppartenenza=" + categoriaDiAppartenenza.toStringRidotto() +
                ", statoOfferta=" + statoOfferta +
                '}';
    }

    public String getNomeArticolo() {
        return nomeArticolo;
    }

    public boolean appartieneA(Categoria categoria) {
        return this.categoriaDiAppartenenza.equals(categoria);
    }
}
