package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreOfferte;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
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

            this.gestoreOfferte.inserisciNuovaOfferta(new OffertaContext(nomeArticolo, utente, listaCampiCompilati, categoriaFogliaSelezionata));
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
            this.gestoreOfferte.getOfferteByUser(utente).forEach(System.out::println);
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

    /**
     * Metodo che chiede all'utente di selezionare l'offerta aperta
     * d'interesse scegliendola tra le offerte contenute dentro la
     * lista passata per parametro.
     *
     * @return l'offerta aperta
     * @see GestoreOfferte
     */
    private OffertaContext chiediOffertaByList(List<OffertaContext> listaOfferte) {
        System.out.println("Seleziona l'offerta aperta di interesse: ");
        for (OffertaContext offertaContext : listaOfferte) {
            if (InputDati.yesOrNo("Vuoi selezionare " + offertaContext.getNomeArticolo() + "?")) {
                return offertaContext;
            }
        }
        System.out.println("Errore: nessuna offerta selezionata, riprovare.");
        return chiediOffertaByList(listaOfferte);
    }

    /**
     * Metodo che permette all'utente di selezionare due offerte aperte
     * che sono barattabili, rispettando i vincoli che le due offerte
     * siano della stessa categoria e che siano di due utenti diversi.
     *
     * @see GestoreScambio
     */
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
                OffertaContext offertaContextDaBarattareA = chiediOffertaByList(listaOfferteAperteUtente);
                System.out.println("Seleziona ora una offerta aperta di medesima categoria " + "e di diverso utente che intendi barattare");
                var listaOffAperteNonUtenteStessaCat = this.gestoreOfferte.getOfferteAperteByCategoriaFogliaAndExcludeUser(offertaContextDaBarattareA.getCategoriaDiAppartenenza(), utente);
                if (listaOffAperteNonUtenteStessaCat.isEmpty()) {
                    System.out.println("Attenzione: non ci sono altre offerte da selezionare disponibili.");
                    System.out.println("Impossibile procedere con l'operazione di baratto.");
                } else {
                    var offertaDaBarattareB = chiediOffertaByList(listaOffAperteNonUtenteStessaCat);
                    System.out.println("Creazione legame tra le offerte selezionate.");
                    System.out.println("Ora attendi eventuale risposta della controparte.");
                    System.out.println("Guarda in offerte in scambio per visualizzare eventuale risposta.");

//                    offertaContextDaBarattareA.setInfoScambio(this.gestoreScambio.getInfoDiScambio());
//                    offertaDaBarattareB.setInfoScambio(this.gestoreScambio.getInfoDiScambio());

                    offertaContextDaBarattareA.creaLegameEModificaStatiConOffertaEInfoScambio(offertaDaBarattareB , this.gestoreScambio.getInfoDiScambio());
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
            for (OffertaContext offertaContextSel : offerteSelezionate) {
                if (InputDati.yesOrNo("Vuoi accettare la proposta di scambio per " + offertaContextSel.getOffertaAssociata().getNomeArticolo() + ", con " + offertaContextSel.getNomeArticolo() + "?")) {
                    accettaPropostaDiScambio(offertaContextSel);
                    System.out.println("Proposta di appuntamento inviata alla controparte.");
                }
            }
        }
    }

    /**
     * Metodo che permette all'utente di accettare una proposta di
     * scambio per un'offerta selezionata passata per parametro.
     * L'utente poi dovrà inserire gli estremi per proporre l'ap-
     * puntamento.
     *
     * @param offertaContextSel l'offerta selezionata
     */
    private void accettaPropostaDiScambio(OffertaContext offertaContextSel) {
        System.out.println("Proposta di scambio accettata, ora compila gli estremi per proporre l'appuntamento.");
        offertaContextSel.accettaPropostaDiScambioAssociata(chiediListaCampiAppuntamento());
    }

    /**
     * Metodo che chiede all'utente gli estremi di un appuntamento,
     * ossia luogo, data e ora dell'incontro.
     *
     * @return la lista degli estremi dell'appuntamento
     */
    private ListaCampiCompilati chiediListaCampiAppuntamento() {
        var listaCampiAppuntamento = new ListaCampiCompilati();
        listaCampiAppuntamento.inserisci(new Campo("Luogo di incontro", true), chiediLuogoDiIncontro());
        listaCampiAppuntamento.inserisci(new Campo("Data di appuntamento", true), chiediDataDiIncontro());
        listaCampiAppuntamento.inserisci(new Campo("Ora di appuntamento", true), chiediOraDiIncontro());
        return listaCampiAppuntamento;
    }

    /**
     * Metodo che chiede all'utente l'ora dell'appuntamento.
     *
     * @return l'orario d'incontro sottoforma di stringa
     */
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

    /**
     * Metodo che chiede all'utente la data dell'appuntamento.
     *
     * @return il giorno dell'incontro
     */
    private String chiediDataDiIncontro() {
        System.out.println("Selezionare un giorno di incontro");
        for (DayOfWeek giorno : gestoreScambio.getInfoDiScambio().get().giorni()) {
            if (InputDati.yesOrNo("Vuoi selezionare il giorno: " + giorno + "?")) {
                return giorno.name();
            }
        }
        System.out.println("Attenzione: selezionare un giorno.");
        return chiediDataDiIncontro();
    }

    /**
     * Metodo che chiede all'utente il luogo dell'appuntamento.
     *
     * @return il luogo dell'incontro
     */
    private String chiediLuogoDiIncontro() {
        System.out.println("Selezionare luogo di incontro");
        for (String luogo : gestoreScambio.getInfoDiScambio().get().listaLuoghi()) {
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
        List<OffertaContext> offerte = this.gestoreOfferte.getOfferteInScambioByUser(utente);
        if (offerte.isEmpty()) {
            System.out.println("Non ci sono offerte in scambio.");
        } else {
            for (OffertaContext offertaContextInScambio : offerte) {
                System.out.println("Proposta di scambio per " + offertaContextInScambio.getOffertaAssociata().getNomeArticolo() + " con " + offertaContextInScambio.getNomeArticolo() + ":");
                System.out.println("\t" + offertaContextInScambio.getOffertaAssociata());
                System.out.println("\t" + offertaContextInScambio);

                if (!Objects.isNull(offertaContextInScambio.getListaCampiAppuntamento())) {
                    System.out.println("\tEstremi di appuntamento proposto dalla controparte: " + offertaContextInScambio.getListaCampiAppuntamento());
                    if (InputDati.yesOrNo("Vuoi accettare l'appuntamento?")) {
                        accettaAppuntamento(offertaContextInScambio);
                        System.out.println("Proposta di appuntamento accettata.");
                        System.out.println("Adesso entrambe le offerte diventano chiuse.");
                    } else {
                        System.out.println("Proponi altri estremi di appuntamento alla controparte:");
                        proponiAltroAppuntamento(offertaContextInScambio);
                        System.out.println("Nuova proposta di appuntamento inivata alla controparte.");
                    }
                } else {
                    System.out.println("\tIn attesa di risposta dalla controparte per la tua proposta di appuntamento.");
                }
            }
        }
    }

    /**
     * Metodo che propone un altro appuntamento per poter scambiare
     * un'offerta che viene passata per parametro.
     *
     * @param offertaContextInScambio l'offerta che si sta scambiando
     */
    private void proponiAltroAppuntamento(OffertaContext offertaContextInScambio) {
        var nuoviEstremiAppuntamento = chiediListaCampiAppuntamento();
        while (nuoviEstremiAppuntamento.equals(offertaContextInScambio.getListaCampiAppuntamento())) {
            System.out.println("Attenzione estremi appuntamento coincidenti con l'appuntamento già proposto.");
            System.out.println("Reinserire altri estremi nel nuovo appuntamento.");
            nuoviEstremiAppuntamento = chiediListaCampiAppuntamento();
        }
        offertaContextInScambio.proponiAltroAppuntamento(nuoviEstremiAppuntamento);
    }

    /**
     * Metodo che permette all'utente di accettare una proposta
     * di appuntamento per l'offerta passata per parametro.
     *
     * @param offertaContextInScambio l'offerta che si sta scambiando
     */
    private void accettaAppuntamento(OffertaContext offertaContextInScambio) {
        offertaContextInScambio.accettaAppuntamento();
    }

    /**
     * Metodo che permette all'utente di vedere tutte le ultime
     * risposte che ha ricevuto per le offerte in scambio.
     *
     * @see GestoreOfferte
     */
    public void visualizzaUltimeRispostePerOfferteInScambio() {
        List<OffertaContext> offerte = this.gestoreOfferte.getOfferteInScambioByUser(utente);
        if (offerte.isEmpty()) {
            System.out.println("Non ci sono offerte in scambio. Quindi neanche risposte.");
        } else {
            for (OffertaContext offertaContextInScambio : offerte) {
                System.out.println("Proposta di scambio per " + offertaContextInScambio.getOffertaAssociata().getNomeArticolo() + " con " + offertaContextInScambio.getNomeArticolo() + ":");
                if (!Objects.isNull(offertaContextInScambio.getListaCampiAppuntamento())) {
                    System.out.println("\tUltima risposta di utente controparte: " + offertaContextInScambio.getListaCampiAppuntamento());
                } else {
                    System.out.println("\tL'utente controparte non ha ancora risposto.");
                }
            }
        }
    }

    /**
     * Metodo che visualizza la lista delle offerte in scambio
     * e chiuse per una determinata categoria foglia.
     *
     * @see GestoreOfferte
     * @see GestoreGerarchie
     */
    public void visualizzaOfferteInScambioEChiuseConSelezioneFoglia() {
        if (gestoreGerarchie.haGerarchie()) {
            System.out.println("Seleziona gerarchia e categoria foglia di interesse per vedere " + "relative offerte in scambio e chiuse");
            var gerarchiaSelezionata = chiediGerarchia();
            var categoriaFogliaSelezionata = chiediCategoriaFogliaByGerarchia(gerarchiaSelezionata);

            var offerteInScambio = this.gestoreOfferte.getOfferteInScambioByCategoriaFoglia(categoriaFogliaSelezionata);
            var offerteChiuse = this.gestoreOfferte.getOfferteChiuseByCategoriaFoglia(categoriaFogliaSelezionata);

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
