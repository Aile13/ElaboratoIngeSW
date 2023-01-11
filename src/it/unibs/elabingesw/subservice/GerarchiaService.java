package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.repository.GerarchiaRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreGerarchieSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.ScambioRepository;
import it.unibs.elabingesw.view.GerarchiaServiceView;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe GerarchiaService che gestisce le varie operazioni
 * che si effettuano su una gerarchia e sulle eventuali
 * categorie radice e categorie figlie.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GerarchiaService {

    private final GerarchiaRepository gerarchiaRepository;
    private final ScambioRepository scambioRepository;
    private final GerarchiaServiceView view = new GerarchiaServiceView();

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreGerarchie e un oggetto GestoreScambio.
     *
     * @param gerarchiaRepository oggetto di tipo GestoreGerarchie
     * @param scambioRepository oggetto di tipo GestoreScambio
     * @see GestoreGerarchieSerializableRepository
     */
    public GerarchiaService(GerarchiaRepository gerarchiaRepository, ScambioRepository scambioRepository) {
        this.gerarchiaRepository = gerarchiaRepository;
        this.scambioRepository = scambioRepository;
    }

    /**
     * Metodo che crea una nuova gerarchia: una volta confermata la
     * sua creazione, questa viene aggiunta all'applicativo.
     */
    public void creaNuovaGerarchia() {
        var categoriaRadice = chiediECreaCategoriaRadice();
        var gerarchia = new GerarchiaDiCategorie(categoriaRadice);

        aggiungiSottoCategorie(gerarchia);

        if (chiediConfermaInserimentoGerarchia()) {
            gerarchiaRepository.inserisciNuovaGerarchia(gerarchia);
        }
    }

    /**
     * Metodo che aggiunge eventuali sottocategorie (ricorsivamente)
     * a una specifica gerarchia che viene passata come parametro.
     *
     * @param gerarchia la gerarchia alla quale si vogliono aggiungere
     *                  sottocategorie
     * @return gerarchia oggetto della classe GerarchiaDiCategorie
     * @see GerarchiaDiCategorie
     */
    private void aggiungiSottoCategorie(GerarchiaDiCategorie gerarchia) {
        if (view.chiediConfermaInserimentoSottoCategoriaConNome(gerarchia.getNome())) {
            var categoriaFiglio = chiediECreaCategoriaFiglioIn(gerarchia);
            var gerarchiaFiglio = gerarchia.inserisciSottoCategoria(categoriaFiglio);

            aggiungiSottoCategorie(gerarchiaFiglio);

            while (view.chiediConfermaInserimentoAltraSottoCategoriaConNome(gerarchia.getNome())) {
                var altraCategoriaFiglio = chiediECreaCategoriaFiglioIn(gerarchia);
                var altraGerarchiaFiglio = gerarchia.inserisciSottoCategoria(altraCategoriaFiglio);
                aggiungiSottoCategorie(altraGerarchiaFiglio);
            }
        }
    }

    /**
     * Metodo che chiede all'utente d'inserire una categoria radice
     * con i suoi vari campi.
     *
     * @return una categoria radice
     * @see CampoService
     */
    private CategoriaRadice chiediECreaCategoriaRadice() {
        var nomeCategoriaRadice = view.getNomeCategoriaRadiceString();
        // check se nome già usato o meno tra le altre gerarchia
        while (gerarchiaRepository.isGerarchiaPresenteByNome(nomeCategoriaRadice)) {
            view.visualizzaMessaggio("Errore nome categoria radice già usato: riprovare.");
            nomeCategoriaRadice = view.getNewNomeCategoriaRadiceString();
        }
        var descrizione = view.getDescrizioneCategoriaRadiceString();

        var listaCampi = CampoService.chiediListaDiCampiPerCategoriaRadice();

        return new CategoriaRadice(nomeCategoriaRadice, descrizione, listaCampi);
    }




    /**
     * Metodo che chiede all'utente di inserire una categoria figlio
     * con i suoi vari campi.
     *
     * @param gerarchia un oggetto di tipo Gerarchia
     * @return una categoria figlio
     * @see CampoService
     */
    private CategoriaFiglio chiediECreaCategoriaFiglioIn(GerarchiaDiCategorie gerarchia) {
        var nomeCatFigl = view.getNomeCategoriaFiglioString();
        while (gerarchia.isNomeCategoriaUsato(nomeCatFigl)) {
            view.visualizzaMessaggio("Errore nome categoria figlio già usato: riprovare.");
            nomeCatFigl = view.getNewNomeCategoriaFiglioString();
        }
        var descrizione = view.getDescrizioneCategoriaFiglioString();
        var listaCampi = CampoService.chiediListaDiCampiPerCategoriaFiglio(gerarchia);

        return new CategoriaFiglio(nomeCatFigl, descrizione, listaCampi);
    }

    /**
     * Metodo per chiedere all'utente la conferma di inserimento
     * di una nuova gerarchia.
     *
     * @return TRUE se si conferma l'inserimento
     * FALSE se non si conferma l'inserimento
     */
    private boolean chiediConfermaInserimentoGerarchia() {
        return view.chiediConfermaInserimentoNuovaGerarchia();
    }

    /**
     * Metodo che visualizza le gerarchie che sono state caricate
     * nell'applicativo.
     */
    public void visualizzaGerarchieInFormaEstesa() {
        view.visualizzaMessaggio("Elenco delle gerarchie caricate:");
        if (this.gerarchiaRepository.getListaGerarchie().isEmpty()) {
           view.visualizzaMessaggio("\tNessuna gerarchia presente.");
        } else {
            this.gerarchiaRepository.getListaGerarchie().forEach(gerarchiaDiCategorie ->
                    view.visualizzaMessaggio(new CompositeDomainTypeRenderer().render(gerarchiaDiCategorie))
            );
        }
    }

    /**
     * Metodo che visualizza le gerarchie che sono state caricate
     * nell'applicativo in forma ridotta.
     */
    public void visualizzaGerarchieInFormaRidotta() {
        view.visualizzaMessaggio("Elenco delle gerarchie caricate:");
        if (this.gerarchiaRepository.getListaGerarchie().isEmpty()) {
            view.visualizzaMessaggio("\tNessuna gerarchia presente.");
        } else {
            this.gerarchiaRepository.getListaGerarchie().forEach(gerarchiaDiCategorie ->
                    view.visualizzaMessaggio(new CompositeDomainTypeLimitedRenderer().render(gerarchiaDiCategorie))
            );
        }
    }

    /**
     * Metodo che carica i dati da un file dell'utente.
     */
    public void caricaDatiDaFileUtente() {
        var fileUtenteService = new FileUtenteService(this.gerarchiaRepository, this.scambioRepository);

        fileUtenteService.avviaServizio();
    }
}
