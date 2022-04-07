package it.unibs.elabingesw.businesslogic.categoria;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Campo che definisce un campo di una determinata categoria.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class Campo implements Serializable {
    private String nome;
    private boolean obbligatorio;
    
    /**
     * Costruttore di classe, accetta come parametri il nome del cam-
     * po e se quest'ultimo è obbligatorio o meno.
     * 
     * @param nome
     * @param obbligatorio
     */
    public Campo(String nome, boolean obbligatorio) {
        this.nome = nome;
        this.obbligatorio = obbligatorio;
    }
    
    /**
     * Metodo che ritorna la lista dei campi nativi che una catego-
     * ria deve avere che possono essere a compilazione obbligatoria
     * o facoltativa.
     *
     * @return lista dei campi nativi necessari a una categoria
     */
    public static List<Campo> getCampiDiDefaultPerCategoriaRadice() {
        return List.of(
                new Campo("Stato di conservazione", true),
                new Campo("Descrizione libera", false)
        );
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Campo{" +
                "nome='" + nome + '\'' +
                ", obbligatorio=" + obbligatorio +
                '}';
    }
    
    /**
     * Metodo per controllare se un campo è obbligatorio o meno.
     *
     * @return TRUE se il campo è obbligatorio
     *         FALSE se il campo è facoltativo
     */
    public boolean isObbligatorio() {
        return this.obbligatorio;
    }
    
    /**
     * Metodo getter.
     *
     * @return il nome del campo
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo che permette di confrontare due oggetti.
     *
     * @param o un oggetto generico
     * @return TRUE se i due oggetti sono uguali
     *         FALSE se i due oggetti sono diversi
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campo campo = (Campo) o;

        if (isObbligatorio() != campo.isObbligatorio()) return false;
        return getNome() != null ? getNome().equals(campo.getNome()) : campo.getNome() == null;
    }

    /**
     * Metodo che fornisce il codice hash dell'oggetto.
     *
     * @return l'hashcode dell'oggetto gerarchia
     */
    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (isObbligatorio() ? 1 : 0);
        return result;
    }
}
