package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.gestione.Manageable;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.io.Serializable;

/**
 * Classe Offerta che rappresenta un'offerta generica.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class Offerta implements Manageable, Serializable {
    private final String nomeArticolo;
    private final Utente autore;
    private final Categoria categoriaDiAppartenenza;
    private final ListaCampiCompilati listaCampiCompilati;
    private StatoOfferta statoOfferta;

    /**
     * Costruttore di classe, accetta come parametri il nome dell'
     * articolo, l'autore dell'offerta, la lista dei campi compi-
     * lati e la catgoria di appartenenza dell'articolo.
     *
     * @param nomeArticolo
     * @param autore
     * @param listaCampiCompilati
     * @param categoriaDiAppartenenza
     */ 
    public Offerta(String nomeArticolo, Utente autore, ListaCampiCompilati listaCampiCompilati, Categoria categoriaDiAppartenenza) {
        this.nomeArticolo = nomeArticolo;
        this.autore = autore;
        this.categoriaDiAppartenenza = categoriaDiAppartenenza;
        this.listaCampiCompilati = listaCampiCompilati;
        this.statoOfferta = StatoOfferta.APERTA;
    }
    
    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due articoli hanno lo stesso no-
     * me o meno.
     *
     * @param nomeArticolo il nome dell'articolo
     * @return TRUE se i nomi sono uguali
     *         FALSE se i nomi sono diversi
     */
    @Override
    public boolean isStessoNome(String nomeArticolo) {
        return this.nomeArticolo.equals(nomeArticolo);
    }
    
    /**
     * Metodo che controlla se un'offerta è aperta o meno.
     *
     * @return TRUE se l'offerta è aperta
     *         FALSE se l'offerta non è aperta
     */
    public boolean isOffertaAperta() {
        return this.statoOfferta == StatoOfferta.APERTA;
    }

    /**
     * Metodo che controlla se due autori sono uguali
     * o meno.
     *
     * @param autore l'oggetto di tipo Utente
     * @return TRUE se gli autori sono uguali
     *         FALSE se gli autori sono diversi
     */
    public boolean isStessoAutore(Utente autore) {
        return this.autore.equals(autore);
    }
    
    /**
     * Metodo che ritira un'offerta trasformandone lo 
     * stato in RITIRATA.
     */
    public void ritiraOfferta() {
        this.statoOfferta = StatoOfferta.RITIRATA;
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Offerta{" +
                "nomeArticolo='" + nomeArticolo + '\'' +
                ", autore=" + autore +
                ", listaCampiCompilati=" + listaCampiCompilati +
                ", categoriaDiAppartenenza=" + categoriaDiAppartenenza.toStringRidotto() +
                ", statoOfferta=" + statoOfferta +
                '}';
    }
    
    /**
     * Metodo getter.
     *
     * @return il nome dell'articolo
     */
    public String getNomeArticolo() {
        return nomeArticolo;
    }
    
    /**
     * Metodo che controlla se un articolo appartiene
     * a una categoria passata per parametro.
     *
     * @param categoria l'oggetto di tipo Categoria
     * @return TRUE se l'articolo appartiene alla categoria in input
     *         FALSE se l'articolo non appartiene alla categoria in input
     */
    public boolean appartieneA(Categoria categoria) {
        return this.categoriaDiAppartenenza.equals(categoria);
    }
}
