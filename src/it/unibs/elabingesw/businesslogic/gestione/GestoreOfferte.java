package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.util.List;

/**
 * Classe GestoreOfferte, sotto-classe della classe GestoreGenerico.
 * <p>
 * Invariante di classe: lo stesso della super-classe.
 * Inoltre il tipo generico T è settato immutabilmente con
 * il tipo Offerta.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GestoreOfferte extends GestoreGenerico<OffertaContext> {
    private static final String FILE_NAME = "Offerte";

    /**
     * Costruttore di classe.
     * aggiungi cosa come :      * Inoltre aggiorna lo stato delle offerte dall'ultimo loro salvataggio.
     * e : E inoltre esegue l'aggiornamento dello stato di tutte
     *      * le offerte in lista.
     *
     */
    // TODO: 27/nov/2022 aggiorna doc, dato che metodo ora aggiorna stato offerte.
    public GestoreOfferte() {
        super(FILE_NAME);
        //aggiornaStatoDelleOfferte();
    }

    /**
     * Metodo che carica, dal file, tutti gli elementi in lista.
     * <p>
     * Post condizione: quella del metodo super chiamata.
     * E inoltre esegue l'aggiornamento dello stato di tutte
     * le offerte in lista.
     */
    // TODO: 27/nov/2022 ora coincidente metodo di superclasse, quindi si toglie.
    /*@Override
    protected void caricaElementi() {
        super.caricaElementi();
    }
*/
    /**
     * Aggiorna stato delle offerte presenti in lista.
     * <p>
     * Precondizione: quella del metodo chiamata su ogni offerta.
     * Post condizione: ogni offerta presente correntemente in lista è aggiornata circa
     * il suo stato rispetto alla logica del sistema.
     */
    public void aggiornaStatoDelleOfferte() {
        this.getListaElementi().forEach(OffertaContext::aggiornaStatoOfferta);
    }

    /**
     * Metodo che permette di salvare le offerte presenti in lista.
     * <p>
     * Precondizione: quella del metodo chiamato.
     * Post condizione: quella del metodo chiamato.
     */
    public void salvaOfferte() {
        salvaDati();
    }

    /**
     * Metodo che controlla se un'offerta relativa a un articolo
     * (il quale nome è passato per parametro) è già presente o me-
     * no.
     * <p>
     * Precondizione: quella del metodo chiamato.
     * Post condizione: quella del metodo chiamato.
     *
     * @param nomeArticolo il nome dell'articolo
     * @return TRUE se l'offerta è già presente
     * FALSE se l'offerta non è già presente
     */
    public boolean isOffertaPresenteByNome(String nomeArticolo) {
        return super.isElementoInListaByNome(nomeArticolo);
    }

    /**
     * Metodo che permette di inserire una nuova offerta.
     * <p>
     * Precondizione: Assumo che il parametro non sia nullo, e
     * che sia correttamente inizializzato. Ovvero che
     * vi sia settato almeno l'attributo nome, e che questo
     * risulti univoco rispetto a tutti gli altri nomi
     * associati alle offerte già presenti in lista.
     * Post condizione: quella del metodo chiamato.
     *
     * @param offertaContext l'oggetto di tipo Offerta
     */
    public void inserisciNuovaOfferta(OffertaContext offertaContext) {
        super.inserisciElemento(offertaContext);
    }

    /**
     * Metodo che ritorna la lista delle offerte di un determinato
     * utente passato per parametro.
     * <p>
     * Precondizione: assumo che il parametro passato
     * non sia nullo e sia inizializzato correttamente. Ovvero
     * che il parametro corrisponda a un utente fruitore già registrato nel sistema.
     * Post condizione: se l'utente non ha ancora fatto alcuna offerta
     * la lista ritornata è vuota. Altrimenti nella lista vengono messe
     * tutte le offerte di cui l'utente, passato come parametro, è autore.
     *
     * @param utente l'oggetto di tipo Utente
     * @return la lista delle offerte
     */
    public List<OffertaContext> getOfferteByUser(Utente utente) {
        return getListaElementi().stream().filter(offerta -> offerta.isStessoAutore(utente)).toList();
    }

    /**
     * Metodo che ritorna la lista delle offerte aperte di un
     * determinato utente passato per parametro.
     * <p>
     * Precondizione: assumo che il parametro passato
     * non sia nullo e sia inizializzato correttamente. Ovvero
     * che il parametro corrisponda a un utente fruitore già registrato nel sistema.
     * Post condizione: se l'utente non ha attualmente alcuna offerta aperta
     * la lista ritornata è vuota. Altrimenti nella lista vengono messe
     * tutte le offerte attualmente aperte di cui l'utente, passato
     * come parametro, è autore.
     *
     * @param utente l'oggetto di tipo Utente
     * @return la lista delle offerte aperte
     */
    public List<OffertaContext> getOfferteAperteByUser(Utente utente) {
        return getOfferteByUser(utente).stream().filter(OffertaContext::isOffertaAperta).toList();
    }

    /**
     * Metodo che ritorna la lista delle offerte aperte di una
     * determinata categoria foglia passata per parametro.
     * <p>
     * Precondizione: assumo che il parametro passato
     * non sia nullo e sia inizializzato correttamente. Ovvero
     * che il parametro corrisponda a una categoria foglia (ovvero terminale) di
     * una gerarchia già registrata nel sistema.
     * Post condizione: se non ci sono correntemente offerte aperte per la
     * categoria foglia passata come parametro la lista ritornata è vuota.
     * Altrimenti nella lista vengono messe tutte le offerte attualmente aperte
     * appartenenti alla medesima categoria foglia passata come parametro.
     *
     * @param categoriaFoglia l'oggetto di tipo Categoria
     * @return la lista delle offerte aperte
     */
    public List<OffertaContext> getOfferteAperteByCategoriaFoglia(Categoria categoriaFoglia) {
        return getOfferteByCategoriaFoglia(categoriaFoglia).stream().filter(OffertaContext::isOffertaAperta).toList();
    }

    /**
     * Metodo che ritorna la lista delle offerte aperte di una
     * determinata categoria foglia passata per parametro escludendo
     * l'utente passato anch'esso per parametro.
     * <p>
     * Precondizione: assumo parametri non nulli e correttamente inizializzati.
     * Ovvero che la categoria foglia sia una categoria terminale appartenente a una
     * gerarchia già registrata nel sistema. E che l'utente passato come parametro
     * sia un utente fruitore già registrato nel sistema.
     * Post condizione: a seconda che esistano attualmente offerte aperte che
     * appartengono alla stessa categoria foglia passata come parametro, e
     * che non siano associate all'utente passato come parametro, queste vengono
     * incluse nella lista che viene ritornata.
     * Altrimenti viene ritornata una lista vuota.
     *
     * @param categoriaFoglia l'oggetto di tipo Categoria
     * @param utente          l'oggetto di tipo Utente
     * @return la lista delle offerte aperte
     */
    public List<OffertaContext> getOfferteAperteByCategoriaFogliaAndExcludeUser(Categoria categoriaFoglia, Utente utente) {
        return getOfferteAperteByCategoriaFoglia(categoriaFoglia).stream().filter(offerta -> !offerta.isStessoAutore(utente)).toList();
    }

    /**
     * Metodo che ritorna la lista delle offerte selezionate di
     * un determinato utente passato per parametro.
     * <p>
     * Precondizione: assumo parametro non nullo e correttamente inizializzato.
     * Ovvero che l'utente passato come parametro
     * sia un utente fruitore già registrato nel sistema.
     * Post condizione: a seconda che esistano attualmente offerte selezionate che
     * siano associate all'utente passato come parametro, queste vengono
     * incluse nella lista che viene ritornata.
     * Altrimenti viene ritornata una lista vuota.
     *
     * @param utente l'oggetto di tipo Utente
     * @return la lista delle offerte selezionate
     */
    public List<OffertaContext> getOfferteSelezionateByUser(Utente utente) {
        return getOfferteByUser(utente).stream().filter(OffertaContext::isOffertaSelezionata).toList();
    }

    /**
     * Metodo che ritorna la lista delle offerte in scambio di
     * un determinato utente passato per parametro.
     * <p>
     * Precondizione: assumo parametro non nullo e correttamente inizializzato.
     * Ovvero che l'utente passato come parametro
     * sia un utente fruitore già registrato nel sistema.
     * Post condizione: a seconda che esistano attualmente offerte in scambio
     * che siano associate all'utente passato come parametro, queste vengono
     * incluse nella lista che viene ritornata.
     * Altrimenti viene ritornata una lista vuota.
     *
     * @param utente l'oggetto di tipo Utente
     * @return la lista delle offerte in scambio
     */
    public List<OffertaContext> getOfferteInScambioByUser(Utente utente) {
        return getOfferteByUser(utente).stream().filter(OffertaContext::isOffertaInScambio).toList();
    }

    /**
     * Metodo che ritorna la lista delle offerte in scambio di
     * una determinata categoria foglia passata per parametro.
     * <p>
     * Precondizione: assumo parametro non nullo e correttamente inizializzato.
     * Ovvero che la categoria foglia sia una categoria terminale appartenente a una
     * gerarchia già registrata nel sistema.
     * Post condizione: a seconda che esistano attualmente offerte in scambio che
     * appartengono alla stessa categoria foglia passata come parametro queste vengono
     * incluse nella lista che viene ritornata.
     * Altrimenti viene ritornata una lista vuota.
     *
     * @param categoriaFoglia l'oggetto di tipo Categoria
     * @return la lista delle offerte in scambio
     */
    public List<OffertaContext> getOfferteInScambioByCategoriaFoglia(Categoria categoriaFoglia) {
        return getOfferteByCategoriaFoglia(categoriaFoglia).stream().filter(OffertaContext::isOffertaInScambio).toList();
    }

    /**
     * Metodo che ritorna la lista di tutte le offerte di
     * una determinata categoria foglia passata per parametro.
     * <p>
     * Precondizione: assumo parametro non nullo e correttamente inizializzato.
     * Ovvero che la categoria foglia sia una categoria terminale appartenente a una
     * gerarchia già registrata nel sistema.
     * Post condizione: a seconda che esistano attualmente offerte in qualsiasi stato che
     * appartengono alla stessa categoria foglia passata come parametro queste vengono
     * incluse nella lista che viene ritornata.
     * Altrimenti viene ritornata una lista vuota.
     *
     * @param categoriaFoglia l'oggetto di tipo Categoria
     * @return la lista delle offerte
     */
    private List<OffertaContext> getOfferteByCategoriaFoglia(Categoria categoriaFoglia) {
        return getListaElementi().stream().filter(offerta -> offerta.appartieneA(categoriaFoglia)).toList();
    }

    /**
     * Metodo che ritorna la lista delle offerte chiuse di una
     * determinata categoria foglia passata per parametro.
     * <p>
     * Precondizione: assumo parametro non nullo e correttamente inizializzato.
     * Ovvero che la categoria foglia sia una categoria terminale appartenente a una
     * gerarchia già registrata nel sistema.
     * Post condizione: a seconda che esistano attualmente offerte chiuse che
     * appartengono alla stessa categoria foglia passata come parametro queste vengono
     * incluse nella lista che viene ritornata.
     * Altrimenti viene ritornata una lista vuota.
     *
     * @param categoriaFoglia l'oggetto di tipo Categoria
     * @return la lista delle offerte chiuse
     */
    public List<OffertaContext> getOfferteChiuseByCategoriaFoglia(Categoria categoriaFoglia) {
        return getOfferteByCategoriaFoglia(categoriaFoglia).stream().filter(OffertaContext::isOffertaChiusa).toList();
    }
}
