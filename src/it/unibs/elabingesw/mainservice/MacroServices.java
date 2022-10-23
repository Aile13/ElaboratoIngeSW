package it.unibs.elabingesw.mainservice;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreOfferte;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.elabingesw.subservice.GerarchiaService;
import it.unibs.elabingesw.subservice.OfferteService;
import it.unibs.elabingesw.subservice.ScambioService;

/**
 * Classe MacroServices di gestione generale.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class MacroServices {
    private final GestoreUtenti gestoreUtenti;
    private final GestoreGerarchie gestoreGerarchie;
    private final GestoreScambio gestoreScambio;
    private final GestoreOfferte gestoreOfferte;

    private final GerarchiaService gerarchiaService;
    private final ScambioService scambioService;
    private final OfferteService offerteService;

    /**
     * Costruttore di classe, accetta come parametri un oggetto di
     * tipo GestoreUtenti e un oggetto di tipo GestoreGerarchie.
     *
     * @param gestoreUtenti oggetto di tipo GestoreUtenti
     * @param gestoreGerarchie oggetto di tipo GestoreGerarchie
     * @param gestoreScambio oggetto di tipo GestoreScambio
     * @param gestoreOfferte oggetto di tipo GestoreOfferte
     *
     * @see GestoreUtenti
     * @see GestoreGerarchie
     * @see GestoreScambio
     * @see GestoreOfferte
     */
    public MacroServices(GestoreUtenti gestoreUtenti, GestoreGerarchie gestoreGerarchie, GestoreScambio gestoreScambio, GestoreOfferte gestoreOfferte) {
        this.gestoreUtenti = gestoreUtenti;
        this.gestoreGerarchie = gestoreGerarchie;
        this.gestoreScambio = gestoreScambio;
        this.gestoreOfferte = gestoreOfferte;

        this.gerarchiaService = new GerarchiaService(this.gestoreGerarchie, this.gestoreScambio);
        this.scambioService = new ScambioService(this.gestoreScambio);
        this.offerteService = new OfferteService(this.gestoreOfferte, this.gestoreGerarchie, this.gestoreScambio);
    }

    /**
     * Metodo che salva utenti e gerarchie su file.
     *
     * @see GestoreUtenti
     * @see GestoreGerarchie
     */
    private void eseguiSalvataggio() {
        gestoreUtenti.salvaUtenti();
        gestoreGerarchie.salvaGerarchie();
        gestoreScambio.salvaInfoScambio();
        gestoreOfferte.salvaOfferte();
    }

    /**
     * Metodo che rimanda alla classe GerarchiaService per
     * creare una nuova gerarchia.
     *
     * @see GerarchiaService
     */
    public void creaNuovaGerarchia() {
        this.gerarchiaService.creaNuovaGerarchia();
    }

    /**
     * Metodo che esegue la procedura di uscita dall'appli-
     * cativo dopo aver salvato i dati inseriti.
     */
    public void eseguiProceduraDiUscita() {
        eseguiSalvataggio();
    }

    /**
     * Metodo che rimanda alla classe GerarchiaService per
     * visualizzare le tutte gerarchie caricate.
     *
     * @see GerarchiaService
     */
    public void visualizzaGerarchieFormaEstesa() {
        this.gerarchiaService.visualizzaGerarchieInFormaEstesa();
    }

    /**
     * Metodo che rimanda alla classe GerarchiaService per
     * visualizzare tutte le gerarchie caricate in forma
     * ridotta.
     *
     * @see GerarchiaService
     */
    public void visualizzaGerarchieFormaRidotta() {
        this.gerarchiaService.visualizzaGerarchieInFormaRidotta();
    }

    /**
     * Metodo che rimanda alla classe ScambioService per
     * visualizzare tutte le informazioni degli scambi.
     *
     * @see ScambioService
     */
    public void visualizzaInfoDiScambioFormaEstesa() {
        this.scambioService.visualizzaInfoDiScambioFormaEstesa();
    }

    /**
     * Metodo che rimanda alla classe ScambioService per
     * visualizzaretutte le informazioni degli scambi in
     * forma ridotta.
     *
     * @see ScambioService
     */
    public void visualizzaInfoDiScambioFormaRidotta() {
        this.scambioService.visualizzaInfoDiScambioFormaRidotta();
    }

    /**
     * Metodo che rimanda alla classe ScambioService per
     * impostare le informazioni sugli scambi.
     *
     * @see ScambioService
     */
    public void impostaInfoDiScambio() {
        this.scambioService.impostaInfoScambio();
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * settare l'utente loggato.
     *
     * @param userLogged l'utente loggato
     * @see OfferteService
     */
    public void setUser(Utente userLogged) {
        this.offerteService.setUser(userLogged);
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * visualizzare le offerte di un utente.
     *
     * @see OfferteService
     */
    public void visualizzaOfferteUtente() {
        this.offerteService.visualizzaOfferteUtente();
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * creare una nuova offerta.
     *
     * @see OfferteService
     */
    public void creaNuovaOfferta() {
        this.offerteService.creaNuovaOfferta();
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * ritirare le offerte di un utente.
     *
     * @see offerteService
     */
    public void ritiraOfferte() {
        this.offerteService.ritiraOfferte();
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * visualizzare le offerte aperte con la selezione
     * della categoria foglia.
     *
     * @see OfferteService
     */
    public void visualizzaOfferteAperteConSelezioneFoglia() {
        this.offerteService.visualizzaOfferteAperteConSelezioneFoglia();
    }

    /**
     * Metodo che rimanda alla classe OfferteService e per-
     * mette di selezionare un'offerta aperta per poterla
     * barattare.
     *
     * @see OfferteService
     */
    public void selezionaUnaOffertaApertaPerBaratto() {
        this.offerteService.selezionaUnaOffertaApertaPerBaratto();
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * visualizzare le proposte di scambio che un utente
     * ha ricevuto.
     *
     * @see OfferteService
     */
    public void visualizzaProposteDiScambio() {
        this.offerteService.visualizzaProposteDiScambio();
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * visualizzare le offerte in scambio di un utente.
     *
     * @see OfferteService
     */
    public void visualizzaOfferteInScambio() {
        this.offerteService.visualizzaOfferteInScambio();
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * visualizzare le ultime risposte ricevute per le
     * offerte in scambio.
     *
     * @see OfferteService
     */
    public void visualizzaUltimeRispostePerOfferteInScambio() {
        this.offerteService.visualizzaUltimeRispostePerOfferteInScambio();
    }

    /**
     * Metodo che rimanda alla classe OfferteService per
     * visualizzare le offerte in scambio e chiuse con la
     * selezione della categoria foglia.
     *
     * @see OfferteService
     */
    public void visualizzaOfferteInScambioEChiuseConSelezioneFoglia() {
        this.offerteService.visualizzaOfferteInScambioEChiuseConSelezioneFoglia();
    }

    /**
     * Metodo che rimanda alla classe GerarchiaService e che
     * permette all'utente di caricare gerarchie delle cate-
     * gorie e valori dei parametri di configurazione da un
     * file.
     *
     * @see GerarchiaService
     */
    public void caricaDatiDaFileUtente() {
        this.gerarchiaService.caricaDatiDaFileUtente();
    }
}
