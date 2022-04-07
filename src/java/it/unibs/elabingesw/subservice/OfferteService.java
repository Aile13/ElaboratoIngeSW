package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreOfferte;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.Offerta;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * Classe OfferteService che gestisce le varie operazioni
 * che si effettuano su un'offerta di un fruitore su un
 * determinato articolo.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class OfferteService {
    private final GestoreOfferte gestoreOfferte;
    private final GestoreGerarchie gestoreGerarchie;
    private final GestoreScambio gestoreScambio;
    private Utente utente;

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreOfferte e un oggetto GestoreGerarchie.
     *
     * @param gestoreOfferte
     * @param gestoreGerarchie
     * @see GestoreOfferte
     * @see GestoreGerarchie
     */
    public OfferteService(GestoreOfferte gestoreOfferte, GestoreGerarchie gestoreGerarchie, GestoreScambio gestoreScambio) {
        this.gestoreOfferte = gestoreOfferte;
        this.gestoreGerarchie = gestoreGerarchie;
        this.gestoreScambio = gestoreScambio;
    }

    /**
     * Metodo che crea una nuova offerta: una volta confermata la
     * sua creazione, questa viene aggiunta all'applicativo.
     */
    public void creaNuovaOfferta() {
        System.out.println("Procedura di creazione nuovo articolo avviata");
        if (this.gestoreGerarchie.haGerarchie()) {

            var nomeArticolo = chiediNomeArticolo();
            var gerarchiaSelezionata = chiediGerarchia();
            var categoriaFogliaSelezionata = chiediCategoriaFogliaByGerarchia(gerarchiaSelezionata);
            var listaCampiCompilati = new ListaCampiCompilati(gerarchiaSelezionata, categoriaFogliaSelezionata);
            ListaCampiCompilatiService.compila(listaCampiCompilati);

            this.gestoreOfferte.inserisciNuovaOfferta(
                    new Offerta(nomeArticolo, utente, listaCampiCompilati, categoriaFogliaSelezionata)
            );
            System.out.println("Offerta inserita.");
        } else {
            System.out.println("Attenzione: non sono presenti gerarchie per inserire nessun articolo.");
            System.out.println("Impossibile inserire una nuova offerta.");
        }
    }

    /**
     * Metodo che chiede all'utente di inserire una categoria foglia
     * passando per parametro la gerarchia selezionata.
     *
     * @param gerarchiaSelezionata una gerarchia di categorie
     * @return una categoria foglia
     * @see GerarchiaDiCategorie
     */
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

    /**
     * Metodo che chiede all'utente la gerarchia contenente la categoria
     * foglia di interesse.
     *
     * @return una gerarchia
     * @see GerarchiaDiCategorie
     */
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

    /**
     * Metodo che chiede all'utente il nome dell'articolo da inserire, 
     * controllando che il nom enon è già stato inserito.
     *
     * @return il nome dell'articolo
     * @see GestoreOfferte
     */
    private String chiediNomeArticolo() {
        var nomeArticolo = InputDati.leggiStringaNonVuota("Inserisci il titolo dell'articolo: ");
        while (this.gestoreOfferte.isOffertaGiaPresenteByNome(nomeArticolo)) {
            System.out.println("Errore: nome articolo già usato, riprovare.");
            nomeArticolo = InputDati.leggiStringaNonVuota("Reinserisci il titolo dell'ariticolo: ");
        }
        return nomeArticolo;
    }

    /**
     * Metodo che visualizza le offerte di un utente.
     *
     * @see GestoreOfferte
     */
    public void visualizzaOfferteUtente() {
        if (this.gestoreOfferte.getOfferteByUser(utente).isEmpty()) {
            System.out.println("\tAttenzione non ci sono offerte di " + utente.getUsername() + " da visualizzare.");
        } else {
            System.out.println("Offerte di " + utente.getUsername() + ":");
            this.gestoreOfferte.getOfferteByUser(utente).forEach(
                    System.out::println
            );
        }
    }

    /**
     * Metodo setter.
     *
     * @param utente l'oggetto Utente
     */
    public void setUser(Utente utente) {
        this.utente = utente;
    }

    /**
     * Metodo che permette all'utente di scegliere quale offerta
     * ritirare tra quelle aperte.
     *
     * @see GestoreOfferte
     */
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

    /**
     * Metodo che visualizza la lista delle offerte aperte (contenente
     * almeno un'offerta aperta) per una determinata categoria foglia.
     *
     * @see GestoreOfferte
     * @see GestoreGerarchie
     */
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
        System.out.println("Seleziona l'offerta aperta di interesse: ");
        for (Offerta offerta : listaOfferte) {
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
                    System.out.println("Ora attendi eventuale risposta della controparte.");
                    System.out.println("Guarda in offerte in scambio per visualizzare eventuale risposta.");

                    offertaDaBarattareA.setInfoScambio(this.gestoreScambio.getInfoDiScambio());
                    offertaDaBarattareB.setInfoScambio(this.gestoreScambio.getInfoDiScambio());

                    offertaDaBarattareA.creaLegameEModificaStatiConOfferta(offertaDaBarattareB);
                }
            }
        }
    }

    public void visualizzaProposteDiScambio() {
        gestoreOfferte.aggiornaStatoDelleOfferte();
        var offerteSelezionate = this.gestoreOfferte.getOfferteSelezionateByUser(utente);
        if (offerteSelezionate.isEmpty()) {
            System.out.println("Non ci sono proposte di scambio.");
        } else {
            for (Offerta offertaSel : offerteSelezionate) {
                if (InputDati.yesOrNo("Vuoi accettare la proposta di scambio per " +
                        offertaSel.getOffertaAccoppiata().getNomeArticolo() + ", con " +
                        offertaSel.getNomeArticolo() + "?")) {
                    accettaPropostaDiScambio(offertaSel);
                    System.out.println("Proposta di appuntamento inviata alla controparte.");
                }
            }
        }
    }

    private void accettaPropostaDiScambio(Offerta offertaSel) {
        System.out.println("Proposta di scambio accettata, ora compila gli estremi per proporre l'appuntamento.");
        offertaSel.accettaPropostaDiScambioAssociata(chiediListaCampiAppuntamento());
    }

    private ListaCampiCompilati chiediListaCampiAppuntamento() {
        var listaCampiAppuntamento = new ListaCampiCompilati();
        listaCampiAppuntamento.inserisci(new Campo("Luogo di incontro", true), chiediLuogoDiIncontro());
        listaCampiAppuntamento.inserisci(new Campo("Data di appuntamento", true), chiediDataDiIncontro());
        listaCampiAppuntamento.inserisci(new Campo("Ora di appuntamento", true), chiediOraDiIncontro());
        return listaCampiAppuntamento;
    }

    private String chiediOraDiIncontro() {
        System.out.println("Selezionare un orario di incontro");
        for (LocalTime orario : gestoreScambio.getInfoDiScambio().get().getListaOrari()) {
            if (InputDati.yesOrNo("Vuoi selezionare l'orario: " + orario + "?")) {
                return orario.toString();
            }
        }
        System.out.println("Attenzione: selezionare un orario.");
        return chiediOraDiIncontro();
    }

    private String chiediDataDiIncontro() {
        System.out.println("Selezionare un giorno di incontro");
        for (DayOfWeek giorno : gestoreScambio.getInfoDiScambio().get().getGiorni()) {
            if (InputDati.yesOrNo("Vuoi selezionare il giorno: " + giorno + "?")) {
                return giorno.name();
            }
        }
        System.out.println("Attenzione: selezionare un giorno.");
        return chiediDataDiIncontro();
    }

    private String chiediLuogoDiIncontro() {
        System.out.println("Selezionare luogo di incontro");
        for (String luogo : gestoreScambio.getInfoDiScambio().get().getListaLuoghi()) {
            if (InputDati.yesOrNo("Vuoi selezionare luogo: " + luogo + "?")) {
                return luogo;
            }
        }
        System.out.println("Attenzione: selezionare un luogo.");
        return chiediLuogoDiIncontro();
    }

    /**
     * Metodo che visualizza le offerte in scambio di un utente.
     *
     * @see GestoreOfferte
     */
    public void visualizzaOfferteInScambio() {
        List<Offerta> offerte = this.gestoreOfferte.getOfferteInScambioByUser(utente);
        if (offerte.isEmpty()) {
            System.out.println("Non ci sono offerte in scambio.");
        } else {
            for (Offerta offertaInScambio : offerte) {
                System.out.println("Proposta di scambio per " +
                        offertaInScambio.getOffertaAccoppiata().getNomeArticolo() +
                        " con " + offertaInScambio.getNomeArticolo() + ":");
                System.out.println("\t" + offertaInScambio.getOffertaAccoppiata());
                System.out.println("\t" + offertaInScambio);

                if (!Objects.isNull(offertaInScambio.getListaCampiAppuntamento())) {
                    System.out.println("\tEstremi di appuntamento proposto dalla controparte: "
                            + offertaInScambio.getListaCampiAppuntamento());
                    if (InputDati.yesOrNo("Vuoi accettare l'appuntamento?")) {
                        accettaAppuntamento(offertaInScambio);
                        System.out.println("Proposta di appuntamento accettata.");
                        System.out.println("Adesso entrambe le offerte diventano chiuse.");
                    } else {
                        System.out.println("Proponi altri estremi di appuntamento alla controparte:");
                        proponiAltroAppuntamento(offertaInScambio);
                        System.out.println("Nuova proposta di appuntamento inivata alla controparte.");
                    }
                } else {
                    System.out.println("\tIn attesa di risposta dalla controparte per la tua proposta di appuntamento.");
                }
            }
        }
    }

    private void proponiAltroAppuntamento(Offerta offertaInScambio) {
        var nuoviEstremiAppuntamento = chiediListaCampiAppuntamento();
        while (nuoviEstremiAppuntamento.equals(offertaInScambio.getListaCampiAppuntamento())) {
            System.out.println("Attenzione estremi appuntamento coincidenti con l'appuntamento già proposto.");
            System.out.println("Reinserire altri estremi nel nuovo appuntamento.");
            nuoviEstremiAppuntamento = chiediListaCampiAppuntamento();
        }
        offertaInScambio.proponiAltroAppuntamento(nuoviEstremiAppuntamento);
    }

    private void accettaAppuntamento(Offerta offertaInScambio) {
        offertaInScambio.accettaAppuntamento();
    }

    public void visualizzaUltimeRispostePerOfferteInScambio() {
        List<Offerta> offerte = this.gestoreOfferte.getOfferteInScambioByUser(utente);
        if (offerte.isEmpty()) {
            System.out.println("Non ci sono offerte in scambio. Quindi neanche risposte.");
        } else {
            for (Offerta offertaInScambio : offerte) {
                System.out.println("Proposta di scambio per " +
                        offertaInScambio.getOffertaAccoppiata().getNomeArticolo() +
                        " con " + offertaInScambio.getNomeArticolo() + ":");
                if (!Objects.isNull(offertaInScambio.getListaCampiAppuntamento())) {
                    System.out.println("\tUltima risposta di utente controparte: "
                            + offertaInScambio.getListaCampiAppuntamento());
                } else {
                    System.out.println("\tL'utente controparte non ha ancora risposto.");
                }
            }
        }
    }

    public void visualizzaOfferteInScambioEChiuseConSelezioneFoglia() {
        if (gestoreGerarchie.haGerarchie()) {
            System.out.println("Seleziona gerarchia e categoria foglia di interesse per vedere " +
                    "relative offerte in scambio e chiuse");
            var gerarchiaSelezionata = chiediGerarchia();
            var categoriaFogliaSelezionata = chiediCategoriaFogliaByGerarchia(gerarchiaSelezionata);

            var offerteInScambio =
                    this.gestoreOfferte.getOfferteInScambioByCategoriaFoglia(categoriaFogliaSelezionata);
            var offerteChiuse = this.gestoreOfferte.getofferteChiuseByCategoriaFoglia(categoriaFogliaSelezionata);

            System.out.println("Per categoria: " + categoriaFogliaSelezionata.getNome());
            System.out.println("Le offerte in scambio:");
            if (offerteInScambio.isEmpty()) {
                System.out.println("\tNon ci sono offerte in scambio per la categoria selezionata.");
            } else {
                offerteInScambio.forEach(System.out::println);
            }
            System.out.println("Le offerte chiuse:");
            if (offerteChiuse.isEmpty()) {
                System.out.println("\tNon ci sono offerte chiuse per la categoria selezionata.");
            } else {
                offerteChiuse.forEach(System.out::println);
            }
        } else {
            System.out.println("Attenzione: non sono presenti gerarchie da selezionare per visualizzare offerte.");
            System.out.println("Impossibile procedere per visualizzare offerte in scambio e chiuse.");
        }
    }
}
