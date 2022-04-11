package it.unibs.elabingesw.businesslogic.utente;

/**
 * Enumeration UserType che definisce i tipi concreti di utente,
 * ossia Fruitore e Configuratore.
 * <p>
 * Invariante di classe: una istanza concreta di utente deve
 * essere per forza una implementazione o da parte di Configuratore
 * o da parte di Fruitore. Per tanto i tipi che UserType deve tener
 * conto e valutare sono solo quelli per questi due
 * tipi di utenti.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public enum UserType {
    FRUITORE, CONFIGURATORE
}
