package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.List;

/**
 * Classe Campo che definisce un campo di una determinata categoria.
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
     * FALSE se il campo è facoltativo
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campo campo = (Campo) o;

        if (isObbligatorio() != campo.isObbligatorio()) return false;
        return getNome() != null ? getNome().equals(campo.getNome()) : campo.getNome() == null;
    }

    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (isObbligatorio() ? 1 : 0);
        return result;
    }

    @Override
    public boolean isStessoNome(String nome) {
        return this.nome.equals(nome);
    }

    public boolean isCampoInListaByNome(List<Campo> listaCampi) {
        return listaCampi.stream().anyMatch(campoInList -> this.isStessoNome(campoInList.getNome()));
    }

    public boolean isCampoDiDefault() {
        return this.isCampoInListaByNome(Campo.getCampiDiDefaultPerCategoriaRadice());
    }

}
