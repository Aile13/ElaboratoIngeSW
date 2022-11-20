package it.unibs.elabingesw.businesslogic.offerta.state;

import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.businesslogic.offerta.OffertaState;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.time.LocalDate;

/**
 * @author Elia
 */
public class AccoppiataState implements OffertaState {
    private final OffertaContext altraOffertaContextDaBarattareB;
    private final Scambio infoDiScambio;
    private final LocalDate dataCreazioneStato;

    public AccoppiataState(OffertaContext altraOffertaContextDaBarattareB, Scambio infoDiScambio) {
        this.altraOffertaContextDaBarattareB = altraOffertaContextDaBarattareB;
        this.infoDiScambio = infoDiScambio;
        dataCreazioneStato = LocalDate.now();
    }

    @Override
    public OffertaContext getOffertaAssociata(OffertaContext context) {
        return altraOffertaContextDaBarattareB;
    }

    @Override
    public ListaCampiCompilati getListaCampiAppuntamento(OffertaContext context) {
        return null;
    }

    @Override
    public void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {

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
        return false;
    }

    @Override
    public void accettaAppuntamento(OffertaContext context) {

    }

    @Override
    public void proponiAltroAppuntamento(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento) {

    }

    @Override
    public boolean isOffertaChiusa(OffertaContext context) {
        return false;
    }

    @Override
    public void impostaData() {

    }

    @Override
    public String toString() {
        return "AccoppiataState{}";
    }
}
