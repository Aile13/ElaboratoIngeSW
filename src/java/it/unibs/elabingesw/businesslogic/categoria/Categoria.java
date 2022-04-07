package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Categoria che rappresenta una categoria generica.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class Categoria implements Manageable, Serializable {
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
        return "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", campiNativi=" + campiNativi;
    }

    /**
     * Metodo che permette di confrontare due oggetti.
     *
     * @param o un oggetto generico
     * @return TRUE se i due oggetti sono uguali
     * FALSE se i due oggetti sono diversi
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        return getNome().equals(categoria.getNome());
    }

    /**
     * Metodo che fornisce il codice hash dell'oggetto.
     *
     * @return l'hashcode dell'oggetto gerarchia
     */
    @Override
    public int hashCode() {
        return getNome().hashCode();
    }

    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due gerarchie hanno lo stesso no-
     * me o meno.
     *
     * @param nome il nome della gerarchia
     * @return TRUE se i nomi sono uguali
     * FALSE se i nomi sono diversi
     */
    @Override
    public boolean isStessoNome(String nome) {
        return this.getNome().equals(nome);
    }

    /**
     * Metodo toString ridotto in cui mostro a video
     * solo il nome e la descrizione della categoria.
     *
     * @return stringa dell'oggetto convertito
     */
    public String toStringRidotto() {
        return "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'';
    }

    public boolean isCampoGiaPreso(Campo campo) {
        return campo.isCampoInListaByNome(campiNativi);
    }
}
