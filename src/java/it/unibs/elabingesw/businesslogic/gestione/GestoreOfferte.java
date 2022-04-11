package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.offerta.Offerta;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.util.List;

/**
 * @author Elia
 */
public class GestoreOfferte extends GestoreGenerico<Offerta> {
    private static final String FILE_NAME = "Offerte";

    public GestoreOfferte() {
        super(FILE_NAME);
    }

    @Override
    protected void caricaElementi() {
        super.caricaElementi();
        aggiornaStatoDelleOfferte();
    }

    public void aggiornaStatoDelleOfferte() {
        this.getListaElementi().forEach(Offerta::aggiornaStatoOfferta);
    }

    public void salvaOfferte() {
        salvaDati();
    }

    public boolean isOffertaGiaPresenteByNome(String nomeArticolo) {
        return super.isElementoInListaByNome(nomeArticolo);
    }

    public void inserisciNuovaOfferta(Offerta offerta) {
        super.inserisciElemento(offerta);
    }

    public List<Offerta> getOfferteByUser(Utente utente) {
        return getListaElementi().stream().filter(offerta -> offerta.isStessoAutore(utente)).toList();
    }

    public List<Offerta> getOfferteAperteByUser(Utente utente) {
        return getOfferteByUser(utente).stream().filter(Offerta::isOffertaAperta).toList();
    }

    public List<Offerta> getOfferteAperteByCategoriaFoglia(Categoria categoriaFoglia) {
        return getOfferteByCategoriaFoglia(categoriaFoglia).stream()
                .filter(Offerta::isOffertaAperta).toList();
    }

    public List<Offerta> getOfferteAperteByCategoriaFogliaAndExcludeUser(Categoria categoriaFoglia, Utente utente) {
        return getOfferteAperteByCategoriaFoglia(categoriaFoglia).stream()
                .filter(
                        offerta -> !offerta.isStessoAutore(utente)
                ).toList();
    }

    public List<Offerta> getOfferteSelezionateByUser(Utente utente) {
        return getOfferteByUser(utente).stream()
                .filter(Offerta::isOffertaSelezionata)
                .toList();
    }

    public List<Offerta> getOfferteInScambioByUser(Utente utente) {
        return getOfferteByUser(utente).stream()
                .filter(Offerta::isOffertaInScambio)
                .toList();
    }

    public List<Offerta> getOfferteInScambioByCategoriaFoglia(Categoria categoriaFoglia) {
        return getOfferteByCategoriaFoglia(categoriaFoglia).stream()
                .filter(Offerta::isOffertaInScambio).toList();
    }

    private List<Offerta> getOfferteByCategoriaFoglia(Categoria categoriaFoglia) {
        return getListaElementi().stream()
                .filter(offerta -> offerta.appartieneA(categoriaFoglia))
                .toList();
    }

    public List<Offerta> getOfferteChiuseByCategoriaFoglia(Categoria categoriaFoglia) {
        return getOfferteByCategoriaFoglia(categoriaFoglia).stream()
                .filter(Offerta::isOffertaChiusa).toList();
    }
}
