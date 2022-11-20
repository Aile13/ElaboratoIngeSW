package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Elia
 */
//todo fare javadoc a livello di interface e di singoli metodi, puoi riusare eventualmente quelli che c'erano in StatoOfferta o simili.
public interface OffertaState extends Serializable, DomainTypeToRender {
    OffertaContext getOffertaAssociata(OffertaContext context); // fatto

    ListaCampiCompilati getListaCampiAppuntamento(OffertaContext context); // fatto

    void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento); // fatto

    boolean isOffertaAperta(OffertaContext context); /// fatto

    void ritiraOfferta(OffertaContext context); // fatto.

    void creaLegameEModificaStatiConOffertaEInfoScambio(OffertaContext context, OffertaContext offertaContextDaBarattareB, Scambio infoDiScambio);  // fatto

    void aggiornaStatoOfferta(OffertaContext context);  // fatto, poi questo è da togliere.

    boolean isOffertaSelezionata(OffertaContext context); /// fatto

    void accettaPropostaDiScambioAssociata(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento);  // fatto

    boolean isOffertaInScambio(OffertaContext context); // fatto

    void accettaAppuntamento(OffertaContext context); // fatto

    void proponiAltroAppuntamento(OffertaContext context, ListaCampiCompilati listaCampiAppuntamento); // fatto

    boolean isOffertaChiusa(OffertaContext context);  // fatto

    void impostaData(); // fatto
}
