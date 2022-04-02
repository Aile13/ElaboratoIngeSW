package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Elia
 */
class StatoOfferta implements Serializable {

    private StatoOffertaEnum statoOffertaEnum;
    private LocalDate dataCreazioneLegame;
    private Offerta altraOfferta;

    private Scambio scambio;

    public StatoOfferta() {
        this.statoOffertaEnum = StatoOffertaEnum.APERTA;
    }

    public void setScambio(Scambio scambio) {
        this.scambio = scambio;
    }

    public boolean isAperta() {
        return this.statoOffertaEnum == StatoOffertaEnum.APERTA;
    }

    public void ritiraOfferta() {
        this.statoOffertaEnum = StatoOffertaEnum.RITIRATA;
    }

    public void setOffertaAccoppiataCon(Offerta offertaDaBarattareB) {
        this.statoOffertaEnum = StatoOffertaEnum.ACCOPPIATA;
        this.altraOfferta = offertaDaBarattareB;
        this.dataCreazioneLegame = LocalDate.now();
    }

    public void setOffertaSelezionataCon(Offerta offertaDaBarattareA) {
        this.statoOffertaEnum = StatoOffertaEnum.SELEZIONATA;
        this.altraOfferta = offertaDaBarattareA;
        this.dataCreazioneLegame = LocalDate.now();
    }

    public void aggiornaStatoOfferta() {
        if (statoOffertaEnum == StatoOffertaEnum.ACCOPPIATA
                || statoOffertaEnum == StatoOffertaEnum.SELEZIONATA) {
            if (dataCreazioneLegame.isAfter(LocalDate.now().plusDays(scambio.getScadenza()))) {
                this.statoOffertaEnum = StatoOffertaEnum.APERTA;
                this.altraOfferta = null;
            }
        }
    }

    @Override
    public String toString() {
        return "StatoOfferta{" +
                "statoOffertaEnum=" + statoOffertaEnum +
                '}';
    }
}
