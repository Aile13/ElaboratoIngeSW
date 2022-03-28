package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.util.Optional;

/**
 * @author Elia
 */
public class GestoreScambio extends GestoreGenerico<Scambio> {
    private static final String FILE_NAME = "InfoScambio";

    public GestoreScambio() {
        super(FILE_NAME);
    }

    public boolean isInfoScambioDaConfigurare() {
        return this.getListaElementi().isEmpty();
    }

    public void salvaInfoScambio() {
        salvaDati();
    }

    public void impostaInfoDiScambio(Scambio scambio) {
        this.inserisciElemento(scambio);
    }

    public Optional<Scambio> getInfoDiScambio() {
        return this.getListaElementi().stream().findFirst();
    }
}
