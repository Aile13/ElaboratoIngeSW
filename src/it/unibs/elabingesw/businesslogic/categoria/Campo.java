package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;
import java.util.List;

/**
 * Record Campo che definisce un campo di una determinata categoria.
 * <p>
 * L'invariante di classe è che gli attributi del record Campo: nome e obbligatorio
 * una volta settati con la creazione dell'istanza rimangano immutati
 * per tutto il ciclo di vita dell'oggetto.
 * <p>
 * Precondizione del costruttore: gli argomenti passati non siano nulli.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public record Campo(String nome, boolean obbligatorio) implements Manageable, Serializable, DomainTypeToRender {

    /**
     * Metodo che ritorna la lista dei campi nativi che una categoria
     * radice deve avere che possono essere a compilazione obbligatoria
     * o facoltativa.
     *
     * @return lista dei campi nativi necessari a una categoria radice
     */
    public static List<Campo> getCampiDiDefaultPerCategoriaRadice() {
        return List.of(new Campo("Stato di conservazione", true), new Campo("Descrizione libera", false));
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    //todo, per tempo, si usa quello integrato di Record, poi si aggiorna di conseguenza l'output delll'app per approval.
    @Override
    public String toString() {
        return "Campo{" + "nome='" + nome + '\'' + ", obbligatorio=" + obbligatorio + '}';
    }

    /**
     * Metodo per controllare se un campo è obbligatorio o meno.
     *
     * @return TRUE se il campo è obbligatorio
     * FALSE se il campo è facoltativo
     */
    //todo da rimuovere, si usa quello integrato di Record
    /*public boolean obbligatorio() {
        return this.obbligatorio;
    }*/

    /**
     * Metodo getter.
     *
     * @return il nome del campo
     */
    //todo da rimuovere, si usa quello integrato di Record
    /*public String nome() {
        return this.nome;
    }*/

    /**
     * Metodo che permette di confrontare due oggetti.
     *
     * @param o un oggetto generico
     * @return TRUE se i due oggetti sono uguali
     * FALSE se i due oggetti sono diversi
     */
    //todo da rimuovere, si usa quello integrato di Record
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campo campo = (Campo) o;

        if (obbligatorio() != campo.obbligatorio()) return false;
        return nome() != null ? nome().equals(campo.nome()) : campo.nome() == null;
    }*/

    /**
     * Metodo che fornisce il codice hash dell'oggetto.
     *
     * @return l'hashcode dell'oggetto campo
     */
    //todo da rimuovere, si usa quello integrato di Record
    /*@Override
    public int hashCode() {
        int result = nome() != null ? nome().hashCode() : 0;
        result = 31 * result + (obbligatorio() ? 1 : 0);
        return result;
    }*/

    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due campi hanno lo stesso no-
     * me o meno.
     *
     * @param nome il nome del campo
     * @return TRUE se i nomi sono uguali
     * FALSE se i nomi sono diversi
     */
    @Override
    public boolean isStessoNome(String nome) {
        return this.nome.equals(nome);
    }

    /**
     * Metodo che controlla se un campo è in lista
     * passata per parametro.
     * <p>
     * Precondizione: argomento del metodo non nullo.
     *
     * @param listaCampi la lista dei campi
     * @return TRUE se il campo è in lista
     * FALSE se il campo non è in lista
     */
    public boolean isCampoInListaByNome(List<Campo> listaCampi) {
        return listaCampi.stream().anyMatch(campoInList -> this.isStessoNome(campoInList.nome()));
    }

    /**
     * Metodo che controlla se un campo è di default o meno.
     *
     * @return TRUE se il campo è di default
     * FALSE se il campo non è di default
     */
    public boolean isCampoDiDefault() {
        return this.isCampoInListaByNome(Campo.getCampiDiDefaultPerCategoriaRadice());
    }

}
