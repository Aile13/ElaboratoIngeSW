package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreOfferte;
import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.businesslogic.offerta.Offerta;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class OfferteService {
    private final GestoreOfferte gestoreOfferte;
    private final GestoreGerarchie gestoreGerarchie;
    private Utente utente;

    public OfferteService(GestoreOfferte gestoreOfferte, GestoreGerarchie gestoreGerarchie) {

        this.gestoreOfferte = gestoreOfferte;
        this.gestoreGerarchie = gestoreGerarchie;
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
                this.gestoreOfferte.getOfferteAperteByCategoriaFoglia(categoria).forEach(System.out::println);
            }
        } else {
            System.out.println("Attenzione: non sono presenti gerarchie da selezionare per visualizzare offerte.");
            System.out.println("Impossibile procedere per visualizzare offerte aperte.");
        }
    }
}
