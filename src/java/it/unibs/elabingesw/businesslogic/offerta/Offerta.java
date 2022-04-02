package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.gestione.Manageable;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Elia
 */
public class Offerta implements Manageable, Serializable {
    private final String nomeArticolo;
    private final Utente autore;
    private final Categoria categoriaDiAppartenenza;
    private final ListaCampiCompilati listaCampiCompilati;
    private final StatoOfferta statoOfferta;

    public Offerta(String nomeArticolo, Utente autore, ListaCampiCompilati listaCampiCompilati, Categoria categoriaDiAppartenenza) {
        this.nomeArticolo = nomeArticolo;
        this.autore = autore;
        this.categoriaDiAppartenenza = categoriaDiAppartenenza;
        this.listaCampiCompilati = listaCampiCompilati;
        this.statoOfferta = new StatoOfferta();
    }

    public Categoria getCategoriaDiAppartenenza() {
        return categoriaDiAppartenenza;
    }

    @Override
    public boolean isStessoNome(String nomeArticolo) {
        return this.nomeArticolo.equals(nomeArticolo);
    }

    public boolean isOffertaAperta() {
        return this.statoOfferta.isAperta();
    }

    public boolean isStessoAutore(Utente autore) {
        return this.autore.equals(autore);
    }

    public void ritiraOfferta() {
       this.statoOfferta.ritiraOfferta();
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

    public void creaLegameEModificaStatiConOfferta(Offerta offertaDaBarattareB) {
        this.statoOfferta.setOffertaAccoppiataCon(offertaDaBarattareB);
        offertaDaBarattareB.statoOfferta.setOffertaSelezionataCon(this);
    }


    public void aggiornaStatoOfferta() {
        this.statoOfferta.aggiornaStatoOfferta();
    }

    public void setInfoScambio(Optional<Scambio> infoDiScambio) {
        infoDiScambio.ifPresent(this.statoOfferta::setScambio);
    }
}
