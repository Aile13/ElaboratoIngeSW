package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.offerta.Offerta;
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
public class GestoreOfferte extends GestoreGenerico<Offerta> {
    private static final String FILE_NAME = "Offerte";

    /**
     * Costruttore di classe.
     */
    public GestoreOfferte() {
        super(FILE_NAME);
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
    public boolean isOffertaGiaPresenteByNome(String nomeArticolo) {
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
     * @param offerta l'oggetto di tipo Offerta
     */
    public void inserisciNuovaOfferta(Offerta offerta) {
        super.inserisciElemento(offerta);
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
    public List<Offerta> getOfferteByUser(Utente utente) {
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
    public List<Offerta> getOfferteAperteByUser(Utente utente) {
        return getOfferteByUser(utente).stream().filter(Offerta::isOffertaAperta).toList();
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
    public List<Offerta> getOfferteAperteByCategoriaFoglia(Categoria categoriaFoglia) {
        return getListaElementi().stream().filter(offerta -> offerta.isOffertaAperta() && offerta.appartieneA(categoriaFoglia)).toList();
    }
}
