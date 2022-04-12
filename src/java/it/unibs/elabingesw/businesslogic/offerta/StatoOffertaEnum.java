package it.unibs.elabingesw.businesslogic.offerta;

/**
 * Enumeration StatoOffertaEnum che definisce i possibili
 * stati in cui si può trovare un'offerta.
 * <p>
 * Invariante di classe: una istanza di offerta deve
 * essere sempre per forza associata a uno stato.
 * Gli stati possibili in cui una offerta può trovarsi sono
 * tutti e soli quelli enunciati nel Enumeration StatoOffertaEnum.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public enum StatoOffertaEnum {
    APERTA,
    ACCOPPIATA,
    SELEZIONATA,
    IN_SCAMBIO,
    CHIUSA,
    RITIRATA;
}
