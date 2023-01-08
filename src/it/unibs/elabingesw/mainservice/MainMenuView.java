package it.unibs.elabingesw.mainservice;

import it.unibs.elabingesw.view.View;
import it.unibs.eliapitozzi.mylib.MyFunctionalMenu;
import it.unibs.eliapitozzi.mylib.VoceEComando;

/**
 * Classe MainMenu per la visualizzazione a video di un menu
 * che permette all'utente di visionare quali operazioni svol-
 * gere.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class MainMenuView extends View {
    private String selectedOption;
    public void eseguiMenuConfiguratore() {
        new MyFunctionalMenu(
                "Menu per Configuratore",
                new VoceEComando[]{new VoceEComando("Esci", () -> setSelectedOption("eseguiProceduraDiUscita")),
                        new VoceEComando("Crea nuova gerarchia", () -> setSelectedOption("creaNuovaGerarchia")),
                        new VoceEComando("Visualizza gerarchie", () -> setSelectedOption("visualizzaGerarchieFormaEstesa")),
                        new VoceEComando("Imposta info di scambio", () -> setSelectedOption("impostaInfoDiScambio")),
                        new VoceEComando("Visualizza info di scambio", () -> setSelectedOption("visualizzaInfoDiScambioFormaEstesa")),
                        new VoceEComando("Visualizza offerte aperte di categoria foglia", () -> setSelectedOption("visualizzaOfferteAperteConSelezioneFoglia")),
                        new VoceEComando("Visualizza offerte in scambio e chiuse", () -> setSelectedOption("visualizzaOfferteInScambioEChiuseConSelezioneFoglia")),
                        new VoceEComando("Carica dati da file", () -> setSelectedOption("caricaDatiDaFileUtente"))})
                .eseguiMenu();
    }

    public void eseguiMenuFruitore() {
        new MyFunctionalMenu(
                "Menu per Fruitore",
                new VoceEComando[]{new VoceEComando("Esci", () -> setSelectedOption("eseguiProceduraDiUscita")),
                        new VoceEComando("Visualizza gerarchie", () -> setSelectedOption("visualizzaGerarchieFormaRidotta")),
                        new VoceEComando("Visualizza info di scambio", () -> setSelectedOption("visualizzaInfoDiScambioFormaRidotta")),
                        new VoceEComando("Crea nuova offerta", () -> setSelectedOption("creaNuovaOfferta")),
                        new VoceEComando("Ritira una o più offerte", () -> setSelectedOption("ritiraOfferte")),
                        new VoceEComando("Visualizza tutte le tue offerte", () -> setSelectedOption("visualizzaOfferteUtente")),
                        new VoceEComando("Visualizza offerte aperte di categoria foglia", () -> setSelectedOption("visualizzaOfferteAperteConSelezioneFoglia")),
                        new VoceEComando("Seleziona un'altra offerta aperta per barattare", () -> setSelectedOption("selezionaUnaOffertaApertaPerBaratto")),
                        new VoceEComando("Visualizza proposte di scambio da altri utenti", () -> setSelectedOption("visualizzaProposteDiScambio")),
                        new VoceEComando("Visualizza offerte in scambio", () -> setSelectedOption("visualizzaOfferteInScambio")),
                        new VoceEComando("Visualizza ultime risposte per offerte in scambio", () -> setSelectedOption("visualizzaUltimeRispostePerOfferteInScambio"))})
                .eseguiMenu();
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
        setChanged();
        notifyObservers();
    }
}
