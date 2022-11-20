package it.unibs.elabingesw.businesslogic.offerta.state;

import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.businesslogic.offerta.OffertaState;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.time.LocalDate;

/**
 * @author Elia
 */
public class InScambioState implements OffertaState {
    private final OffertaContext altraOffertaContextDaBarattare;
    private ListaCampiCompilati listaCampiAppuntamento;
    private final Scambio infoDiScambio;
    private LocalDate dataCreazioneStato;

    public InScambioState(OffertaContext altraOffertaContextDaBarattare, Scambio infoDiScambio) {
        this.altraOffertaContextDaBarattare = altraOffertaContextDaBarattare;
        this.infoDiScambio = infoDiScambio;
        listaCampiAppuntamento = null;
        dataCreazioneStato = LocalDate.now();
    }

    public InScambioState(OffertaContext altraOffertaContextDaBarattare, ListaCampiCompilati listaCampiAppuntamento, Scambio infoDiScambio) {
        this.altraOffertaContextDaBarattare = altraOffertaContextDaBarattare;
        this.listaCampiAppuntamento = listaCampiAppuntamento;
        this.infoDiScambio = infoDiScambio;
        dataCreazioneStato = LocalDate.now();
    }

    @Override
    public OffertaContext getOffertaAssociata(OffertaContext context) {
        return altraOffertaContextDaBarattare;
    }

    @Override
    public ListaCampiCompilati getListaCampiAppuntamento(OffertaContext context) {
        return this.listaCampiAppuntamento;
    }

    @Override
    public void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.listaCampiAppuntamento = listaCampiAppuntamento;
    }

    @Override
    public boolean isOffertaAperta(OffertaContext context) {
        return false;
    }

    @Override
    public void ritiraOfferta(OffertaContext context) {

    }

    @Override
    public void creaLegameEModificaStatiConOffertaEInfoScambio(OffertaContext context, OffertaContext offertaContextDaBarattareB, Scambio infoDiScambio) {

    }

    @Override
    public void aggiornaStatoOfferta(OffertaContext context) {
        if (LocalDate.now().isAfter(dataCreazioneStato.plusDays(infoDiScambio.scadenza()))) {
            context.setOffertaState(new ApertaState());
        }
    }

    @Override
    public boolean isOffertaSelezionata(OffertaContext context) {
        return false;
    }

    @Override
    public void accettaPropostaDiScambioAssociata(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento) {

    }

    @Override
    public boolean isOffertaInScambio(OffertaContext context) {
        return true;
    }

    @Override
    public void accettaAppuntamento(OffertaContext context) {
        context.setOffertaState(new ChiusaState());
        altraOffertaContextDaBarattare.setOffertaState(new ChiusaState());
        altraOffertaContextDaBarattare.getOffertaState().setListaCampiAppuntamento(getListaCampiAppuntamento(context));
    }

    @Override
    public String toString() {
        return "InScambioState{}";
    }

    @Override
    public void proponiAltroAppuntamento(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento) {
        altraOffertaContextDaBarattare.getOffertaState().setListaCampiAppuntamento(listaCampiAppuntamento);
        this.setListaCampiAppuntamento(null);
        dataCreazioneStato = LocalDate.now();
        altraOffertaContextDaBarattare.getOffertaState().impostaData();
    }

    @Override
    public boolean isOffertaChiusa(OffertaContext context) {
        return false;
    }

    @Override
    public void impostaData() {
        dataCreazioneStato = LocalDate.now();
    }
}
