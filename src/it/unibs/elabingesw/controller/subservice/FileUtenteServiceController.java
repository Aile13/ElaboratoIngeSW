package it.unibs.elabingesw.controller.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.repository.GerarchiaRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreGerarchieSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.ScambioRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreScambioSerializableRepository;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.view.FileUtenteServiceView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe FileUtenteServiceController che gestisce le varie
 * operazioni che si effettuano in modo che l'utente possa
 * caricare nell'applicativo dei dati da file.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class FileUtenteServiceController implements Observer {

    private final GerarchiaRepository gerarchiaRepository;
    private final ScambioRepository scambioRepository;
    private final FileUtenteServiceView view;
    private File selectedFile;
    private String contenutoFile;
    
    /**
     * Enumeration CommandOption con i due comandi
     * che si possono scegliere.
     *
     */
    private enum CommandOption {
        PARAMETRI_CONFIGURAZIONE,
        GERARCHIE
    }

    private CommandOption commandSelected;

    /**
     * Costruttore di classe.
     *
     * @param fileUtenteServiceView oggetto di tipo FileUtenteServiceView
     * @param gerarchiaRepository   oggetto di tipo GerarchiaRepository
     * @param scambioRepository     oggetto di tipo ScambioRepository
     */
    public FileUtenteServiceController(FileUtenteServiceView fileUtenteServiceView, GerarchiaRepository gerarchiaRepository, ScambioRepository scambioRepository) {

        this.view = fileUtenteServiceView;
        this.gerarchiaRepository = gerarchiaRepository;
        this.scambioRepository = scambioRepository;

        this.view.addObserver(this);
    }

    /**
     * Metodo che legge e carica i parametri dal file
     * dell'utente.
     */
    private void leggiECaricaParametri() {
        try {
            contenutoFile = Files.readString(selectedFile.toPath());
            rimuoviSpaziQuandoNonStringa();
            programParametri();
            view.visualizzaMessaggio("Nuovi parametri caricati in sistema.");

        } catch (IOException e) {
            view.visualizzaMessaggio("Attenzione: errore durante la lettura del file.");
            view.visualizzaMessaggio("Impossibile procedere con il caricamento dei parametri.");
        } catch (Exception e) {
            view.visualizzaMessaggio(e.getMessage());
        }
    }

    /**
     * Metodo che programma i parametri degli scambi
     * che sono stati impostati.
     *
     * @throws Exception
     */
    private void programParametri() throws Exception {
        Scambio scambio = parametri();
        this.scambioRepository.impostaInfoDiScambio(scambio);
    }

    /**
     * Metodo che legge e carica le gerarchie dal file
     * dell'utente.
     */
    private void leggiECaricaGerarchie() {
        try {
            contenutoFile = Files.readString(selectedFile.toPath());
            rimuoviSpaziQuandoNonStringa();
            programGerarchie();
            view.visualizzaMessaggio("Nuove gerarchie caricate in sistema.");

        } catch (IOException e) {
            view.visualizzaMessaggio("Attenzione: errore durante la lettura del file.");
            view.visualizzaMessaggio("Impossibile procedere con il caricamento delle gerarchie.");
        } catch (Exception e) {
            view.visualizzaMessaggio(e.getMessage());
        }
    }

    /**
     * Metodo che rimuove gli spazi quando l'applicativo
     * legge il file e incontra qualcosa che non sia una
     * stringa.
     */
    private void rimuoviSpaziQuandoNonStringa() {
        StringBuilder builder = new StringBuilder();
        contenutoFile = contenutoFile.trim();
        boolean filtrare = true;
        while (!contenutoFile.isEmpty()) {
            if (contenutoFile.startsWith("\"")) {
                filtrare = !filtrare;
            }
            if (!filtrare || contenutoFile.charAt(0) != ' ') {
                builder.append(contenutoFile.charAt(0));
            }
            consume(String.valueOf(contenutoFile.charAt(0)));
        }

        contenutoFile = builder.toString().replaceAll(System.lineSeparator(), "");
    }

    /**
     * Metodo che controlla e inizializza i parametri
     * che l'applicativo legge da file.
     *
     * @return un oggetto di tipo Scambio
     * @throws Exception
     */
    private Scambio parametri() throws Exception {
        ifStartsWithAndThenConsumeOrError("scambio{");
        String piazza = piazza();
        ifStartsWithAndThenConsumeOrError(",");
        var luoghi = luoghi();
        ifStartsWithAndThenConsumeOrError(",");
        var giorni = giorni();
        ifStartsWithAndThenConsumeOrError(",");
        var intervalliOrari = intervalliOrari();
        ifStartsWithAndThenConsumeOrError(",");
        var scadenza = scadenza();
        ifStartsWithAndThenConsumeOrError("};");
        return new Scambio(piazza, luoghi, giorni, intervalliOrari, scadenza);
    }

    /**
     * Metodo che controlla la scadenza acquisita.
     *
     * @return la scadenza
     * @throws Exception
     */
    private int scadenza() throws Exception {
        ifStartsWithAndThenConsumeOrError("scadenza=");
        var scadenza = Integer.parseInt(matchStringa());
        if (scadenza <= 0) {
            errore();
        }
        return scadenza;
    }

    /**
     * Metodo che controlla la lista degli intervalli
     * orari acquisiti.
     *
     * @return la lista degli intervalli orari
     * @throws Exception
     */
    private List<IntervalloOrario> intervalliOrari() throws Exception {
        List<IntervalloOrario> intervalliOrari = new LinkedList<>();
        ifStartsWithAndThenConsumeOrError("intervalli-orari=[");
        intervalliOrari.add(intervalloOrario());
        while (contenutoFile.startsWith(",")) {
            consume(",");
            var nuovoIntervalloOrario = intervalloOrario();
            if (intervalliOrari.stream().anyMatch(intervalloOrario -> intervalloOrario.intersecaAltroIntervalloOrario(nuovoIntervalloOrario))) {
                errore();
            } else {
                intervalliOrari.add(nuovoIntervalloOrario);
            }
        }
        ifStartsWithAndThenConsumeOrError("]");
        return intervalliOrari;
    }

    /**
     * Metodo che controlla un singolo intervallo
     * orario acquisito.
     *
     * @return un oggetto di tipo IntervalloOrario
     * @throws Exception
     */
    private IntervalloOrario intervalloOrario() throws Exception {
        IntervalloOrario intervalloOrario = null;
        var intervalloString = matchStringa();
        var orari = intervalloString.split("-");
        var minutiOrarioIniziale = orari[0].split(":")[1];
        if (!minutiOrarioIniziale.equals("00") && !minutiOrarioIniziale.equals("30")) {
            errore();
        } else {
            var minutiOrarioFinale = orari[1].split(":")[1];
            if (!minutiOrarioFinale.equals("00") && !minutiOrarioFinale.equals("30")) {
                errore();
            } else {
                var orarioIniziale = LocalTime.parse(orari[0], DateTimeFormatter.ofPattern("HH:mm"));
                var orarioFinale = LocalTime.parse(orari[1], DateTimeFormatter.ofPattern("HH:mm"));
                if (!orarioFinale.isAfter(orarioIniziale)) {
                    errore();
                } else {
                    intervalloOrario = new IntervalloOrario(orarioIniziale, orarioFinale);
                }
            }
        }
        return intervalloOrario;
    }

    /**
     * Metodo che controlla la lista dei giorni
     * acquisiti.
     *
     * @return la lista dei giorni
     * @throws Exception
     */
    private List<DayOfWeek> giorni() throws Exception {
        List<DayOfWeek> giorni = new LinkedList<>();
        ifStartsWithAndThenConsumeOrError("giorni=[");
        giorni.add(DayOfWeek.valueOf(matchStringa()));
        while (contenutoFile.startsWith(",")) {
            consume(",");
            var altroGiorno = DayOfWeek.valueOf(matchStringa());
            if (giorni.stream().anyMatch(dayOfWeek -> dayOfWeek.equals(altroGiorno))) {
                errore();
            } else {
                giorni.add(altroGiorno);
            }
        }
        ifStartsWithAndThenConsumeOrError("]");
        return giorni;
    }

    /**
     * Metodo che controlla la lista dei luoghi
     * acquisiti.
     *
     * @return la lista dei luoghi
     * @throws Exception
     */
    private List<String> luoghi() throws Exception {
        List<String> luoghi = new LinkedList<>();
        ifStartsWithAndThenConsumeOrError("luoghi=[");
        luoghi.add(matchStringa());
        while (contenutoFile.startsWith(",")) {
            consume(",");
            var altroLuogo = matchStringa();
            if (luoghi.stream().anyMatch(luogo -> luogo.equals(altroLuogo))) {
                errore();
            } else {
                luoghi.add(altroLuogo);
            }
        }
        ifStartsWithAndThenConsumeOrError("]");
        return luoghi;
    }

    /**
     * Metodo che controlla la piazza acquisita.
     *
     * @return la piazza
     * @throws Exception
     */
    private String piazza() throws Exception {
        ifStartsWithAndThenConsumeOrError("piazza=");
        return matchStringa();
    }

    /**
     * Metodo che programma le gerarchie che sono
     * state acquisite.
     *
     * @throws Exception
     */
    private void programGerarchie() throws Exception {
        List<GerarchiaDiCategorie> listaGerarchie = new LinkedList<>();
        gerarchiaList(listaGerarchie);
        listaGerarchie.forEach(gerarchiaRepository::inserisciNuovaGerarchia);
    }

    /**
     * Metodo che ritorna la lista delle gerarchie che
     * vengono acquisite dal file dell'utente.
     *
     * @param gerarchie una lista di gerarchie di categorie
     * @throws Exception
     */
    private void gerarchiaList(List<GerarchiaDiCategorie> gerarchie) throws Exception {
        if (!contenutoFile.isEmpty()) {
            var nuovaGerarchia = gerarchia();

            if (gerarchie.stream().anyMatch(gerarchiaDiCategorie -> gerarchiaDiCategorie.isStessoNome(nuovaGerarchia.getNome()))) {
                errore();
            } else {
                gerarchie.add(nuovaGerarchia);
            }

            if (contenutoFile.startsWith(";")) {
                consume(";");
                gerarchiaList(gerarchie);
            } else errore();
        }
    }

    /**
     * Metodo che controlla e inizializza le gerarchie
     * che l'applicativo legge da file.
     *
     * @return un oggetto di tipo GerarchiaDiCategorie
     * @throws Exception
     */
    private GerarchiaDiCategorie gerarchia() throws Exception {
        ifStartsWithAndThenConsumeOrError("gerarchia{");
        var gerarchiaDiCategorie = categoriaRadice();
        ifStartsWithAndThenConsumeOrError("}");
        return gerarchiaDiCategorie;
    }

    /**
     * Metodo che imposta una categoria radice.
     *
     * @return un oggetto di tipo GerarchiaDiCategorie
     * @throws Exception
     */
    private GerarchiaDiCategorie categoriaRadice() throws Exception {
        ifStartsWithAndThenConsumeOrError("categoriaRadice(");
        var nomeCategoriaRadice = matchStringa();
        ifStartsWithAndThenConsumeOrError(",");
        var descrizione = matchStringa();
        ifStartsWithAndThenConsumeOrError(",");
        List<Campo> listaCampi = campoListOp();
        ifStartsWithAndThenConsumeOrError(",");

        if (listaCampi.stream().anyMatch(Campo::isCampoDiDefault) || gerarchiaRepository.isGerarchiaPresenteByNome(nomeCategoriaRadice))
            errore();

        GerarchiaDiCategorie gerarchiaDiCategorie = new GerarchiaDiCategorie(new CategoriaRadice(nomeCategoriaRadice, descrizione, listaCampi));

        categoriaFiglioListOp(gerarchiaDiCategorie);

        ifStartsWithAndThenConsumeOrError(")");
        return gerarchiaDiCategorie;
    }

    /**
     * Metodo che controlla se ci sono categorie figlio
     * nella lista e nel caso le legge e le immette nella gerarchia.
     *
     * @param gerarchia un oggetto di tipo GerarchiaDiCategorie
     * @throws Exception
     */
    private void categoriaFiglioListOp(GerarchiaDiCategorie gerarchia) throws Exception {
        ifStartsWithAndThenConsumeOrError("[");
        if (!contenutoFile.startsWith("]")) categoriaFiglioList(gerarchia);
        ifStartsWithAndThenConsumeOrError("]");
    }

    /**
     * Metodo che legge una o più categorie figlio in una lista e
     * le aggiunge alla gerarchia passata come parametro.
     *
     * @param gerarchia un oggetto di tipo GerarchiaDiCategorie
     * @throws Exception
     */
    private void categoriaFiglioList(GerarchiaDiCategorie gerarchia) throws Exception {
        if (!contenutoFile.isEmpty()) {
            categoriaFiglio(gerarchia);

            if (contenutoFile.startsWith(",")) {
                consume(",");
                categoriaFiglio(gerarchia);
            }
        }
    }

    /**
     * Metodo che imposta, dopo averla letta,
     * una categoria figlio nella gerarchia passata come parametro.
     *
     * @param gerarchia la gerarchia a cui si aggiunge la categoria letta.
     * @throws Exception
     */
    private void categoriaFiglio(GerarchiaDiCategorie gerarchia) throws Exception {
        ifStartsWithAndThenConsumeOrError("categoriaFiglio(");
        var nomeCategoria = matchStringa();

        if (gerarchia.isNomeCategoriaUsato(nomeCategoria)) errore();

        ifStartsWithAndThenConsumeOrError(",");
        var descrizione = matchStringa();
        ifStartsWithAndThenConsumeOrError(",");
        List<Campo> campi = campoListOp();
        ifStartsWithAndThenConsumeOrError(",");
        var nuovaCategoria = new CategoriaFiglio(nomeCategoria, descrizione, campi);
        var nuovaGerarchia = gerarchia.inserisciSottoCategoria(nuovaCategoria);
        categoriaFiglioListOp(nuovaGerarchia);

        ifStartsWithAndThenConsumeOrError(")");
    }

    /**
     * Metodo che controlla se ci sono campi nativi
     * nella lista e nel caso le legge e le colleziona e le ritorna.
     *
     * @return la lista dei campi
     * @throws Exception
     */
    private List<Campo> campoListOp() throws Exception {
        List<Campo> listaCampi = new LinkedList<>();

        ifStartsWithAndThenConsumeOrError("[");
        if (!contenutoFile.startsWith("]")) campoList(listaCampi);

        ifStartsWithAndThenConsumeOrError("]");
        return listaCampi;
    }

    /**
     * Metodo che imposta la lista dei campi che
     * vengono acquisiti dalla lettura nell'elenco dei campi nel file.
     *
     * @param listaCampi la lista dei campi
     * @throws Exception
     */
    private void campoList(List<Campo> listaCampi) throws Exception {
        Campo campo = campo();
        if (campo.isCampoInListaByNome(listaCampi)) {
            errore();
        } else {
            listaCampi.add(campo);
        }

        if (contenutoFile.startsWith(",")) {
            consume(",");
            campoList(listaCampi);
        }

    }

    /**
     * Metodo che legge un campo.
     *
     * @return un oggetto di tipo Campo
     * @throws Exception
     */
    private Campo campo() throws Exception {
        ifStartsWithAndThenConsumeOrError("(");
        var nomeCampo = matchStringa();
        ifStartsWithAndThenConsumeOrError(":");
        var isObbligatorio = matchBoolean();
        ifStartsWithAndThenConsumeOrError(")");
        return new Campo(nomeCampo, isObbligatorio);
    }

    /**
     * Metodo che controlla la presenza di un
     * termine booleano nel file e ne ritorna
     * il corrispondente valore.
     *
     * @return TRUE se legge "t"
     * FALSE se legge "f"
     * @throws Exception
     */
    private boolean matchBoolean() throws Exception {
        if (contenutoFile.startsWith("t")) {
            consume("t");
            return true;
        } else if (contenutoFile.startsWith("f")) {
            consume("f");
            return false;
        } else errore();
        return false;
    }

    /**
     * Metodo che controlla la presenza di un
     * termine stringa nel file e ne ritorna
     * il corrispondente valore.
     *
     * @return una stringa, quella letta
     * @throws Exception
     */
    private String matchStringa() throws Exception {
        ifStartsWithAndThenConsumeOrError("\"");
        if (contenutoFile.startsWith("\"")) errore();

        StringBuilder builder = new StringBuilder();

        if (!contenutoFile.contains("\"")) {
            errore();
        }

        while (contenutoFile.charAt(0) != '"') {
            builder.append(contenutoFile.charAt(0));
            consume(String.valueOf(contenutoFile.charAt(0)));
        }
        consume("\"");
        return builder.toString();
    }

    /**
     * Metodo che accerta che l'inizio corrente
     * del file inizi con la stringa passata come parametro.
     * Quindi se è così procede a rimuovere tale inizio
     * dal contenuto del file.
     * Altrimenti lancia una eccezione perché il file non
     * rispetta la sintassi per l'import dei dati.
     *
     * @param string una stringa
     * @throws Exception
     */
    private void ifStartsWithAndThenConsumeOrError(String string) throws Exception {
        if (contenutoFile.startsWith(string)) {
            consume(string);
        } else errore();
    }

    /**
     * Metodo che rimuove tanti caratteri iniziali dal file
     * quanti sono quelli che costituiscono la stringa parametro.
     * <p>
     * Precondizione: assumo che il file inizi esattamente
     * come indicato nella stringa parametro.
     *
     * @param string una stringa
     */
    private void consume(String string) {
        contenutoFile = contenutoFile.substring(string.length());
    }

    /**
     * Metodo che manda un messaggio d'errore.
     * Specifica che la sintassi del file di caricamento dei dati
     * non è corretta e per questo viene richiamato per sollevare
     * tale eccezione.
     *
     * @throws Exception
     */
    private void errore() throws Exception {
        throw new Exception("Attenzione: contenuto file non conforme a sintassi. Impossibile procedere al caricamento dei dati.");
    }

    /**
     * Metodo che dà avvio alla funzionalità
     */
    public void avviaServizio() {
        this.view.eseguiMenu();
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {
        FileUtenteServiceView viewWithEvents = (FileUtenteServiceView) o;
        switch (viewWithEvents.getSelectedOption()) {
            case "eseguiProceduraDiUscita" -> this.eseguiProceduraDiUscita();
            case "caricaParametriConfigurazione" -> {
                this.commandSelected = CommandOption.PARAMETRI_CONFIGURAZIONE;
                eseguiSelezioneFile();
            }
            case "caricaGerarchieDiCategorie" -> {
                this.commandSelected = CommandOption.GERARCHIE;
                eseguiSelezioneFile();
            }
            case "fileSelezionato" -> {
                selectedFile = viewWithEvents.getSelectedFile();
                System.out.println("File selezionato: " + selectedFile.getAbsolutePath());
                if (commandSelected == CommandOption.PARAMETRI_CONFIGURAZIONE) {
                    this.eseguiProceduraDiCaricamentoParametriDiConfigurazione();
                } else {
                    this.eseguiProceduraDiCaricamentoGerarchieDiCategorie();
                }
            }
            case "fileNonSelezionato" -> this.eseguiProceduraFileNonSelezionato();
        }


    }

    /**
     * Metodo che esegue la seleziona il file da cui importare
     * i dati.
     */
    private void eseguiSelezioneFile() {
        view.visualizzaMessaggio("Ora seleziona dalla finestra il file da cui importare i dati.");
        view.visualizzaMessaggio("Apertura finestra di selezione file.");
        view.eseguiSelezioneFile();
    }

    /**
     * Metodo che comunica all'utente che non è stato selezionato
     * nessun file.
     */
    private void eseguiProceduraFileNonSelezionato() {
        view.visualizzaMessaggio("Nessun file selezionato, esco dalla procedura.");
    }

    /**
     * Metodo che procede con il caricamento da file delle
     * gerarchie di categorie.
     */
    private void eseguiProceduraDiCaricamentoGerarchieDiCategorie() {
        leggiECaricaGerarchie();
    }

    /**
     * Metodo che procede con il caricamento da file dei parametri
     * di configurazione degli scambi: se questi sono già stati con-
     * figurati in precedenza avvisa l'utente ed esce dalla procedura.
     */
    private void eseguiProceduraDiCaricamentoParametriDiConfigurazione() {
        if (scambioRepository.isInfoScambioDaConfigurare()) {
            leggiECaricaParametri();
        } else {
            view.visualizzaMessaggio("Attenzione: info di scambio già configurate.");
            view.visualizzaMessaggio("Esco dalla procedura.");
        }
    }
    
    /**
     * Metodo che esce dalla funzionalità.
     */
    private void eseguiProceduraDiUscita() {
        view.visualizzaMessaggio("Opzione di uscita selezionata, esco da procedura.");
    }
}


