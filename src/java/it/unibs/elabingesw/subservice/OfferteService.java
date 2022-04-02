package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreOfferte;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.Offerta;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.util.List;

/**
 * @author Elia
 */
public class OfferteService {
    private final GestoreOfferte gestoreOfferte;
    private final GestoreGerarchie gestoreGerarchie;
    private final GestoreScambio gestoreScambio;
    private Utente utente;

    public OfferteService(GestoreOfferte gestoreOfferte, GestoreGerarchie gestoreGerarchie, GestoreScambio gestoreScambio) {

        this.gestoreOfferte = gestoreOfferte;
        this.gestoreGerarchie = gestoreGerarchie;
        this.gestoreScambio = gestoreScambio;
    }

    public void creaNuovaOfferta() {
        System.out.println("Procedura di creazione nuovo articolo avviata");
        if (this.gestoreGerarchie.haGerarchie()) {

            var nomeArticolo = chiediNomeArticolo();
            var gerarchiaSelezionata = chiediGerarchia();
            var categoriaFogliaSelezionata = chiediCategoriaFogliaByGerarchia(gerarchiaSelezionata);
            var listaCampiCompilati = new ListaCampiCompilati(gerarchiaSelezionata, categoriaFogliaSelezionata);
            compilaListaCampiCompilati(listaCampiCompilati);

            this.gestoreOfferte.inserisciNuovaOfferta(
                    new Offerta(nomeArticolo, utente, listaCampiCompilati, categoriaFogliaSelezionata)
            );
            System.out.println("Offerta inserita.");
        } else {
            System.out.println("Attenzione: non sono presenti gerarchie per inserire nessun articolo.");
            System.out.println("Impossibile inserire una nuova offerta.");
        }
    }

    private void compilaListaCampiCompilati(ListaCampiCompilati listaCampiCompilati) {
        listaCampiCompilati.getCampiCompilati().forEach((campo, s) -> {
            String compilazione = null;
            if (campo.isObbligatorio()) {
                compilazione = InputDati.leggiStringaNonVuota("Compila campo obbligatorio " + campo.getNome() + ": ");
            } else {
                if (InputDati.yesOrNo("Compilare campo non obbligatorio " + campo.getNome() + " ? ")) {
                    compilazione = InputDati.leggiStringaNonVuota("Compila campo " + campo.getNome() + ": ");
                }
            }
            listaCampiCompilati.getCampiCompilati().put(campo, compilazione);
        });
    }

    private Categoria chiediCategoriaFogliaByGerarchia(GerarchiaDiCategorie gerarchiaSelezionata) {
        System.out.println("Seleziona la categoria foglia di interesse: ");
        for (Categoria categoria : gerarchiaSelezionata.getListaDiCategoriaFoglia()) {
            if (InputDati.yesOrNo("Vuoi selezionare " + categoria.getNome() + "?")) {
                return categoria;
            }
        }
        System.out.println("Errore: nessuna categoria selezionata, riprovare.");
        return chiediCategoriaFogliaByGerarchia(gerarchiaSelezionata);
    }

    private GerarchiaDiCategorie chiediGerarchia() {
        System.out.println("Seleziona la gerarchia che contiene la categoria foglia di interesse: ");
        for (GerarchiaDiCategorie gerarchia : this.gestoreGerarchie.getListaGerarchie()) {
            if (InputDati.yesOrNo("Vuoi selezionare " + gerarchia.getNome() + "?")) {
                return gerarchia;
            }
        }
        System.out.println("Errore: nessuna gerarchia selezionata, riprovare.");
        return chiediGerarchia();
    }

    private String chiediNomeArticolo() {
        var nomeArticolo = InputDati.leggiStringaNonVuota("Inserisci il titolo dell'articolo: ");
        while (this.gestoreOfferte.isOffertaGiaPresenteByNome(nomeArticolo)) {
            System.out.println("Errore: nome articolo già usato, riprovare.");
            nomeArticolo = InputDati.leggiStringaNonVuota("Reinserisci il titolo dell'ariticolo: ");
        }
        return nomeArticolo;
    }

    public void visualizzaOfferteUtente() {
        if (this.gestoreOfferte.getOfferteByUser(utente).isEmpty()) {
            System.out.println("\tAttenzione non ci sono offerte di " + utente.getUsername() + " da visualizzare.");
        } else {
            System.out.println("Offerte aperte e ritirate di " + utente.getUsername() + ":");
            this.gestoreOfferte.getOfferteByUser(utente).forEach(
                    System.out::println
            );
        }
    }

