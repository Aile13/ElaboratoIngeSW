package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

/**
 * @author Elia
 */
public class GestoreScambio extends GestoreGenerico<Scambio> {
    private static final String FILE_NAME = "InfoScambio";

    public GestoreScambio() {
        super(FILE_NAME);
    }

    public void salvaInfoScambio() {
        salvaDati();
    }
}
