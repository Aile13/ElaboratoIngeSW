package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.util.Optional;

/**
 * @author Elia
 */
public interface ScambioRepository {
    boolean isInfoScambioDaConfigurare();

    void salvaInfoScambio();

    void impostaInfoDiScambio(Scambio scambio);

    Optional<Scambio> getInfoDiScambio();
}
