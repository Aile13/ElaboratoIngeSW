package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Elia
 */
//todo Laaraj fare javadoc a livello di interface e di singoli metodi, puoi riusare eventualmente quelli che c'erano in StatoOfferta o simili.
public interface OffertaState extends Serializable, DomainTypeToRender {
    OffertaContext getOffertaAssociata(OffertaContext context);

    ListaCampiCompilati getListaCampiAppuntamento(OffertaContext context);

    void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento);

    boolean isOffertaAperta(OffertaContext context);

    void ritiraOfferta(OffertaContext context);

    void creaLegameEModificaStatiConOffertaEInfoScambio(OffertaContext context, OffertaContext offertaContextDaBarattareB, Scambio infoDiScambio);

    void aggiornaStatoOfferta(OffertaContext context);

    boolean isOffertaSelezionata(OffertaContext context);

    void accettaPropostaDiScambioAssociata(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento);

    boolean isOffertaInScambio(OffertaContext context);

    void accettaAppuntamento(OffertaContext context);

    void proponiAltroAppuntamento(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento);

    boolean isOffertaChiusa(OffertaContext context);

    void impostaData();
}
