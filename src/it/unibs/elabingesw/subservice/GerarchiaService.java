package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GerarchiaRepository;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchieSerializableRepository;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.domaintyperenderer.CompositeDomainTypeRenderer;
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
    private final GestoreScambio gestoreScambio;

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreGerarchie e un oggetto GestoreScambio.
     *
     * @param gerarchiaRepository oggetto di tipo GestoreGerarchie
     * @param gestoreScambio oggetto di tipo GestoreScambio
     * @see GestoreGerarchieSerializableRepository
     */
    public GerarchiaService(GerarchiaRepository gerarchiaRepository, GestoreScambio gestoreScambio) {
        this.gerarchiaRepository = gerarchiaRepository;
        this.gestoreScambio = gestoreScambio;
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
        if (InputDati.yesOrNo("Vuoi aggiungere una sotto-categoria per " + gerarchia.getNome() + "?")) {
            var categoriaFiglio = chiediECreaCategoriaFiglioIn(gerarchia);
            var gerarchiaFiglio = gerarchia.inserisciSottoCategoria(categoriaFiglio);

            aggiungiSottoCategorie(gerarchiaFiglio);

            while (InputDati.yesOrNo("Vuoi aggiungere un'altra sotto-categoria per " + gerarchia.getNome() + "?")) {
                var altraCategoriaFiglio = chiediECreaCategoriaFiglioIn(gerarchia);
                var altraGerarchiaFiglio = gerarchia.inserisciSottoCategoria(altraCategoriaFiglio);
                aggiungiSottoCategorie(altraGerarchiaFiglio);
            }
        }
    }

    /**
     * Metodo che chiede all'utente di inserire una categoria radice
     * con i suoi vari campi.
     *
     * @return una categoria radice
     * @see CampoService
     */
    private CategoriaRadice chiediECreaCategoriaRadice() {
        var nomeCategoriaRadice = InputDati.leggiStringaNonVuota("Inserisci nome della categoria radice: ");
        // check se nome già usato o meno tra le altre gerarchia
        // TODO: 25/nov/2022 Sostituire  gestoreGerarchie.isElementoInListaByNome con qualcosa di orientato alla classe che richiama il super. fatto.
        while (gerarchiaRepository.isGerarchiaPresenteByNome(nomeCategoriaRadice)) {
            System.out.println("Errore nome categoria radice già usato: riprovare.");
            nomeCategoriaRadice = InputDati.leggiStringaNonVuota("Reinserisci nome della categoria radice: ");
        }
        var descrizione = InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria radice: ");

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
        var nomeCatFigl = InputDati.leggiStringaNonVuota("Inserisci nome della categoria figlio: ");
        while (gerarchia.isNomeCategoriaUsato(nomeCatFigl)) {
            System.out.println("Errore nome categoria figlio già usato: riprovare.");
            nomeCatFigl = InputDati.leggiStringaNonVuota("Reinserisci nome della categoria radice: ");
        }
        var descrizione = InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria figlio: ");
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
        return InputDati.yesOrNo("Vuoi inserire la nuova gerarchia?");
    }

    /**
     * Metodo che visualizza le gerarchie che sono state caricate
     * nell'applicativo.
     */
    public void visualizzaGerarchieInFormaEstesa() {
        System.out.println("Elenco delle gerarchie caricate:");
        if (this.gerarchiaRepository.getListaGerarchie().isEmpty()) {
            System.out.println("\tNessuna gerarchia presente.");
        } else {
            this.gerarchiaRepository.getListaGerarchie().forEach(gerarchiaDiCategorie ->
                    System.out.println(new CompositeDomainTypeRenderer().render(gerarchiaDiCategorie))
            );
//            this.gestoreGerarchie.getListaGerarchie().forEach(System.out::println);
        }
    }

    /**
     * Metodo che visualizza le gerarchie che sono state caricate
     * nell'applicativo in forma ridotta.
     */
    public void visualizzaGerarchieInFormaRidotta() {
        System.out.println("Elenco delle gerarchie caricate:");
        if (this.gerarchiaRepository.getListaGerarchie().isEmpty()) {
            System.out.println("\tNessuna gerarchia presente.");
        } else {
            //this.gestoreGerarchie.getListaGerarchie().forEach(gerarchiaDiCategorie -> System.out.println(gerarchiaDiCategorie.toStringRidotto()));
            this.gerarchiaRepository.getListaGerarchie().forEach(gerarchiaDiCategorie -> System.out.println(new CompositeDomainTypeLimitedRenderer().render(gerarchiaDiCategorie)));
        }
    }

    /**
     * Metodo che carica i dati da un file dell'utente.
     */
    public void caricaDatiDaFileUtente() {
        var fileUtenteService = new FileUtenteService(this.gerarchiaRepository, this.gestoreScambio);

        fileUtenteService.avviaServizio();
    }
}
