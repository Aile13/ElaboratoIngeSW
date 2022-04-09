package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.eliapitozzi.mylib.MyMenu;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Elia
 */
public class FileUtenteService {

    private final GestoreGerarchie gestoreGerarchie;
    private final GestoreScambio gestoreScambio;
    private File selectedFile;
    private String contenutoFile;

    public FileUtenteService(GestoreGerarchie gestoreGerarchie, GestoreScambio gestoreScambio) {

        this.gestoreGerarchie = gestoreGerarchie;
        this.gestoreScambio = gestoreScambio;
    }

    private void scegliAzioneESelezionaFileDaCaricare() {
        var menu = new MyMenu("Menu selezione caricamento dati da file utente", new String[]{
                "Carica da file i valori dei parametri di configurazione",
                "Carica da file le gerarchie di categorie"
        }, true);

        int scelta = menu.scegli();

        if (scelta != 0) {
            System.out.println("Ora seleziona dalla finestra il file da cui importare i dati.");
            System.out.println("Apertura finestra di selezione file.");

            JFileChooser jFileChooser = new JFileChooser(".");
            int returnValue = jFileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                this.selectedFile = jFileChooser.getSelectedFile();
                System.out.println("File selezionato: " + selectedFile.getAbsolutePath());
                if (scelta == 1) {
                    if (gestoreScambio.isInfoScambioDaConfigurare()) {
                        leggiECaricaParametri();
                    } else {
                        System.out.println("Attenzione: info di scambio già configurate.");
                        System.out.println("Esco dalla procedura.");
                    }
                } else if (scelta == 2) {
                    leggiECaricaGerachie();
                }
            } else {
                System.out.println("Nessun file selezionato, esco dalla procedura.");
            }

        } else {
            System.out.println("Opzione di uscita selezionata, esco da procedura.");
        }
    }

    private void leggiECaricaParametri() {
        try {
            contenutoFile = Files.readString(selectedFile.toPath());
            rimuoviSpaziQuandoNonStringa();
            programParametri();
            System.out.println("Nuovi parametri caricati in sistema.");

        } catch (IOException e) {
            System.out.println("Attenzione: errore durante la lettura del file.");
            System.out.println("Impossibile procedere con il caricamento dei parametri.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void programParametri() throws Exception {
        Scambio scambio = parametri();
        this.gestoreScambio.impostaInfoDiScambio(scambio);
    }

    private void leggiECaricaGerachie() {
        try {
            contenutoFile = Files.readString(selectedFile.toPath());
            rimuoviSpaziQuandoNonStringa();
            programGerarchie();
            System.out.println("Nuove gerarchie caricate in sistema.");

        } catch (IOException e) {
            System.out.println("Attenzione: errore durante la lettura del file.");
            System.out.println("Impossibile procedere con il caricamento delle gerarchie.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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

        contenutoFile = builder.toString()
                .replaceAll(System.lineSeparator(), "");
    }

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

    private int scadenza() throws Exception {
        ifStartsWithAndThenConsumeOrError("scadenza=");
        var scadenza = Integer.parseInt(matchStringa());
        if (scadenza <= 0) {
            errore();
        }
        return scadenza;
    }

    private List<IntervalloOrario> intervalliOrari() throws Exception {
        List<IntervalloOrario> intervalliOrari = new LinkedList<>();
        ifStartsWithAndThenConsumeOrError("intervalli-orari=[");
        intervalliOrari.add(interalloOrario());
        while (contenutoFile.startsWith(",")) {
            consume(",");
            var nuovoIntervalloOrario = interalloOrario();
            if (intervalliOrari.stream()
                    .anyMatch(intervalloOrario ->
                            intervalloOrario.intersecaAltroIntervalloOrario(nuovoIntervalloOrario))) {
                errore();
            } else {
                intervalliOrari.add(nuovoIntervalloOrario);
            }
        }
        ifStartsWithAndThenConsumeOrError("]");
        return intervalliOrari;
    }

    private IntervalloOrario interalloOrario() throws Exception {
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

    private String piazza() throws Exception {
        ifStartsWithAndThenConsumeOrError("piazza=");
        return matchStringa();
    }

    private void programGerarchie() throws Exception {
        List<GerarchiaDiCategorie> listaGerarchie = new LinkedList<>();
        gerarchiaList(listaGerarchie);
        listaGerarchie.forEach(gestoreGerarchie::inserisciNuovaGerarchia);
    }

    private void gerarchiaList(List<GerarchiaDiCategorie> gerarchie) throws Exception {
        if (!contenutoFile.isEmpty()) {
            var nuovaGerarchia = gerarchia();

            if (gerarchie.stream()
                    .anyMatch(gerarchiaDiCategorie ->
                            gerarchiaDiCategorie.isStessoNome(nuovaGerarchia.getNome()))) {
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

    private GerarchiaDiCategorie gerarchia() throws Exception {
        ifStartsWithAndThenConsumeOrError("gerarchia{");
        var gerarchiaDiCategorie = categoriaRadice();
        ifStartsWithAndThenConsumeOrError("}");
        return gerarchiaDiCategorie;
    }

    private GerarchiaDiCategorie categoriaRadice() throws Exception {
        ifStartsWithAndThenConsumeOrError("categoriaRadice(");
        var nomeCategoriaRadice = matchStringa();
        ifStartsWithAndThenConsumeOrError(",");
        var descrizione = matchStringa();
        ifStartsWithAndThenConsumeOrError(",");
        List<Campo> listaCampi = campoListOp();
        ifStartsWithAndThenConsumeOrError(",");

        if (listaCampi.stream().anyMatch(Campo::isCampoDiDefault) ||
                gestoreGerarchie.isElementoInListaByNome(nomeCategoriaRadice))
            errore();

        GerarchiaDiCategorie gerarchiaDiCategorie = new GerarchiaDiCategorie(
                new CategoriaRadice(nomeCategoriaRadice, descrizione, listaCampi)
        );

        categoriaFiglioListOp(gerarchiaDiCategorie);

        ifStartsWithAndThenConsumeOrError(")");
        return gerarchiaDiCategorie;
    }

    private void categoriaFiglioListOp(GerarchiaDiCategorie gerarchia) throws Exception {
        ifStartsWithAndThenConsumeOrError("[");
        if (!contenutoFile.startsWith("]"))
            categoriaFiglioList(gerarchia);
        ifStartsWithAndThenConsumeOrError("]");
    }

    private void categoriaFiglioList(GerarchiaDiCategorie gerarchia) throws Exception {
        if (!contenutoFile.isEmpty()) {
            categoriaFiglio(gerarchia);

            if (contenutoFile.startsWith(",")) {
                consume(",");
                categoriaFiglio(gerarchia);
            }
        }
    }


    private void categoriaFiglio(GerarchiaDiCategorie gerarchia) throws Exception {
        ifStartsWithAndThenConsumeOrError("categoriaFiglio(");
        var nomeCategoria = matchStringa();

        if (gerarchia.isNomeCategoriaUsato(nomeCategoria))
            errore();

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

    private List<Campo> campoListOp() throws Exception {
        List<Campo> listaCampi = new LinkedList<>();

        ifStartsWithAndThenConsumeOrError("[");
        if (!contenutoFile.startsWith("]"))
            campoList(listaCampi);

        ifStartsWithAndThenConsumeOrError("]");
        return listaCampi;
    }

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

    private Campo campo() throws Exception {
        ifStartsWithAndThenConsumeOrError("(");
        var nomeCampo = matchStringa();
        ifStartsWithAndThenConsumeOrError(":");
        var isObbl = matchBoolean();
        ifStartsWithAndThenConsumeOrError(")");
        return new Campo(nomeCampo, isObbl);
    }

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

    private String matchStringa() throws Exception {
        ifStartsWithAndThenConsumeOrError("\"");
        if (contenutoFile.startsWith("\""))
            errore();

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

    private void ifStartsWithAndThenConsumeOrError(String string) throws Exception {
        if (contenutoFile.startsWith(string)) {
            consume(string);
        } else errore();
    }

    private void consume(String string) {
        contenutoFile = contenutoFile.substring(string.length());
    }

    private void errore() throws Exception {
        throw new Exception("Attenzione: contenuto file non conforme a sintassi. " +
                "Impossibile procedere al caricamento dei dati.");
    }

    public void avviaServizio() {
        scegliAzioneESelezionaFileDaCaricare();
    }
}
