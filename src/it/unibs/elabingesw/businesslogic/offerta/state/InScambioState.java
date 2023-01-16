package it.unibs.elabingesw.businesslogic.offerta.state;

import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.businesslogic.offerta.OffertaState;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.time.LocalDate;

/**
 * Classe InScambioState per gestire un'offerta in scambio.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class InScambioState implements OffertaState {
    private final OffertaContext altraOffertaContextDaBarattare;
    private ListaCampiCompilati listaCampiAppuntamento;
    private final Scambio infoDiScambio;
    private LocalDate dataCreazioneStato;

    /**
     * Costruttore di classe
     * 
     * @param altraOffertaContextDaBarattare oggetto di tipo OffertaContext
     * @param infoDiScambio oggetto di tipo Scambio
     */
    public InScambioState(OffertaContext altraOffertaContextDaBarattare, Scambio infoDiScambio) {
        this.altraOffertaContextDaBarattare = altraOffertaContextDaBarattare;
        this.infoDiScambio = infoDiScambio;
        listaCampiAppuntamento = null;
        dataCreazioneStato = LocalDate.now();
    }

    /**
     * Costruttore di classe
     * 
     * @param altraOffertaContextDaBarattare oggetto di tipo OffertaContext
     * @param listaCampiAppuntamento oggetto di tipo ListaCampiCompilati
     * @param infoDiScambio oggetto di tipo Scambio
     */
    public InScambioState(OffertaContext altraOffertaContextDaBarattare, ListaCampiCompilati listaCampiAppuntamento, Scambio infoDiScambio) {
        this.altraOffertaContextDaBarattare = altraOffertaContextDaBarattare;
        this.listaCampiAppuntamento = listaCampiAppuntamento;
        this.infoDiScambio = infoDiScambio;
        dataCreazioneStato = LocalDate.now();
    }

    /**
     * Metodo getter.
     * 
     * @param context oggetto di tipo OffertaContext
     * @return l'offerta associata
     */
    @Override
    public OffertaContext getOffertaAssociata(OffertaContext context) {
        return altraOffertaContextDaBarattare;
    }

    /**
     * Metodo getter.
     * 
     * @param context oggetto di tipo OffertaContext
     * @return la lista dei campi dell'appuntamento
     */
    @Override
    public ListaCampiCompilati getListaCampiAppuntamento(OffertaContext context) {
        return this.listaCampiAppuntamento;
    }

    /**
     * Metodo setter.
     * 
     * @param listaCampiAppuntamento la lista dei campi dell'appuntamento
     */
    @Override
    public void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.listaCampiAppuntamento = listaCampiAppuntamento;
    }

    /**
     * Metodo che controlla se un'offerta è aperta o meno.
     *
     * @param context oggetto di tipo OffertaContext
     * @return FALSE l'offerta non è aperta
     */
    @Override
    public boolean isOffertaAperta(OffertaContext context) {
        return false;
    }

    /**
     * Metodo che ritira un'offerta trasformandone lo
     * stato in RITIRATA.
     * 
     * @param context oggetto di tipo OffertaContext
     */
    @Override
    public void ritiraOfferta(OffertaContext context) {

    }

    /**
     * Metodo che permette di creare un legame tra due diverse offerte e 
     * ne modifica i rispettivi stati dopo la creazione del legame.
     * 
     * @param context primo oggetto di tipo OffertaContext
     * @param offertaContextDaBarattareB secondo oggetto di tipo OffertaContext
     * @param infoDiScambio oggeto di tipo Scambio
     */
    @Override
    public void creaLegameEModificaStatiConOffertaEInfoScambio(OffertaContext context, OffertaContext offertaContextDaBarattareB, Scambio infoDiScambio) {

    }

    /**
     * Metodo che aggiorna lo stato di un'offerta.
     * 
     * @param context oggetto di tipo OffertaContext
     */
    @Override
    public void aggiornaStatoOfferta(OffertaContext context) {
        if (LocalDate.now().isAfter(dataCreazioneStato.plusDays(infoDiScambio.scadenza()))) {
            context.setOffertaState(new ApertaState());
        }
    }

    /**
     * Metodo che controlla se un'offerta è selezionata o meno.
     *
     * @param context oggetto di tipo OffertaContext
     * @return FALSE l'offerta non è selezionata
     */
    @Override
    public boolean isOffertaSelezionata(OffertaContext context) {
        return false;
    }

    /**
     * Metodo che permette di accettare una proposta
     * di scambio associata a un'offerta passata per
     * parametro.
     *
     * @param context oggetto di tipo OffertaContext
     * @param listaCampiAppuntamento la lista dei campi dell'appuntamento
     */
    @Override
    public void accettaPropostaDiScambioAssociata(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento) {

    }

    /**
     * Metodo che controlla se un'offerta è in scambio o meno.
     *
     * @param context oggetto di tipo OffertaContext
     * @return TRUE l'offerta è in scambio
     */
    @Override
    public boolean isOffertaInScambio(OffertaContext context) {
        return true;
    }

    /**
     * Metodo che permette a una parte di accettare
     * l'appuntamento per lo scambio di un'offerta
     * di un'altra parte.
     * 
     * @param context oggetto di tipo OffertaContext
     */
    @Override
    public void accettaAppuntamento(OffertaContext context) {
        context.setOffertaState(new ChiusaState());
        altraOffertaContextDaBarattare.setOffertaState(new ChiusaState());
        altraOffertaContextDaBarattare.getOffertaState().setListaCampiAppuntamento(getListaCampiAppuntamento(context));
    }

    /**
     * Metodo toString per ritornare una stringa.
     *
     * @return una stringa
     */
    @Override
    public String toString() {
        return "InScambioState{}";
    }

    /**
     * Metodo che permette a una parte di proporre
     * un altor appuntamento per poter scambiare due
     * offerte.
     * 
     * @param context oggetto di tipo OffertaContext
     * @param listaCampiAppuntamento oggetto di tipo ListaCampiCompilati
     */
    @Override
    public void proponiAltroAppuntamento(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento) {
        altraOffertaContextDaBarattare.getOffertaState().setListaCampiAppuntamento(listaCampiAppuntamento);
        this.setListaCampiAppuntamento(null);
        dataCreazioneStato = LocalDate.now();
        altraOffertaContextDaBarattare.getOffertaState().impostaData();
    }

    /**
     * Metodo che controlla se un'offerta è chiusa o meno.
     *
     * @param context oggetto di tipo OffertaContext
     * @return FALSE l'offerta non è chiusa
     */
    @Override
    public boolean isOffertaChiusa(OffertaContext context) {
        return false;
    }

    /**
     * Metodo che imposta la data.
     */
    @Override
    public void impostaData() {
        dataCreazioneStato = LocalDate.now();
    }
}
