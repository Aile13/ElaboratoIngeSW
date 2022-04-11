package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.gestione.Manageable;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.io.Serializable;
import java.util.Optional;

/**
 * Classe Offerta che rappresenta un'offerta generica.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class Offerta implements Manageable, Serializable {
    private final String nomeArticolo;
    private final Utente autore;
    private final Categoria categoriaDiAppartenenza;
    private final ListaCampiCompilati listaCampiCompilati;
    private final StatoOfferta statoOfferta;

    /**
     * Costruttore di classe, accetta come parametri il nome dell'
     * articolo, l'autore dell'offerta, la lista dei campi compi-
     * lati e la catgoria di appartenenza dell'articolo.
     *
     * @param nomeArticolo
     * @param autore
     * @param listaCampiCompilati
     * @param categoriaDiAppartenenza
     */ 
    public Offerta(String nomeArticolo, Utente autore, ListaCampiCompilati listaCampiCompilati, Categoria categoriaDiAppartenenza) {
        this.nomeArticolo = nomeArticolo;
        this.autore = autore;
        this.categoriaDiAppartenenza = categoriaDiAppartenenza;
        this.listaCampiCompilati = listaCampiCompilati;
        this.statoOfferta = new StatoOfferta();
    }

    /**
     * Metodo getter.
     *
     * @return la categoria di appartenenza dell'offerta
     */
    public Categoria getCategoriaDiAppartenenza() {
        return categoriaDiAppartenenza;
    }

    /**
     * Metodo getter.
     *
     * @return lo stato dell'offerta
     */
    StatoOfferta getStatoOfferta() {
        return statoOfferta;
    }

    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due articoli hanno lo stesso no-
     * me o meno.
     *
     * @param nomeArticolo il nome dell'articolo
     * @return TRUE se i nomi sono uguali
     *         FALSE se i nomi sono diversi
     */
    @Override
    public boolean isStessoNome(String nomeArticolo) {
        return this.nomeArticolo.equals(nomeArticolo);
    }

    /**
     * Metodo che controlla se un'offerta è aperta o meno.
     *
     * @return TRUE se l'offerta è aperta
     *         FALSE se l'offerta non è aperta
     */
    public boolean isOffertaAperta() {
        return this.statoOfferta.isAperta();
    }

    /**
     * Metodo che controlla se due autori sono uguali
     * o meno.
     *
     * @param autore l'oggetto di tipo Utente
     * @return TRUE se gli autori sono uguali
     *         FALSE se gli autori sono diversi
     */
    public boolean isStessoAutore(Utente autore) {
        return this.autore.equals(autore);
    }

    /**
     * Metodo che ritira un'offerta.
     */
    public void ritiraOfferta() {
       this.statoOfferta.ritiraOfferta();
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
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

    /**
     * Metodo getter.
     *
     * @return il nome dell'articolo
     */
    public String getNomeArticolo() {
        return nomeArticolo;
    }

    /**
     * Metodo che controlla se un articolo appartiene
     * a una categoria passata per parametro.
     *
     * @param categoria l'oggetto di tipo Categoria
     * @return TRUE se l'articolo appartiene alla categoria in input
     *         FALSE se l'articolo non appartiene alla categoria in input
     */
    public boolean appartieneA(Categoria categoria) {
        return this.categoriaDiAppartenenza.equals(categoria);
    }

    /**
     * Metodo che permette di creare un legame tra due 
     * tipi diversi di offerte (es. offerta accoppiata-
     * selezionata).
     *
     * @param offertaDaBarattareB oggetto di tipo Offerta
     */
    public void creaLegameEModificaStatiConOfferta(Offerta offertaDaBarattareB) {
        this.statoOfferta.setOffertaAccoppiataCon(offertaDaBarattareB);
        offertaDaBarattareB.statoOfferta.setOffertaSelezionataCon(this);
    }

    /**
     * Metodo che aggiorna lo stato di un'offerta.
     */
    public void aggiornaStatoOfferta() {
        this.statoOfferta.aggiornaStatoOfferta();
    }

    /**
     * Metodo setter.
     *
     * @param infoDiScambio le informazioni di uno scambio
     */
    public void setInfoScambio(Optional<Scambio> infoDiScambio) {
        infoDiScambio.ifPresent(this.statoOfferta::setScambio);
    }

    /**
     * Metodo che controlla se un'offerta è selezionata o meno.
     *
     * @return TRUE se l'offerta è selezionata
     *         FALSE se l'offerta non è selezionata
     */
    public boolean isOffertaSelezionata() {
        return statoOfferta.isSelezionata();
    }

    /**
     * Metodo getter.
     *
     * @return l'offerta accoppiata
     */
    public Offerta getOffertaAccoppiata() {
        return statoOfferta.getOffertaAccoppiata();
    }

    /**
     * Metodo che permette di accettare una proposta
     * di scambio associata.
     *
     * @param listaCampiAppuntamento la lista dei campi dell'appuntamento
     */
    public void accettaPropostaDiScambioAssociata(ListaCampiCompilati listaCampiAppuntamento) {
        this.statoOfferta.accettaPropostaDiScambioAssociata(listaCampiAppuntamento);
    }

    /**
     * Metodo che controlla se un'offerta è in scambio o meno.
     *
     * @return TRUE se l'offerta è in scambio
     *         FALSE se l'offerta non è in scambio
     */
    public boolean isOffertaInScambio() {
        return statoOfferta.isInScambio();
    }

    /**
     * Metodo getter.
     *
     * @return lo lista dei campi di un appuntamento
     */
    public ListaCampiCompilati getListaCampiAppuntamento() {
        return statoOfferta.getListaCampiAppuntamento();
    }

    /**
     * Metodo che permette ad una parte di accettare 
     * l'appuntamento per lo scambio di un'offerta 
     * di un'altra parte.
     *
     */
    public void accettaAppuntamento() {
        this.statoOfferta.accettaAppuntamento();
    }

    /**
     * Metodo che propone un altro appuntamento,
     * con le specifiche di quest'ultimo inserite
     * in una lista che viene passata per parametro.
     *
     * @param listaCampiAppuntamento la lista dei campi dell'appuntamento
     */
    public void proponiAltroAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.statoOfferta.proponiAltroAppuntamento(listaCampiAppuntamento);
    }

    /**
     * Metodo che controlla se un'offerta è chiusa o meno.
     *
     * @return TRUE se l'offerta è chiusa
     *         FALSE se l'offerta non è chiusa
     */
    public boolean isOffertaChiusa() {
        return this.statoOfferta.isChiusa();
    }
}
