package it.unibs.elabingesw.service;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;

/**
 * @author Elia
 */
public class MacroService {
    private final GestoreUtenti gestoreUtenti;
    private final GestoreGerarchie gestoreGerarchie;
    private final GerarchiaService gerarchiaService;

    public MacroService(GestoreUtenti gestoreUtenti, GestoreGerarchie gestoreGerarchie) {
        this.gestoreUtenti = gestoreUtenti;
        this.gestoreGerarchie = gestoreGerarchie;
        this.gerarchiaService = new GerarchiaService(this.gestoreGerarchie);
    }

    private void eseguiSalvataggio() {
        gestoreUtenti.salvaUtenti();
        gestoreGerarchie.salvaGerarchie();
    }

    public void creaNuovaGerarchia() {
        this.gerarchiaService.creaNuovaGerarchia();
    }

    public void eseguiProceduraDiUscita() {
        eseguiSalvataggio();
    }

    public void visualizzaGerarchie() {
       this.gerarchiaService.visualizzaGerarchie();

    }
}