    public void setUser(Utente utente) {
        this.utente = utente;
    }

    public void ritiraOfferte() {
        if (this.gestoreOfferte.getOfferteAperteByUser(this.utente).isEmpty()) {
            System.out.println("\tAttenzione: non ci sono offerte aperte da ritirare.");
        } else {
            System.out.println("Seleziona quali offerte aperte vuoi ritirare: ");
            this.gestoreOfferte.getOfferteAperteByUser(this.utente).forEach(offerta -> {
                if (InputDati.yesOrNo("\tVuoi ritirare l'offerta: " + offerta.getNomeArticolo() + "? ")) {
                    offerta.ritiraOfferta();
                }
            });
        }
    }

    public void visualizzaOfferteAperteConSelezioneFoglia() {
        if (this.gestoreGerarchie.haGerarchie()) {
            GerarchiaDiCategorie gerarchia = chiediGerarchia();
            Categoria categoria = chiediCategoriaFogliaByGerarchia(gerarchia);
            if (this.gestoreOfferte.getOfferteAperteByCategoriaFoglia(categoria).isEmpty()) {
                System.out.println("Attenzione: nessuna offerta aperta per questa categoria foglia.");
            } else {
                System.out.println("Elenco offerte data la categoria selezionata: ");
                this.gestoreOfferte.getOfferteAperteByCategoriaFoglia(categoria).forEach(System.out::println);
            }
        } else {
            System.out.println("Attenzione: non sono presenti gerarchie da selezionare per visualizzare offerte.");
            System.out.println("Impossibile procedere per visualizzare offerte aperte.");
        }
    }

    private Offerta chiediOffertaByList(List<Offerta> listaOfferte) {
        System.out.println("Seleziona l'offerta aperta: ");
        for (Offerta offerta : this.gestoreOfferte.getOfferteAperteByUser(utente)) {
            if (InputDati.yesOrNo("Vuoi selezionare " + offerta.getNomeArticolo() + "?")) {
                return offerta;
            }
        }
        System.out.println("Errore: nessuna offerta selezionata, riprovare.");
        return chiediOffertaByList(listaOfferte);
    }

    public void selezionaUnaOffertaApertaPerBaratto() {
        if (gestoreScambio.isInfoScambioDaConfigurare()) {
            System.out.println("Attenzione: criteri e tempistiche di scambio non ancora impostati.");
            System.out.println("Impossibile procedere con l'operazione di baratto.");
        } else {
            System.out.println("Seleziona una tua offerta aperta che intendi barattare");
            var listaOfferteAperteUtente = this.gestoreOfferte.getOfferteAperteByUser(utente);
            if (listaOfferteAperteUtente.isEmpty()) {
                System.out.println("Attenzione: non ci sono offerte aperte da selezionare.");
                System.out.println("Impossibile procedere con l'operazione di baratto.");
            } else {
                Offerta offertaDaBarattareA = chiediOffertaByList(listaOfferteAperteUtente);
                System.out.println("Seleziona ora una offerta aperta di medesima categoria " +
                        "e di diverso utente che intedi barattare");
                var listaOffAperteNonUtenteStessaCat = this.gestoreOfferte
                        .getOfferteAperteByCategoriaFogliaAndExcludeUser(
                                offertaDaBarattareA.getCategoriaDiAppartenenza(), utente);
                if (listaOffAperteNonUtenteStessaCat.isEmpty()) {
                    System.out.println("Attenzione: non ci sono altre offerte da selezionare disponibili.");
                    System.out.println("Impossibile procedere con l'operazione di baratto.");
                } else {
                    var offertaDaBarattareB = chiediOffertaByList(listaOffAperteNonUtenteStessaCat);
                    System.out.println("Creazione legame tra le offerte selezionate.");
                    System.out.println("Ora attendi eventuale risposta della controparte. Guarda in offerte in scambio.");

                    offertaDaBarattareA.setInfoScambio(this.gestoreScambio.getInfoDiScambio());
                    offertaDaBarattareB.setInfoScambio(this.gestoreScambio.getInfoDiScambio());

                    offertaDaBarattareA.creaLegameEModificaStatiConOfferta(offertaDaBarattareB);
                }
            }
        }
    }

    public void visualizzaProposteDiScambio() {
        
    }
}
