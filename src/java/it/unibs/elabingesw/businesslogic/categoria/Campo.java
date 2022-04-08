package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.List;

/**
 * Record Campo che definisce un campo di una determinata categoria.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public record Campo(String nome, boolean obbligatorio) implements Manageable, Serializable {
    
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
     * @return l'hashcode dell'oggetto campo
     */
    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (isObbligatorio() ? 1 : 0);
        return result;
    }

    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due campi hanno lo stesso no-
     * me o meno.
     *
     * @param nome il nome del campo
     * @return TRUE se i nomi sono uguali
     *         FALSE se i nomi sono diversi
     */
    @Override
    public boolean isStessoNome(String nome) {
        return this.nome.equals(nome);
    }

    /**
     * Metodo che controlla se un campo è in lista
     * passata per parametro.
     *
     * @param listaCampi la lista dei campi
     * @return TRUE se il campo è in lista 
     *         FALSE se il campo non è in lista
     */
    public boolean isCampoInListaByNome(List<Campo> listaCampi) {
        return listaCampi.stream().anyMatch(campoInList -> this.isStessoNome(campoInList.getNome()));
    }

    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due gerarchie hanno lo stesso no-
     * me o meno.
     *
     * @return TRUE se il campo è di default
     *         FALSE se il campo non è di default
     */
    public boolean isCampoDiDefault() {
        return this.isCampoInListaByNome(Campo.getCampiDiDefaultPerCategoriaRadice());
    }

}
