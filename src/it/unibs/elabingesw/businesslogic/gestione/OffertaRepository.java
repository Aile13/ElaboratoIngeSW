package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.util.List;

/**
 * @author Elia
 */
public interface OffertaRepository {
    void aggiornaStatoDelleOfferte();

    void salvaOfferte();

    boolean isOffertaPresenteByNome(String nomeArticolo);

    void inserisciNuovaOfferta(OffertaContext offertaContext);

    List<OffertaContext> getOfferteByUser(Utente utente);

    List<OffertaContext> getOfferteAperteByUser(Utente utente);

    List<OffertaContext> getOfferteAperteByCategoriaFoglia(Categoria categoriaFoglia);

    List<OffertaContext> getOfferteAperteByCategoriaFogliaAndExcludeUser(Categoria categoriaFoglia, Utente utente);

    List<OffertaContext> getOfferteSelezionateByUser(Utente utente);

    List<OffertaContext> getOfferteInScambioByUser(Utente utente);

    List<OffertaContext> getOfferteInScambioByCategoriaFoglia(Categoria categoriaFoglia);

    List<OffertaContext> getOfferteByCategoriaFoglia(Categoria categoriaFoglia);

    List<OffertaContext> getOfferteChiuseByCategoriaFoglia(Categoria categoriaFoglia);
}
