package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.offerta.Offerta;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.util.List;

/**
 * Classe GestoreOfferte, figlia della classe GestoreGenerico.
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
     * Metodo che permette di salvare le offerte inserite.
     */
    public void salvaOfferte() {
        salvaDati();
    }

    /**
     * Metodo che controlla se un'offerta relativa ad un articolo
     * (il quale nome è passato per parametro) è già presente o me-
     * no.
     *
     * @param nomeArticolo il nome dell'articolo
     * @return TRUE se l'offerta è già presente
     *         FALSE se l'offerta non è già presente
     */
    public boolean isOffertaGiaPresenteByNome(String nomeArticolo) {
        return super.isElementoInListaByNome(nomeArticolo);
    }
    
    /**
     * Metodo che permette di inserire una nuova offerta.
     *
     * @param offerta l'oggetto di tipo Offerta
     */
    public void inserisciNuovaOfferta(Offerta offerta) {
        super.inserisciElemento(offerta);
    }

    /**
     * Metodo che ritorna la lista delle offerte di un determinato
     * utente passato per parametro.
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
     *
     * @param categoriaFoglia l'oggetto di tipo Categoria
     * @return la lista delle offerte aperte
     */
    public List<Offerta> getOfferteAperteByCategoriaFoglia(Categoria categoriaFoglia) {
        return getListaElementi().stream()
                .filter(
                        offerta -> offerta.isOffertaAperta() && offerta.appartieneA(categoriaFoglia)
                ).toList();
    }
}
