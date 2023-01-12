package it.unibs.elabingesw.controller.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.repository.*;
import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.OffertaContext;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreGerarchieSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreOfferteSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreScambioSerializableRepository;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.elabingesw.view.OfferteServiceView;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;

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
public class OfferteServiceController {
    private final OffertaRepository offertaRepository;
    private final GerarchiaRepository gerarchiaRepository;
    private final ScambioRepository scambioRepository;

    private final OfferteServiceView view = new OfferteServiceView();
    private Utente utente;

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreOfferte e un oggetto GestoreGerarchie.
     *
     * @param offertaRepository
     * @param gerarchiaRepository
     * @see GestoreOfferteSerializableRepository
     * @see GestoreGerarchieSerializableRepository
     */
    public OfferteServiceController(OffertaRepository offertaRepository, GerarchiaRepository gerarchiaRepository, ScambioRepository scambioRepository) {
        this.offertaRepository = offertaRepository;
        this.gerarchiaRepository = gerarchiaRepository;
        this.scambioRepository = scambioRepository;
    }

    /**
     * Metodo che crea una nuova offerta: una volta confermata la
     * sua creazione, questa viene aggiunta all'applicativo.
     */
    public void creaNuovaOfferta() {
        view.visualizzaMessaggio("Procedura di creazione nuovo articolo avviata");
        if (this.gerarchiaRepository.haGerarchie()) {
            var nomeArticolo = chiediNomeArticolo();
            var gerarchiaSelezionata = chiediGerarchia();
            Categoria categoriaFogliaSelezionata = chiediCategoriaFogliaByGerarchia(gerarchiaSelezionata);
            var listaCampiCompilati = new ListaCampiCompilati(gerarchiaSelezionata, categoriaFogliaSelezionata);
            ListaCampiCompilatiServiceController.compila(listaCampiCompilati);

            this.offertaRepository.inserisciNuovaOfferta(new OffertaContext(nomeArticolo, utente, listaCampiCompilati, categoriaFogliaSelezionata));
            view.visualizzaMessaggio("Offerta inserita.");
        } else {
            view.visualizzaMessaggio("Attenzione: non sono presenti gerarchie per inserire nessun articolo.");
            view.visualizzaMessaggio("Impossibile inserire una nuova offerta.");
        }
    }

    /**
     * Metodo che chiede all'utente d'inserire una categoria foglia
     * passando per parametro la gerarchia selezionata.
     *
     * @param gerarchiaSelezionata una gerarchia di categorie
     * @return una categoria foglia
     * @see GerarchiaDiCategorie
     */
    private Categoria chiediCategoriaFogliaByGerarchia(GerarchiaDiCategorie gerarchiaSelezionata) {
        view.visualizzaMessaggio("Seleziona la categoria foglia di interesse: ");
        for (Categoria categoria : gerarchiaSelezionata.getListaDiCategoriaFoglia()) {
            if (view.chiediSeSelezionareCategoriaFogliaByNome(categoria.getNome())) {
                return categoria;
            }
        }
        view.visualizzaMessaggio("Errore: nessuna categoria selezionata, riprovare.");
        return chiediCategoriaFogliaByGerarchia(gerarchiaSelezionata);
    }

    /**
     * Metodo che chiede all'utente la gerarchia contenente la categoria
     * foglia d'interesse.
     *
     * @return una gerarchia
     * @see GerarchiaDiCategorie
     */
    private GerarchiaDiCategorie chiediGerarchia() {
        view.visualizzaMessaggio("Seleziona la gerarchia che contiene la categoria foglia di interesse: ");
        for (GerarchiaDiCategorie gerarchia : this.gerarchiaRepository.getListaGerarchie()) {
            if (view.chiediSeSelezionareGerarchiaByNome(gerarchia.getNome())) {
                return gerarchia;
            }
        }
        view.visualizzaMessaggio("Errore: nessuna gerarchia selezionata, riprovare.");
        return chiediGerarchia();
    }

    /**
     * Metodo che chiede all'utente il nome dell'articolo da inserire,
     * controllando che il nome non è già stato inserito.
     *
     * @return il nome dell'articolo
     * @see GestoreOfferteSerializableRepository
     */
    private String chiediNomeArticolo() {
        var nomeArticolo = view.chiediTitoloDiArticolo();
        while (this.offertaRepository.isOffertaPresenteByNome(nomeArticolo)) {
            view.visualizzaMessaggio("Errore: nome articolo già usato, riprovare.");
            nomeArticolo = view.chiediNewTitoloDiArticolo();
        }
        return nomeArticolo;
    }

