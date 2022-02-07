package it.unibs.elabingesw.businesslogic.categoria;

import java.util.List;

/**
 * @author Elia
 */
final class CategoriaFiglio extends Categoria {
    public CategoriaFiglio(String nome, String descrizione, List<Campo> campiNativi) {
        super(nome, descrizione, campiNativi);
    }
}
