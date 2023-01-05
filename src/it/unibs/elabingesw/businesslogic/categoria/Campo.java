package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.repository.Manageable;

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
