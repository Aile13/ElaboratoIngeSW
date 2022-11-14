package it.unibs.elabingesw.businesslogic.offerta.state;

import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.businesslogic.offerta.OffertaState;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;

/**
 * @author Elia
 */
public class RitirataState implements OffertaState {
    @Override
    public OffertaContext getOffertaAssociata(OffertaContext context) {
        return null;
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
        return "RitirataState{}";
    }
}
