package it.unibs.elabingesw.businesslogic.categoria;

import java.util.List;

/**
 * @author Elia
 */
public final class CategoriaRadice extends Categoria {

    public CategoriaRadice(String nome, String descrizione, List<Campo> campiNativi) {
        super(nome, descrizione, campiNativi);
        this.inserisciCampiNativi(Campo.getCampiDiDefault());
    }
}
