package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe StatoOfferta per la gestione delle operazioni
 * che si possono effettuare sui diversi tipi di offerte.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
class StatoOfferta implements Serializable {

    private StatoOffertaEnum statoOffertaEnum;
    private LocalDate dataCreazioneStato;
    private Scambio scambio;

    private Offerta altraOfferta;
    private ListaCampiCompilati listaCampiAppuntamento;

    /**
     * Costruttore di classe
     */
    public StatoOfferta() {
        this.statoOffertaEnum = StatoOffertaEnum.APERTA;
        this.listaCampiAppuntamento = null;
        impostaData();
    }

    /**
     * Metodo getter.
     *
     * @return la lista dei campi che definiscono un appuntamento
     */
    public ListaCampiCompilati getListaCampiAppuntamento() {
        return listaCampiAppuntamento;
    }

    /**
     * Metodo setter.
     *
     * @param la lista dei campi che definiscono un appuntamento
     */
    private void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.listaCampiAppuntamento = listaCampiAppuntamento;
    }

    /**
     * Metodo setter.
     *
     * @param scambio l'oggetto di tipo Scambio
     */
    public void setScambio(Scambio scambio) {
        this.scambio = scambio;
    }

    /**
     * Metodo che controlla se un'offerta è aperta o meno.
     *
     * @return TRUE se l'offerta è aperta
     *         FALSE se l'offerta non è aperta
     */
    public boolean isAperta() {
        return this.statoOffertaEnum == StatoOffertaEnum.APERTA;
    }

    /**
     * Metodo che ritira un'offerta trasformandone lo 
     * stato in RITIRATA.
     */
    public void ritiraOfferta() {
        this.statoOffertaEnum = StatoOffertaEnum.RITIRATA;
        impostaData();
    }

    /**
     * Metodo che setta un'offerta accoppiata con un'offerta
     * passata per parametro.
     *
     * @param offertaDaBarattareB l'oggetto di tipo offerta
     */
    public void setOffertaAccoppiataCon(Offerta offertaDaBarattareB) {
        this.statoOffertaEnum = StatoOffertaEnum.ACCOPPIATA;
        this.altraOfferta = offertaDaBarattareB;
        impostaData();
    }

    /**
     * Metodo che setta un'offerta selezionata con un'offerta
     * passata per parametro.
     *
     * @param offertaDaBarattareB l'oggetto di tipo offerta
     */
    public void setOffertaSelezionataCon(Offerta offertaDaBarattareA) {
        this.statoOffertaEnum = StatoOffertaEnum.SELEZIONATA;
        this.altraOfferta = offertaDaBarattareA;
        impostaData();
    }

    /**
     * Metodo che imposta la data attuale.
     */
    private void impostaData() {
        this.dataCreazioneStato = LocalDate.now();
    }

    /**
     * Metodo che aggiorna lo stato di un'offerta.
     */
    public void aggiornaStatoOfferta() {
        if (statoOffertaEnum == StatoOffertaEnum.ACCOPPIATA
                || statoOffertaEnum == StatoOffertaEnum.SELEZIONATA
                || statoOffertaEnum == StatoOffertaEnum.IN_SCAMBIO) {
            if (dataCreazioneStato.isAfter(LocalDate.now().plusDays(scambio.getScadenza()))) {
                this.statoOffertaEnum = StatoOffertaEnum.APERTA;
                this.altraOfferta = null;
                this.listaCampiAppuntamento = null;
                impostaData();
            }
        }
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "StatoOfferta{" +
                "statoOffertaEnum=" + statoOffertaEnum +
                '}';
    }

    /**
     * Metodo che controlla se un'offerta è selezionata o meno.
     *
     * @return TRUE se l'offerta è selezionata
     *         FALSE se l'offerta non è selezionata
     */
    public boolean isSelezionata() {
        return statoOffertaEnum == StatoOffertaEnum.SELEZIONATA;
    }

    /**
     * Metodo getter.
     *
     * @return un offerta accoppiata
     */
    public Offerta getOffertaAccoppiata() {
        return altraOfferta;
    }

    /**
     * Metodo che permette di accettare una proposta 
     * di scambio associata.
     *
     * @param listaCampiAppuntamento la lista dei campi che definiscono un appuntamento
     */
    public void accettaPropostaDiScambioAssociata(ListaCampiCompilati listaCampiAppuntamento) {
        this.statoOffertaEnum = StatoOffertaEnum.IN_SCAMBIO; // B
        this.altraOfferta // A
                .getStatoOfferta().statoOffertaEnum = StatoOffertaEnum.IN_SCAMBIO;
        this.altraOfferta
                .getStatoOfferta().setListaCampiAppuntamento(listaCampiAppuntamento);

        this.impostaData();
        this.altraOfferta.getStatoOfferta().impostaData();
    }

    /**
     * Metodo che controlla se un'offerta è in scambio o meno.
     *
     * @return TRUE se l'offerta è in scambio
     *         FALSE se l'offerta non è in scambio
     */
    public boolean isInScambio() {
        return this.statoOffertaEnum == StatoOffertaEnum.IN_SCAMBIO;
    }

    /**
     * Metodo che permette di accettare un'offerta facendole
     * cambiare lo stato in chiusa.
     */
    public void accettaAppuntamento() {
        this.statoOffertaEnum = StatoOffertaEnum.CHIUSA;
        impostaData();

        this.getOffertaAccoppiata().getStatoOfferta().statoOffertaEnum = StatoOffertaEnum.CHIUSA;
        this.altraOfferta.getStatoOfferta()
                .setListaCampiAppuntamento(this.getListaCampiAppuntamento());
        this.getOffertaAccoppiata().getStatoOfferta().impostaData();
    }

    /**
     * Metodo che permette di proporre un altro appuntamento
     * passandone i dettagli tramite una lista in input.
     *
     * @param listaCampiAppuntamento la lista dei campi che definiscono un appuntamento
     */
    public void proponiAltroAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.altraOfferta
                .getStatoOfferta().setListaCampiAppuntamento(listaCampiAppuntamento);
        this.setListaCampiAppuntamento(null);

        this.impostaData();
        this.altraOfferta.getStatoOfferta().impostaData();
    }

    /**
     * Metodo che controlla se un'offerta è chiusa o meno.
     *
     * @return TRUE se l'offerta è chiusa
     *         FALSE se l'offerta non è chiusa
     */
    public boolean isChiusa() {
        return this.statoOffertaEnum == StatoOffertaEnum.CHIUSA;
    }
}
