package it.unibs.elabingesw.businesslogic.categoria;

import java.util.List;

/**
 * Classe Categoria che rappresenta una categoria generica.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class Categoria {
    private final String nome;
    private final String descrizione;
    private final List<Campo> campiNativi;
    
    /**
     * Costruttore di classe, accetta come parametri il nome, la
     * descrizione e la lista dei campi nativi di una categoria.
     *
     * @param nome
     * @param descrizione
     * @param campiNativi
     */ 
    public Categoria(String nome, String descrizione, List<Campo> campiNativi) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.campiNativi = campiNativi;
    }
    
    /**
     * Metodo getter.
     *
     * @return il nome della categoria
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Metodo che permette all'utente di inserire una lista di
     * campi nativi per una determinata categoria.
     * 
     * @param campi la lista dei campi
     */
    public void inserisciCampiNativi(List<Campo> campi) {
        this.campiNativi.addAll(campi);
    }
    
    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", campiNativi=" + campiNativi +
                '}';
    }
}
