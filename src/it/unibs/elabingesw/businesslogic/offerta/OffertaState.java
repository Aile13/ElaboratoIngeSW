package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.io.Serializable;

/**
 * Interfaccia OffertaState per la gestione delle operazioni
 * che si possono effettuare sui diversi tipi di offerte.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public interface OffertaState extends Serializable, DomainTypeToRender {
    
    /**
     * Metodo getter.
     * 
     * @param context oggetto di tipo OffertaContext
     * @return l'offerta associata all'oggetto passato
               per parametro
     */
    OffertaContext getOffertaAssociata(OffertaContext context);

    /**
     * Metodo getter.
     * 
     * @param context oggetto di tipo OffertaContext
     * @return la lista dei campi di un appuntamento
     */
    ListaCampiCompilati getListaCampiAppuntamento(OffertaContext context);

    /**
     * Metodo setter.
     * 
     * @param listaCampiAppuntamento la lista dei campi di un appuntamento
     */
    void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento);

    /**
     * Metodo che controlla se un'offerta è aperta o meno.
     *
     * @param context oggetto di tipo OffertaContext
     * @return TRUE se l'offerta è aperta
     * FALSE se l'offerta non è aperta
     */
    boolean isOffertaAperta(OffertaContext context);

    /**
     * Metodo che ritira un'offerta trasformandone lo
     * stato in RITIRATA.
     * 
     * @param context oggetto di tipo OffertaContext
     */
    void ritiraOfferta(OffertaContext context);

    /**
     * Metodo che permette di creare un legame tra due diverse offerte e 
     * ne modifica i rispettivi stati dopo la creazione del legame.
     * 
     * @param context primo oggetto di tipo OffertaContext
     * @param offertaContextDaBarattareB secondo oggetto di tipo OffertaContext
     * @param infoDiScambio oggetto di tipo Scambio
     */
    void creaLegameEModificaStatiConOffertaEInfoScambio(OffertaContext context, OffertaContext offertaContextDaBarattareB, Scambio infoDiScambio);

    /**
     * Metodo che aggiorna lo stato di un'offerta.
     * 
     * @param context oggetto di tipo OffertaContext
     */
    void aggiornaStatoOfferta(OffertaContext context);

    /**
     * Metodo che controlla se un'offerta è selezionata o meno.
     *
     * @param context oggetto di tipo OffertaContext
     * @return TRUE se l'offerta è selezionata
     * FALSE se l'offerta non è selezionata
     */
    boolean isOffertaSelezionata(OffertaContext context);

    /**
     * Metodo che permette di accettare una proposta
     * di scambio associata a un'offerta passata per
     * parametro.
     *
     * @param context oggetto di tipo OffertaContext
     * @param listaCampiAppuntamento la lista dei campi dell'appuntamento
     */
    void accettaPropostaDiScambioAssociata(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento);

    /**
     * Metodo che controlla se un'offerta è in scambio o meno.
     *
     * @param context oggetto di tipo OffertaContext
     * @return TRUE se l'offerta è in scambio
     * FALSE se l'offerta non è in scambio
     */
    boolean isOffertaInScambio(OffertaContext context);

    /**
     * Metodo che permette a una parte di accettare
     * l'appuntamento per lo scambio di un'offerta
     * di un'altra parte.
     * 
     * @param context oggetto di tipo OffertaContext
     */
    void accettaAppuntamento(OffertaContext context);

    /**
     * Metodo che propone un altro appuntamento,
     * con le specifiche di quest'ultimo inserite
     * in una lista che viene passata per parametro.
     * 
     * @param context oggetto di tipo OffertaContext
     * @param listaCampiAppuntamento la lista dei campi del nuovo appuntamento
     */
    void proponiAltroAppuntamento(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento);

    /**
     * Metodo che controlla se un'offerta è chiusa o meno.
     *
     * @param context oggetto di tipo OffertaContext
     * @return TRUE se l'offerta è chiusa
     * FALSE se l'offerta non è chiusa
     */
    boolean isOffertaChiusa(OffertaContext context);

    /**
     * Metodo che imposta la data.
     */
    void impostaData();
}