    /**
     * Metodo che visualizza le offerte di un utente.
     *
     * @see GestoreOfferteSerializableRepository
     */
    public void visualizzaOfferteUtente() {
        if (this.offertaRepository.getOfferteByUser(utente).isEmpty()) {
            view.visualizzaMessaggio("\tAttenzione non ci sono offerte di " + utente.getUsername() + " da visualizzare.");
        } else {
            view.visualizzaMessaggio("Offerte di " + utente.getUsername() + ":");
            this.offertaRepository.getOfferteByUser(utente).forEach(offertaContext ->
                    view.visualizzaMessaggio(new CompositeDomainTypeRenderer().render(offertaContext))
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
     * @see GestoreOfferteSerializableRepository
     */
    public void ritiraOfferte() {
        if (this.offertaRepository.getOfferteAperteByUser(this.utente).isEmpty()) {
            view.visualizzaMessaggio("\tAttenzione: non ci sono offerte aperte da ritirare.");
        } else {
            view.visualizzaMessaggio("Seleziona quali offerte aperte vuoi ritirare: ");
            this.offertaRepository.getOfferteAperteByUser(this.utente).forEach(offerta -> {
                if (view.chiediSeRitirareOffertaByNomeArticolo(offerta.getNomeArticolo())) {
                    offerta.ritiraOfferta();
                }
            });
        }
    }

    /**
     * Metodo che visualizza la lista delle offerte aperte (contenente
     * almeno un'offerta aperta) per una determinata categoria foglia.
     *
     * @see GestoreOfferteSerializableRepository
     * @see GestoreGerarchieSerializableRepository
     */
    public void visualizzaOfferteAperteConSelezioneFoglia() {
        if (this.gerarchiaRepository.haGerarchie()) {
            GerarchiaDiCategorie gerarchia = chiediGerarchia();
            Categoria categoria = chiediCategoriaFogliaByGerarchia(gerarchia);
            if (this.offertaRepository.getOfferteAperteByCategoriaFoglia(categoria).isEmpty()) {
                view.visualizzaMessaggio("Attenzione: nessuna offerta aperta per questa categoria foglia.");
            } else {
                view.visualizzaMessaggio("Elenco offerte data la categoria selezionata: ");
                this.offertaRepository.getOfferteAperteByCategoriaFoglia(categoria).forEach(offertaContext ->
                        view.visualizzaMessaggio(new CompositeDomainTypeRenderer().render(offertaContext))
                );

            }
        } else {
            view.visualizzaMessaggio("Attenzione: non sono presenti gerarchie da selezionare per visualizzare offerte.");
            view.visualizzaMessaggio("Impossibile procedere per visualizzare offerte aperte.");
        }
    }

    /**
     * Metodo che chiede all'utente di selezionare l'offerta aperta
     * d'interesse scegliendola tra le offerte contenute dentro la
     * lista passata per parametro.
     *
     * @return l'offerta aperta
     * @see GestoreOfferteSerializableRepository
     */
    private OffertaContext chiediOffertaByList(List<OffertaContext> listaOfferte) {
        view.visualizzaMessaggio("Seleziona l'offerta aperta di interesse: ");
        for (OffertaContext offertaContext : listaOfferte) {
            if (view.chiediSeSelezionareOffertaByNomeArticolo(offertaContext.getNomeArticolo())) {
                return offertaContext;
            }
        }
        view.visualizzaMessaggio("Errore: nessuna offerta selezionata, riprovare.");
        return chiediOffertaByList(listaOfferte);
    }

    /**
     * Metodo che permette all'utente di selezionare due offerte aperte
     * che sono barattabili, rispettando i vincoli che le due offerte
     * siano della stessa categoria e che siano di due utenti diversi.
     *
     * @see GestoreScambioSerializableRepository
     */
    public void selezionaUnaOffertaApertaPerBaratto() {
        if (scambioRepository.isInfoScambioDaConfigurare()) {
            view.visualizzaMessaggio("Attenzione: criteri e tempistiche di scambio non ancora impostati.");
            view.visualizzaMessaggio("Impossibile procedere con l'operazione di baratto.");
        } else {
            view.visualizzaMessaggio("Seleziona una tua offerta aperta che intendi barattare");
            var listaOfferteAperteUtente = this.offertaRepository.getOfferteAperteByUser(utente);
            if (listaOfferteAperteUtente.isEmpty()) {
                view.visualizzaMessaggio("Attenzione: non ci sono offerte aperte da selezionare.");
                view.visualizzaMessaggio("Impossibile procedere con l'operazione di baratto.");
            } else {
                OffertaContext offertaContextDaBarattareA = chiediOffertaByList(listaOfferteAperteUtente);
                view.visualizzaMessaggio("Seleziona ora una offerta aperta di medesima categoria e di diverso utente che intendi barattare");
                var listaOffAperteNonUtenteStessaCat = this.offertaRepository.getOfferteAperteByCategoriaFogliaAndExcludeUser(offertaContextDaBarattareA.getCategoriaDiAppartenenza(), utente);
                if (listaOffAperteNonUtenteStessaCat.isEmpty()) {
                    view.visualizzaMessaggio("Attenzione: non ci sono altre offerte da selezionare disponibili.");
                    view.visualizzaMessaggio("Impossibile procedere con l'operazione di baratto.");
                } else {
                    var offertaDaBarattareB = chiediOffertaByList(listaOffAperteNonUtenteStessaCat);
                    view.visualizzaMessaggio("Creazione legame tra le offerte selezionate.");
                    view.visualizzaMessaggio("Ora attendi eventuale risposta della controparte.");
                    view.visualizzaMessaggio("Guarda in offerte in scambio per visualizzare eventuale risposta.");

                    offertaContextDaBarattareA.creaLegameEModificaStatiConOffertaEInfoScambio(offertaDaBarattareB, this.scambioRepository.getInfoDiScambio());
                }
            }
        }
    }

    public void visualizzaProposteDiScambio() {
        offertaRepository.aggiornaStatoDelleOfferte();
        var offerteSelezionate = this.offertaRepository.getOfferteSelezionateByUser(utente);
        if (offerteSelezionate.isEmpty()) {
            view.visualizzaMessaggio("Non ci sono proposte di scambio.");
        } else {
            for (OffertaContext offertaContextSel : offerteSelezionate) {
                if (view.chiediSeAccettarePropostaDiScambioByNomiArticoli(offertaContextSel.getOffertaAssociata().getNomeArticolo(), offertaContextSel.getNomeArticolo())) {
                    accettaPropostaDiScambio(offertaContextSel);
                    view.visualizzaMessaggio("Proposta di appuntamento inviata alla controparte.");
                }
            }
        }
    }

    /**
     * Metodo che permette all'utente di accettare una proposta di
     * scambio per un'offerta selezionata passata per parametro.
     * L'utente poi dovrà inserire gli estremi per proporre l'appuntamento.
     *
     * @param offertaContextSel l'offerta selezionata
     */
    private void accettaPropostaDiScambio(OffertaContext offertaContextSel) {
        view.visualizzaMessaggio("Proposta di scambio accettata, ora compila gli estremi per proporre l'appuntamento.");
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
     * @return l'orario d'incontro sotto forma di stringa
     */
    private String chiediOraDiIncontro() {
        view.visualizzaMessaggio("Selezionare un orario di incontro");
        for (LocalTime orario : scambioRepository.getInfoDiScambio().get().getListaOrari()) {
            if (view.chiediSeSelezionareOrario(orario)) {
                return orario.toString();
            }
        }
        view.visualizzaMessaggio("Attenzione: selezionare un orario.");
        return chiediOraDiIncontro();
    }

    /**
     * Metodo che chiede all'utente la data dell'appuntamento.
     *
     * @return il giorno dell'incontro
     */
    private String chiediDataDiIncontro() {
        view.visualizzaMessaggio("Selezionare un giorno di incontro");
        for (DayOfWeek giorno : scambioRepository.getInfoDiScambio().get().giorni()) {
            if (view.chiediSeSelezionareGiorno(giorno)) {
                return giorno.name();
            }
        }
        view.visualizzaMessaggio("Attenzione: selezionare un giorno.");
        return chiediDataDiIncontro();
    }

    /**
     * Metodo che chiede all'utente il luogo dell'appuntamento.
     *
     * @return il luogo dell'incontro
     */
    private String chiediLuogoDiIncontro() {
        view.visualizzaMessaggio("Selezionare luogo di incontro");
        for (String luogo : scambioRepository.getInfoDiScambio().get().listaLuoghi()) {
            if (view.chiediSeSelezionareLuogo(luogo)) {
                return luogo;
            }
        }
        view.visualizzaMessaggio("Attenzione: selezionare un luogo.");
        return chiediLuogoDiIncontro();
    }

    /**
     * Metodo che visualizza le offerte in scambio di un utente.
     *
     * @see GestoreOfferteSerializableRepository
     */
    public void visualizzaOfferteInScambio() {
        List<OffertaContext> offerte = this.offertaRepository.getOfferteInScambioByUser(utente);
        if (offerte.isEmpty()) {
            view.visualizzaMessaggio("Non ci sono offerte in scambio.");
        } else {
            for (OffertaContext offertaContextInScambio : offerte) {
                view.visualizzaMessaggio("Proposta di scambio per " + offertaContextInScambio.getOffertaAssociata().getNomeArticolo() + " con " + offertaContextInScambio.getNomeArticolo() + ":");
                view.visualizzaMessaggio("\t" + new CompositeDomainTypeRenderer().render(offertaContextInScambio.getOffertaAssociata()));
                view.visualizzaMessaggio("\t" + new CompositeDomainTypeRenderer().render(offertaContextInScambio));

                if (!Objects.isNull(offertaContextInScambio.getListaCampiAppuntamento())) {
                    view.visualizzaMessaggio("\tEstremi di appuntamento proposto dalla controparte: " + new CompositeDomainTypeRenderer().render(offertaContextInScambio.getListaCampiAppuntamento()));
                    if (view.chiediSeAccettareAppuntamento()) {
                        accettaAppuntamento(offertaContextInScambio);
                        view.visualizzaMessaggio("Proposta di appuntamento accettata.");
                        view.visualizzaMessaggio("Adesso entrambe le offerte diventano chiuse.");
                    } else {
                        view.visualizzaMessaggio("Proponi altri estremi di appuntamento alla controparte:");
                        proponiAltroAppuntamento(offertaContextInScambio);
                        view.visualizzaMessaggio("Nuova proposta di appuntamento inviata alla controparte.");
                    }
                } else {
                    view.visualizzaMessaggio("\tIn attesa di risposta dalla controparte per la tua proposta di appuntamento.");
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
            view.visualizzaMessaggio("Attenzione estremi appuntamento coincidenti con l'appuntamento già proposto.");
            view.visualizzaMessaggio("Reinserire altri estremi nel nuovo appuntamento.");
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
     * @see GestoreOfferteSerializableRepository
     */
    public void visualizzaUltimeRispostePerOfferteInScambio() {
        List<OffertaContext> offerte = this.offertaRepository.getOfferteInScambioByUser(utente);
        if (offerte.isEmpty()) {
            view.visualizzaMessaggio("Non ci sono offerte in scambio. Quindi neanche risposte.");
        } else {
            for (OffertaContext offertaContextInScambio : offerte) {
                view.visualizzaMessaggio("Proposta di scambio per " + offertaContextInScambio.getOffertaAssociata().getNomeArticolo() + " con " + offertaContextInScambio.getNomeArticolo() + ":");
                if (!Objects.isNull(offertaContextInScambio.getListaCampiAppuntamento())) {
                    view.visualizzaMessaggio("\tUltima risposta di utente controparte: " + new CompositeDomainTypeRenderer().render(offertaContextInScambio.getListaCampiAppuntamento()));
                } else {
                    view.visualizzaMessaggio("\tL'utente controparte non ha ancora risposto.");
                }
            }
        }
    }

    /**
     * Metodo che visualizza la lista delle offerte in scambio
     * e chiuse per una determinata categoria foglia.
     *
     * @see GestoreOfferteSerializableRepository
     * @see GestoreGerarchieSerializableRepository
     */
    public void visualizzaOfferteInScambioEChiuseConSelezioneFoglia() {
        if (gerarchiaRepository.haGerarchie()) {
            view.visualizzaMessaggio("Seleziona gerarchia e categoria foglia di interesse per vedere relative offerte in scambio e chiuse");
            var gerarchiaSelezionata = chiediGerarchia();
            Categoria categoriaFogliaSelezionata = chiediCategoriaFogliaByGerarchia(gerarchiaSelezionata);

            var offerteInScambio = this.offertaRepository.getOfferteInScambioByCategoriaFoglia(categoriaFogliaSelezionata);
            var offerteChiuse = this.offertaRepository.getOfferteChiuseByCategoriaFoglia(categoriaFogliaSelezionata);

            view.visualizzaMessaggio("Per categoria: " + categoriaFogliaSelezionata.getNome());
            view.visualizzaMessaggio("Le offerte in scambio:");
            if (offerteInScambio.isEmpty()) {
                view.visualizzaMessaggio("\tNon ci sono offerte in scambio per la categoria selezionata.");
            } else {
                offerteInScambio.forEach(offertaContext -> view.visualizzaMessaggio(new CompositeDomainTypeRenderer().render(offertaContext)));
            }
            view.visualizzaMessaggio("Le offerte chiuse:");
            if (offerteChiuse.isEmpty()) {
                view.visualizzaMessaggio("\tNon ci sono offerte chiuse per la categoria selezionata.");
            } else {
                offerteChiuse.forEach(offertaContext -> view.visualizzaMessaggio(new CompositeDomainTypeRenderer().render(offertaContext)));
            }
        } else {
            view.visualizzaMessaggio("Attenzione: non sono presenti gerarchie da selezionare per visualizzare offerte.");
            view.visualizzaMessaggio("Impossibile procedere per visualizzare offerte in scambio e chiuse.");
        }
    }
}
