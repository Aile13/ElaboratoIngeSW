package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.MyFunctionalMenu;
import it.unibs.eliapitozzi.mylib.VoceEComando;

import javax.swing.JFileChooser;
import java.io.File;

/**
 * @author Elia
 */
public class FileUtenteServiceView extends View {
    private String selectedOption;
    private File selectedFile;

    public void eseguiMenu() {
        new MyFunctionalMenu(
                "Menu selezione caricamento dati da file utente",
                new VoceEComando[]{
                        new VoceEComando("Esci", () -> setSelectedOption("eseguiProceduraDiUscita")),
                        new VoceEComando("Carica da file i valori dei parametri di configurazione", () -> setSelectedOption("caricaParametriConfigurazione")),
                        new VoceEComando("Carica da file le gerarchie di categorie", () -> setSelectedOption("caricaGerarchieDiCategorie"))
                }
        ).eseguiMenu();
    }

    public void eseguiSelezioneFile() {
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.showOpenDialog(null);
        int returnValue = jFileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            setSelectedFile(jFileChooser.getSelectedFile());
            setSelectedOption("fileSelezionato");
        } else setSelectedOption("fileNonSelezionato");
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
        setChanged();
        notifyObservers();
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

}
