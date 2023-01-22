package it.unibs.elabingesw.view;

import it.unibs.eliapitozzi.mylib.MyFunctionalMenu;
import it.unibs.eliapitozzi.mylib.VoceEComando;

import javax.swing.JFileChooser;
import java.io.File;

/**
 * Classe FileUtenteServiceView, sottoclasse di View.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class FileUtenteServiceView extends View {
    private String selectedOption;
    private File selectedFile;

    /**
     * Metodo che esegue il menu.
     */
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

    /**
     * Metodo che si occupa della procedura relativa alla
     * selezione del file.
     */
    public void eseguiSelezioneFile() {
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.showOpenDialog(null);
        int returnValue = jFileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            setSelectedFile(jFileChooser.getSelectedFile());
            setSelectedOption("fileSelezionato");
        } else setSelectedOption("fileNonSelezionato");
    }

    /**
     * Metodo getter.
     *
     * @return l'opzione scelta
     */
    public String getSelectedOption() {
        return selectedOption;
    }

    /**
     * Metodo setter.
     *
     * @param selectedOption l'opzione scelta
     */
    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo getter.
     *
     * @return il file selezionato
     */
    public File getSelectedFile() {
        return selectedFile;
    }

    /**
     * Metodo setter.
     *
     * @param selectedFile il file selezionato
     */
    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

}
