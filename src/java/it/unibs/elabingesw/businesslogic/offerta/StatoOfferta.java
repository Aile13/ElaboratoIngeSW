package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Elia
 */
class StatoOfferta implements Serializable {

    private StatoOffertaEnum statoOffertaEnum;
    private LocalDate dataCreazioneStato;
    private Scambio scambio;

    private Offerta altraOfferta;
    private ListaCampiCompilati listaCampiAppuntamento;

    public StatoOfferta() {
        this.statoOffertaEnum = StatoOffertaEnum.APERTA;
        this.listaCampiAppuntamento = null;
        impostaData();
    }

    public ListaCampiCompilati getListaCampiAppuntamento() {
        return listaCampiAppuntamento;
    }

    public void setScambio(Scambio scambio) {
        this.scambio = scambio;
    }

    public boolean isAperta() {
        return this.statoOffertaEnum == StatoOffertaEnum.APERTA;
    }

    public void ritiraOfferta() {
        this.statoOffertaEnum = StatoOffertaEnum.RITIRATA;
    }

    public void setOffertaAccoppiataCon(Offerta offertaDaBarattareB) {
        this.statoOffertaEnum = StatoOffertaEnum.ACCOPPIATA;
        this.altraOfferta = offertaDaBarattareB;
        impostaData();
    }

    public void setOffertaSelezionataCon(Offerta offertaDaBarattareA) {
        this.statoOffertaEnum = StatoOffertaEnum.SELEZIONATA;
        this.altraOfferta = offertaDaBarattareA;
        impostaData();
    }

    private void impostaData() {
        this.dataCreazioneStato = LocalDate.now();
    }

    public void aggiornaStatoOfferta() {
        if (statoOffertaEnum == StatoOffertaEnum.ACCOPPIATA
                || statoOffertaEnum == StatoOffertaEnum.SELEZIONATA
                || statoOffertaEnum == StatoOffertaEnum.IN_SCAMBIO) {
            if (dataCreazioneStato.isAfter(LocalDate.now().plusDays(scambio.getScadenza()))) {
                this.statoOffertaEnum = StatoOffertaEnum.APERTA;
                this.altraOfferta = null;
                this.listaCampiAppuntamento = null;
            }
        }
    }

    @Override
    public String toString() {
        return "StatoOfferta{" +
                "statoOffertaEnum=" + statoOffertaEnum +
                '}';
    }

    public boolean isSelezionata() {
        return statoOffertaEnum == StatoOffertaEnum.SELEZIONATA;
    }

    public Offerta getOffertaAccoppiata() {
        return altraOfferta;
    }

    public void accettaPropostaDiScambioAssociata(ListaCampiCompilati listaCampiAppuntamento) {
        this.statoOffertaEnum = StatoOffertaEnum.IN_SCAMBIO; // B
        this.altraOfferta // A
                .getStatoOfferta().statoOffertaEnum = StatoOffertaEnum.IN_SCAMBIO;
        this.altraOfferta
                .getStatoOfferta().setListaCampiAppuntamento(listaCampiAppuntamento);

        this.impostaData();
        this.altraOfferta.getStatoOfferta().impostaData();
    }

    private void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.listaCampiAppuntamento = listaCampiAppuntamento;
    }

    public boolean isInScambio() {
        return this.statoOffertaEnum == StatoOffertaEnum.IN_SCAMBIO;
    }

    public void accettaAppuntamento() {
        this.statoOffertaEnum = StatoOffertaEnum.CHIUSA;
        impostaData();

        this.getOffertaAccoppiata().getStatoOfferta().statoOffertaEnum = StatoOffertaEnum.CHIUSA;
        this.altraOfferta.getStatoOfferta()
                .setListaCampiAppuntamento(this.getListaCampiAppuntamento());
        this.getOffertaAccoppiata().getStatoOfferta().impostaData();
    }

    public void proponiAltroAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.altraOfferta
                .getStatoOfferta().setListaCampiAppuntamento(listaCampiAppuntamento);

        this.impostaData();
        this.altraOfferta.getStatoOfferta().impostaData();
    }
}
