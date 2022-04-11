package it.unibs.elabingesw.businesslogic.offerta;

import java.io.Serializable;

/**
 * Enumeration StatoOfferta che definisce i due possibili
 * stati in cui si può trovare un'offerta.
 * <p>
 * Invariante di classe: una istanza di offerta deve
 * essere sempre per forza associata a uno stato.
 * Gli stati possibili in cui una offerta può trovarsi sono
 * tutti e soli quelli enunciati nel Enumeration StatoOfferta.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
enum StatoOfferta implements Serializable {
    APERTA, RITIRATA
}
