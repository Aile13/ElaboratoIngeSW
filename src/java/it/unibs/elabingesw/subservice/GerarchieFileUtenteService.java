package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Elia
 */
public class GerarchieFileUtenteService {

    private final GestoreGerarchie gestoreGerarchie;
    private File selectedFile;
    private String contenutoFile;

    public GerarchieFileUtenteService(GestoreGerarchie gestoreGerarchie) {

        this.gestoreGerarchie = gestoreGerarchie;
    }

    private void selezionaFileDaCaricare() {
        System.out.println("Apertura finestra di selezione file.");

        JFileChooser jFileChooser = new JFileChooser(".");
        int returnValue = jFileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = jFileChooser.getSelectedFile();
            System.out.println("File selezionato: " + selectedFile.getAbsolutePath());
            leggiECaricaGerachie();
        } else {
            System.out.println("Nessun file selezionato, esco dalla procedura.");
        }
        this.selectedFile = jFileChooser.getSelectedFile();
    }

    private void leggiECaricaGerachie() {
        try {
            contenutoFile = Files.readString(selectedFile.toPath());
            rimuoviSpaziQuandoNonStringa();
            program();
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

    private void program() throws Exception {
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
                "Impossibile procedere al caricamento delle gerarchie.");
    }

    public void avviaServizio() {
        selezionaFileDaCaricare();
    }
}
