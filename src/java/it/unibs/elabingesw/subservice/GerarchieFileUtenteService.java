package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
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
            System.out.println(contenutoFile);

            program();

        } catch (IOException e) {
            System.err.println("Attenzione: errore durante la lettura del file.");
            System.err.println("Impossibile procedere con il caricamento di tutte le gerarchie.");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void rimuoviSpaziQuandoNonStringa() {
        StringBuilder builder = new StringBuilder();
        contenutoFile.replaceAll("\n\t", "");
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
        contenutoFile = builder.toString();
    }

    private void program() throws Exception {
        gerarchiaList();
    }

    private void gerarchiaList() throws Exception {
        if (!contenutoFile.isEmpty()) {
            gerarchia();
            if (contenutoFile.startsWith(";")) {
                consume(";");
                gerarchiaList();
            } else errore();
        }
    }

    private void gerarchia() throws Exception {
        ifStartsWithAndThenConsumeOrError("gerarchia{");
        categoriaRadice();
        ifStartsWithAndThenConsumeOrError("}");
    }

    private CategoriaRadice categoriaRadice() throws Exception {
        ifStartsWithAndThenConsumeOrError("categoriaRadice(");
        var nomeCategoriaRadice = matchStringa();
        ifStartsWithAndThenConsumeOrError(",");
        var descrizione = matchStringa();
        ifStartsWithAndThenConsumeOrError(",");
        List<Campo> listaCampi = campoListOp();

        if (listaCampi.stream().anyMatch(Campo::isCampoDiDefault))
            errore();

        return new CategoriaRadice(nomeCategoriaRadice, descrizione, listaCampi);
    }

    private List<Campo> campoListOp() throws Exception {
        List<Campo> listaCampi = new LinkedList<>();

        ifStartsWithAndThenConsumeOrError("[");
        if (!contenutoFile.startsWith("]"))
            campoList(listaCampi);

        ifStartsWithAndThenConsumeOrError("]");
        return listaCampi;
    }

    private List<Campo> campoList(List<Campo> listaCampi) throws Exception {
        Campo campo = campo();
        if (campo.isCampoInListaByNome(listaCampi)) {
            errore();
        }

        if (contenutoFile.startsWith(",")) {
            consume(",");
            campoList(listaCampi);
        }
        return listaCampi;
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
        contenutoFile = contenutoFile.replaceFirst(string, "");
    }

    private void errore() throws Exception {
        throw new Exception("Attenzione: contenuto file non conforme a sintassi. " +
                "Impossibile procedere al caricamento di tutte le gerarchie.");
    }

    public void avviaServizio() {
        selezionaFileDaCaricare();
    }
}
