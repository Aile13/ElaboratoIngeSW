package it.unibs.elabingesw.controller.mainservice;

import it.unibs.elabingesw.businesslogic.repository.GerarchiaRepository;
import it.unibs.elabingesw.businesslogic.repository.OffertaRepository;
import it.unibs.elabingesw.businesslogic.repository.ScambioRepository;
import it.unibs.elabingesw.businesslogic.repository.UtenteRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreGerarchieSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreUtentiSerializableRepository;
import it.unibs.elabingesw.businesslogic.utente.UserType;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.elabingesw.controller.subservice.GerarchiaServiceController;
import it.unibs.elabingesw.controller.subservice.OfferteServiceController;
import it.unibs.elabingesw.controller.subservice.ScambioServiceController;
import it.unibs.elabingesw.view.MainMenuView;

import java.util.Observable;
import java.util.Observer;

/**
 * Classe MacroServicesController di gestione generale.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class MacroServicesController implements Observer {
    private final MainMenuView mainMenuView;
    private final UtenteRepository utenteRepository;
    private final GerarchiaRepository gerarchiaRepository;
    private final ScambioRepository scambioRepository;
    private final OffertaRepository offertaRepository;

    private final GerarchiaServiceController gerarchiaService;
    private final ScambioServiceController scambioService;
    private final OfferteServiceController offerteService;
    private Utente userLogged;

    /**
     * Costruttore di classe.
     *
     * @param mainMenuView        oggetto di tipo MainMenuView
     * @param utenteRepository    oggetto di tipo UtenteRepository
     * @param gerarchiaRepository oggetto di tipo GerarchiaRepository
     * @param scambioRepository   oggetto di tipo ScambioRepository
     * @param offertaRepository   oggetto di tipo OffertaRepository
     */
    public MacroServicesController(MainMenuView mainMenuView, UtenteRepository utenteRepository, GerarchiaRepository gerarchiaRepository, ScambioRepository scambioRepository, OffertaRepository offertaRepository) {
        this.mainMenuView = mainMenuView;
        this.utenteRepository = utenteRepository;
        this.gerarchiaRepository = gerarchiaRepository;
        this.scambioRepository = scambioRepository;
        this.offertaRepository = offertaRepository;

        this.gerarchiaService = new GerarchiaServiceController(this.gerarchiaRepository, this.scambioRepository);
        this.scambioService = new ScambioServiceController(this.scambioRepository);
        this.offerteService = new OfferteServiceController(this.offertaRepository, this.gerarchiaRepository, this.scambioRepository);

        mainMenuView.addObserver(this);
    }

    /**
     * Metodo che salva utenti e gerarchie su file.
     *
     * @see GestoreUtentiSerializableRepository
     * @see GestoreGerarchieSerializableRepository
     */
    private void eseguiSalvataggio() {
        utenteRepository.salvaUtenti();
        gerarchiaRepository.salvaGerarchie();
        scambioRepository.salvaInfoScambio();
        offertaRepository.salvaOfferte();
    }

    /**
     * Metodo che rimanda alla classe GerarchiaServiceController
     * per creare una nuova gerarchia.
     *
     * @see GerarchiaServiceController
     */
    public void creaNuovaGerarchia() {
        this.gerarchiaService.creaNuovaGerarchia();
    }

    /**
     * Metodo che esegue la procedura di uscita dall'applicativo dopo aver salvato i dati inseriti.
     */
    public void eseguiProceduraDiUscita() {
        eseguiSalvataggio();
    }

    /**
     * Metodo che rimanda alla classe GerarchiaServiceController
     * per visualizzare tutte le gerarchie caricate.
     *
     * @see GerarchiaServiceController
     */
    public void visualizzaGerarchieFormaEstesa() {
        this.gerarchiaService.visualizzaGerarchieInFormaEstesa();
    }

    /**
     * Metodo che rimanda alla classe GerarchiaServiceController
     * per visualizzare tutte le gerarchie caricate in forma
     * ridotta.
     *
     * @see GerarchiaServiceController
     */
    public void visualizzaGerarchieFormaRidotta() {
        this.gerarchiaService.visualizzaGerarchieInFormaRidotta();
    }

    /**
     * Metodo che rimanda alla classe ScambioServiceController
     * per visualizzare tutte le informazioni degli scambi.
     *
     * @see ScambioServiceController
     */
    public void visualizzaInfoDiScambioFormaEstesa() {
        this.scambioService.visualizzaInfoDiScambioFormaEstesa();
    }

    /**
     * Metodo che rimanda alla classe ScambioServiceController 
     * per visualizzare tutte le informazioni degli scambi in
     * forma ridotta.
     *
     * @see ScambioServiceController
     */
    public void visualizzaInfoDiScambioFormaRidotta() {
        this.scambioService.visualizzaInfoDiScambioFormaRidotta();
    }

    /**
     * Metodo che rimanda alla classe ScambioServiceController
     * per impostare le informazioni sugli scambi.
     *
     * @see ScambioServiceController
     */
    public void impostaInfoDiScambio() {
        this.scambioService.impostaInfoScambio();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController
     * per settare l'utente che ha eseguito l'accesso.
     *
     * @param userLogged l'utente che ha eseguito l'accesso.
     * @see OfferteServiceController
     */
    public void setUser(Utente userLogged) {
        this.offerteService.setUser(userLogged);
        this.userLogged = userLogged;
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController
     * per visualizzare le offerte di un utente.
     *
     * @see OfferteServiceController
     */
    public void visualizzaOfferteUtente() {
        this.offerteService.visualizzaOfferteUtente();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController
     * per creare una nuova offerta.
     *
     * @see OfferteServiceController
     */
    public void creaNuovaOfferta() {
        this.offerteService.creaNuovaOfferta();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController 
     * per ritirare le offerte di un utente.
     *
     * @see OfferteServiceController
     */
    public void ritiraOfferte() {
        this.offerteService.ritiraOfferte();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController
     * per visualizzare le offerte aperte con la selezione
     * della categoria foglia.
     *
     * @see OfferteServiceController
     */
    public void visualizzaOfferteAperteConSelezioneFoglia() {
        this.offerteService.visualizzaOfferteAperteConSelezioneFoglia();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController 
     * e permette di selezionare un'offerta aperta per poterla
     * barattare.
     *
     * @see OfferteServiceController
     */
    public void selezionaUnaOffertaApertaPerBaratto() {
        this.offerteService.selezionaUnaOffertaApertaPerBaratto();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController
     * per visualizzare le proposte di scambio che un utente
     * ha ricevuto.
     *
     * @see OfferteServiceController
     */
    public void visualizzaProposteDiScambio() {
        this.offerteService.visualizzaProposteDiScambio();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController
     * per visualizzare le offerte in scambio di un utente.
     *
     * @see OfferteServiceController
     */
    public void visualizzaOfferteInScambio() {
        this.offerteService.visualizzaOfferteInScambio();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController
     * per visualizzare le ultime risposte ricevute per le
     * offerte in scambio.
     *
     * @see OfferteServiceController
     */
    public void visualizzaUltimeRispostePerOfferteInScambio() {
        this.offerteService.visualizzaUltimeRispostePerOfferteInScambio();
    }

    /**
     * Metodo che rimanda alla classe OfferteServiceController
     * per visualizzare le offerte in scambio e chiuse con la
     * selezione della categoria foglia.
     *
     * @see OfferteServiceController
     */
    public void visualizzaOfferteInScambioEChiuseConSelezioneFoglia() {
        this.offerteService.visualizzaOfferteInScambioEChiuseConSelezioneFoglia();
    }

    /**
     * Metodo che rimanda alla classe GerarchiaService e che
     * permette all'utente di caricare gerarchie delle categorie e
     * valori dei parametri di configurazione da un file.
     *
     * @see GerarchiaServiceController
     */
    public void caricaDatiDaFileUtente() {
        this.gerarchiaService.caricaDatiDaFileUtente();
    }

    /**
     * Metodo che mostra a video il menu che è differente in base
     * al tipo di utente che ne richiede l'utilizzo.
     *
     * @see MainMenuView
     */
    public void eseguiMainMenu() {
        if (userLogged.getUserType() == UserType.CONFIGURATORE)
            mainMenuView.eseguiMenuConfiguratore();
        else mainMenuView.eseguiMenuFruitore();
    }

    /**
     * Metodo che viene chiamato ogni volta che l'oggetto osserva-
     * to viene modificato. Un'applicazione chiama il metodo di un 
     * oggetto in modo che tutti gli osservatori vengano notificati
     * del cambiamento.
     *
     * @param o oggetto di tipo Observable.
     * @param arg argomento che verrà passato al metodo notifyObservers()
     */
    @Override
    public void update(Observable o, Object arg) {
        switch (((MainMenuView) o).getSelectedOption()) {
            case "eseguiProceduraDiUscita" -> this.eseguiProceduraDiUscita(); //todo fatto
            case "creaNuovaGerarchia" -> this.creaNuovaGerarchia();
            case "visualizzaGerarchieFormaEstesa" -> this.visualizzaGerarchieFormaEstesa();
            case "impostaInfoDiScambio" -> this.impostaInfoDiScambio();
            case "visualizzaInfoDiScambioFormaEstesa" -> this.visualizzaInfoDiScambioFormaEstesa();
            case "visualizzaOfferteAperteConSelezioneFoglia" -> this.visualizzaOfferteAperteConSelezioneFoglia();
            case "visualizzaOfferteInScambioEChiuseConSelezioneFoglia" -> this.visualizzaOfferteInScambioEChiuseConSelezioneFoglia();
            case "caricaDatiDaFileUtente" -> this.caricaDatiDaFileUtente();
            case "visualizzaGerarchieFormaRidotta" -> this.visualizzaGerarchieFormaRidotta();
            case "visualizzaInfoDiScambioFormaRidotta" -> this.visualizzaInfoDiScambioFormaRidotta();
            case "creaNuovaOfferta" -> this.creaNuovaOfferta();
            case "ritiraOfferte" -> this.ritiraOfferte();
            case "visualizzaOfferteUtente" -> this.visualizzaOfferteUtente();
            case "selezionaUnaOffertaApertaPerBaratto" -> this.selezionaUnaOffertaApertaPerBaratto();
            case "visualizzaProposteDiScambio" -> this.visualizzaProposteDiScambio();
            case "visualizzaOfferteInScambio" -> this.visualizzaOfferteInScambio();
            case "visualizzaUltimeRispostePerOfferteInScambio" -> this.visualizzaUltimeRispostePerOfferteInScambio();
        }
    }
}
