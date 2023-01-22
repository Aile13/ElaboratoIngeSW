package it.unibs.elabingesw.businesslogic.repository;

import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;

import java.util.List;

/**
 * Interfaccia GerarchiaRepository con metodi appositi
 * per gestire operazioni su collezioni di gerarchie.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public interface GerarchiaRepository {
    
    /**
     * Metodo getter.
     *
     * @return la lista delle gerarchie
     */
    List<GerarchiaDiCategorie> getListaGerarchie();

    /**
     * Metodo che permette di salvare in maniera persistente
     * le gerarchie presenti in lista.
     */
    void salvaGerarchie();

    /**
     * Metodo che inserisce una nuova gerarchia nella lista di gerarchie.
     *
     * @param gerarchiaDiCategorie oggetto di tipo GerarchiaDiCategorie
     */
    void inserisciNuovaGerarchia(GerarchiaDiCategorie gerarchiaDiCategorie);

    /**
     * Metodo che controlla se una gerarchia il cui nome è
     * passato per parametro è presente in lista o meno.
     *
     * @param nomeGerarchia il nome della gerarchia
     * @return TRUE la gerarchie è presente in lista
     * FALSE la gerarchia non è presente in lista
     */
    boolean isGerarchiaPresenteByNome(String nomeGerarchia);

    /**
     * Metodo che controlla se la lista delle gerarchie
     * contiene almeno una gerarchia.
     *
     * @return TRUE se la lista delle gerarchie non è vuota
     * FALSE se la lista delle gerarchie è vuota
     */
    boolean haGerarchie();
}
