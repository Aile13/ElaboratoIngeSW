package it.unibs.elabingesw.businesslogic.repository;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.util.List;

/**
 * @author Elia
 */
public interface OffertaRepository {
    
    /**
     * Metodo che aggiorna stato delle offerte presenti in lista.
     */
    void aggiornaStatoDelleOfferte();

    /**
     * Metodo che permette di salvare le offerte presenti in lista.
     */
    void salvaOfferte();

    /**
     * Metodo che controlla se un'offerta relativa a un articolo
     * (il quale nome è passato per parametro) è già presente o me-
     * no.
     *
     * @param nomeArticolo il nome dell'articolo
     * @return TRUE se l'offerta è già presente
     * FALSE se l'offerta non è già presente
     */
    boolean isOffertaPresenteByNome(String nomeArticolo);

    /**
     * Metodo che permette di inserire una nuova offerta.
     *
     * @param offertaContext oggetto di tipo OffertaContext
     */
    void inserisciNuovaOfferta(OffertaContext offertaContext);

    /**
     * Metodo che ritorna la lista delle offerte di un determinato
     * utente passato per parametro.
     *
     * @param utente oggetto di tipo Utente
     * @return la lista delle offerte
     */
    List<OffertaContext> getOfferteByUser(Utente utente);

    /**
     * Metodo che ritorna la lista delle offerte aperte di un
     * determinato utente passato per parametro.
     *
     * @param utente oggetto di tipo Utente
     * @return la lista delle offerte aperte
     */
    List<OffertaContext> getOfferteAperteByUser(Utente utente);

    /**
     * Metodo che ritorna la lista delle offerte aperte di una
     * determinata categoria foglia passata per parametro.
     *
     * @param categoriaFoglia oggetto di tipo Categoria
     * @return la lista delle offerte aperte
     */
    List<OffertaContext> getOfferteAperteByCategoriaFoglia(Categoria categoriaFoglia);

    /**
     * Metodo che ritorna la lista delle offerte aperte di una
     * determinata categoria foglia passata per parametro escludendo
     * l'utente passato anch'esso per parametro.
     *
     * @param categoriaFoglia oggetto di tipo Categoria
     * @param utente oggetto di tipo Utente
     * @return la lista delle offerte aperte
     */
    List<OffertaContext> getOfferteAperteByCategoriaFogliaAndExcludeUser(Categoria categoriaFoglia, Utente utente);

    /**
     * Metodo che ritorna la lista delle offerte selezionate di
     * un determinato utente passato per parametro.
     *
     * @param utente oggetto di tipo Utente
     * @return la lista delle offerte selezionate
     */
    List<OffertaContext> getOfferteSelezionateByUser(Utente utente);

    /**
     * Metodo che ritorna la lista delle offerte in scambio di
     * un determinato utente passato per parametro.
     *
     * @param utente oggetto di tipo Utente
     * @return la lista delle offerte in scambio
     */
    List<OffertaContext> getOfferteInScambioByUser(Utente utente);

    /**
     * Metodo che ritorna la lista delle offerte in scambio di
     * una determinata categoria foglia passata per parametro.
     *
     * @param categoriaFoglia oggetto di tipo Categoria
     * @return la lista delle offerte in scambio
     */
    List<OffertaContext> getOfferteInScambioByCategoriaFoglia(Categoria categoriaFoglia);

    /**
     * Metodo che ritorna la lista di tutte le offerte di
     * una determinata categoria foglia passata per parametro.
     *
     * @param categoriaFoglia oggetto di tipo Categoria
     * @return la lista delle offerte
     */
    List<OffertaContext> getOfferteByCategoriaFoglia(Categoria categoriaFoglia);

    /**
     * Metodo che ritorna la lista delle offerte chiuse di una
     * determinata categoria foglia passata per parametro.
     *
     * @param categoriaFoglia oggetto di tipo Categoria
     * @return la lista delle offerte chiuse
     */
    List<OffertaContext> getOfferteChiuseByCategoriaFoglia(Categoria categoriaFoglia);
}
