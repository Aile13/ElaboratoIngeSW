package it.unibs.elabingesw.businesslogic.categoria;

import java.util.List;

/**
 * @author Elia
 */
abstract class Categoria {
    private final String nome;
    private final String descrizione;
    private final List<Campo> campiNativi;

    public Categoria(String nome, String descrizione, List<Campo> campiNativi) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.campiNativi = campiNativi;
    }
}
