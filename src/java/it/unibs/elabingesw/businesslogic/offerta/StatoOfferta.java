package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe StatoOfferta per la gestione delle operazioni
 * che si possono effettuare sui diversi tipi di offerte.
 * <p>
 * Invariante di classe: a ogni offerta è associato
 * in ogni momento uno stato, e questo è realizzato
 * associando sempre un valore all'attributo
 * statoOffertaEnum, quindi l'invariante è che
 * questo attributo è sempre valorizzato e aggiornato
 * secondo la logica evolutiva degli stati delle offerte.
 * Quest'ultima si individua attraverso i vari metodi
 * della classe.
 * Inoltre come invariante abbiamo che la dataCreazioneStato
 * viene aggiornato ogni volta sussiste un cambio di stato
 * da parte dell'offerta, annotando infatti quando questa ha
 * cambiato stato l'ultima volta.
 * Nota: gli attributi scambio e listaCampiAppuntamento
 * vengono usati solo quando l'offerta si trova in stato di scambio.
 * L'attributo altraOfferta non è usato solo quando l'offerta è
 * in stato di offerta aperta od offerta ritirata.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
class StatoOfferta implements Serializable {

    private StatoOffertaEnum statoOffertaEnum;
    private LocalDate dataCreazioneStato;
    private Scambio scambio;

    private Offerta altraOfferta;
    private ListaCampiCompilati listaCampiAppuntamento;

    /**
     * Costruttore di classe
     * <p>
     * Post condizione: l'offerta appena creata è
     * sempre all'inizio nello stato di offerta aperta.
     * E non vi è quindi associato alcun appuntamento (null).
     * E inoltre si imposta la data sull'acquisizione di
     * questo stato da parte dell'offerta.
     */
    public StatoOfferta() {
        this.statoOffertaEnum = StatoOffertaEnum.APERTA;
        this.listaCampiAppuntamento = null;
        impostaData();
    }

    /**
     * Metodo getter.
     * <p>
     * Precondizione: assumo che questo metodo venga chiamato
     * solo quando l'offerta è in stato di offerta in scambio.
     * Post condizione: il valore ritornato è null o nel caso che
     * l'altra controparte non abbia ancora negato la proposta
     * di appuntamento e ne abbia quindi formulato una alternativa che
     * poi viene settata nell'attributo del getter. O nel caso che
     * l'altra controparte non abbia ancora accettato la proposta
     * di appuntamento e quindi così poi l'attributo del getter
     * verrebbe inizializzato con l'appuntamento che questa offerta
     * aveva proposto all'altra.
     * Se il valore è inizializzato è per uno dei due casi trattati sopra
     * e viene ritornato quello. Facendo così capire all'offerta che chiama il getter che c'è
     * un appuntamento a cui "reagire".
     *
     * @return la lista dei campi che definiscono un appuntamento
     */
    public ListaCampiCompilati getListaCampiAppuntamento() {
        return listaCampiAppuntamento;
    }

    /**
     * Metodo setter.
     * <p>
     * Precondizione: questo metodo può essere invocato solo
     * quando l'offerta che lo richiama è in particolari condizioni:
     * Quindi si deve invocare tal metodo, solo sull'offerte in stato di offerta accoppiata, di cui si è accettata
     * la proposta di scambio e si vuole quindi "proporre" a tale offerta un appuntamento perché lei possa
     * poi accettarlo, e quindi far incontrare le persone, o rifiutarlo e proporne un altro direttamente lei.
     * Quindi in alternativa questo metodo si deve invocare solo sulle offerte in scambio associate, in
     * modo da permettere la trasmissione di una nuova proposta di appuntamento all'offerta su cui si sta
     * invocando il metodo. In quest'ultimo caso, bisogna avere che l'offerta, su cui si sta invocando il metodo,
     * aveva precedentemente proposte lei un appuntamento all'offerta controparte, e che questa l'abbia letto e rifiutato e
     * ne abbia quindi proposto uno lei con il setter in questione, con la specifica che questo nuovo appuntamento sia
     * diverso da quello che aveva ricevuto.
     *
     * @param listaCampiAppuntamento lista dei campi compilati che definiscono un appuntamento
     */
    private void setListaCampiAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.listaCampiAppuntamento = listaCampiAppuntamento;
    }

    /**
     * Metodo setter.
     * <p>
     * Precondizione: assumo che questo metodo venga chiamato
     * solo quando l'offerta è in stato di offerta accoppiata o in stato di offerta selezionata.
     * E deve essere chiamato per settare le info di scambio, quando l'offerta
     * è appena passata in uno dei due stati sopra indicati.
     * Assumo inoltre che il parametro non sia nullo e sia correttamente
     * inizializzato, ovvero che contenga l'istanza di Scambio che è registrata nel sistema.
     * Di questo parametro poi si usa l'attributo scadenza per determinare la corretta
     * evoluzione delle offerte nel loro stato corrente e negli stati successivi.
     *
     * @param scambio l'oggetto di tipo Scambio
     */
    public void setScambio(Scambio scambio) {
        this.scambio = scambio;
    }

    /**
     * Metodo che controlla se un'offerta è aperta o meno.
     *
     * @return TRUE se l'offerta è aperta
     * FALSE se l'offerta non è aperta
     */
    public boolean isAperta() {
        return this.statoOffertaEnum == StatoOffertaEnum.APERTA;
    }

    /**
     * Metodo che ritira un'offerta trasformandone lo
     * stato in RITIRATA.
     * <p>
     * Precondizione: questo metodo è invocabile solo sulle
     * offerte correntemente in stato di offerta aperta.
     * Post condizione: l'offerta è ritirata e viene
     * annotata la data del ritiro.
     */
    public void ritiraOfferta() {
        this.statoOffertaEnum = StatoOffertaEnum.RITIRATA;
        impostaData();
    }

    /**
     * Metodo che setta un'offerta accoppiata con un'offerta
     * passata per parametro.
     * <p>
     * Precondizione: Quella del metodo che ne fa uso.
     * Post condizione: l'offerta che invoca il metodo cambia di stato da
     * offerta aperta in offerta accoppiata.
     * Inoltre si crea il legame tra quest'ultima e l'offerta passata
     * come parametro.
     * Infine si annota la data del passaggio di stato.
     *
     * @param offertaDaBarattareB l'oggetto di tipo offerta
     */
    public void setOffertaAccoppiataCon(Offerta offertaDaBarattareB) {
        this.statoOffertaEnum = StatoOffertaEnum.ACCOPPIATA;
        this.altraOfferta = offertaDaBarattareB;
        impostaData();
    }

    /**
     * Metodo che setta un'offerta selezionata e la collega con un'offerta
     * passata per parametro.
     * <p>
     * Precondizione: Quella del metodo che ne fa uso.
     * Post condizione: l'offerta che invoca il metodo cambia di stato da
     * offerta aperta in offerta selezionata.
     * Inoltre si crea il legame tra quest'ultima e l'offerta passata
     * come parametro.
     * Infine si annota la data del passaggio di stato.
     *
     * @param offertaDaBarattareA l'oggetto di tipo offerta
     */
    public void setOffertaSelezionataCon(Offerta offertaDaBarattareA) {
        this.statoOffertaEnum = StatoOffertaEnum.SELEZIONATA;
        this.altraOfferta = offertaDaBarattareA;
        impostaData();
    }

    /**
     * Metodo che imposta la data attuale.
     */
    private void impostaData() {
        this.dataCreazioneStato = LocalDate.now();
    }

    /**
     * Metodo che aggiorna lo stato di un'offerta.
     * <p>
     * Post condizione: in funzione dello stato corrente dell'offerta,
     * e della data di creazione del suo stato, e del parametro scadenza
     * delle info di scambio, si determina lo stato successivo da associare all'offerta.
     * Se l'offerta è accoppiata o selezionata o in scambio, e la data di creazione dello stato
     * è scaduta rispetto ai giorni trascorsi da essa in funzione del parametro scadenza,
     * allora questa diventa di nuovo una offerta aperta e viene cancellato il legame
     * con l'offerta collegata a essa, e viene cancellato l'eventuale proposta di appuntamento
     * che aveva al momento con se.
     * Infine al cambio di stato segue l'aggiornamento della data della sua creazione.
     * Se invece la data di creazione non è ancora scaduta o se l'offerta non è
     * in uno degli stati sopra indicati, allora lo stato e la sua data di creazione
     * rimangono immutati alla chiamata del metodo.
     */
    public void aggiornaStatoOfferta() {
        if (statoOffertaEnum == StatoOffertaEnum.ACCOPPIATA || statoOffertaEnum == StatoOffertaEnum.SELEZIONATA || statoOffertaEnum == StatoOffertaEnum.IN_SCAMBIO) {
            if (LocalDate.now().isAfter(dataCreazioneStato.plusDays(scambio.getScadenza()))) {
                this.statoOffertaEnum = StatoOffertaEnum.APERTA;
                this.altraOfferta = null;
                this.listaCampiAppuntamento = null;
                impostaData();
            }
        }
    }

    /**
     * Metodo per la formattazione che converte un oggetto nella
     * relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "StatoOfferta{" + "statoOffertaEnum=" + statoOffertaEnum + '}';
    }

    /**
     * Metodo che controlla se un'offerta è selezionata o meno.
     *
     * @return TRUE se l'offerta è selezionata
     * FALSE se l'offerta non è selezionata
     */
    public boolean isSelezionata() {
        return statoOffertaEnum == StatoOffertaEnum.SELEZIONATA;
    }

    /**
     * Metodo getter.
     * <p>
     * Precondizione: tal metodo deve essere invocato
     * solo quando l'offerta che chiama il metodo è correntemente
     * nello stato di offerta selezionata.
     *
     * @return un offerta accoppiata
     */
    public Offerta getOffertaAccoppiata() {
        return altraOfferta;
    }

    /**
     * Metodo che permette di accettare una proposta
     * di scambio associata.
     * <p>
     * Precondizione: Quella del metodo che ne fa uso.
     * Post condizione: l'offerta selezionata accetta la proposta
     * di scambio fatta dalla offerta accoppiata. Quindi entrambe
     * le offerte ora migrano nello stato di offerta in scambio,
     * con il conseguente aggiornamento delle date di creazione del nuovo stato.
     * Inoltre alla ex offerta accoppiata viene assegnata una proposta di appuntamento (parametro)
     * che è stata formulata dalla ex offerta selezionata.
     *
     * @param listaCampiAppuntamento la lista dei campi che definiscono un appuntamento
     */
    public void accettaPropostaDiScambioAssociata(ListaCampiCompilati listaCampiAppuntamento) {
        this.statoOffertaEnum = StatoOffertaEnum.IN_SCAMBIO; // B
        this.altraOfferta // A
                .getStatoOfferta().statoOffertaEnum = StatoOffertaEnum.IN_SCAMBIO;
        this.altraOfferta.getStatoOfferta().setListaCampiAppuntamento(listaCampiAppuntamento);

        this.impostaData();
        this.altraOfferta.getStatoOfferta().impostaData();
    }

    /**
     * Metodo che controlla se un'offerta è in scambio o meno.
     *
     * @return TRUE se l'offerta è in scambio
     * FALSE se l'offerta non è in scambio
     */
    public boolean isInScambio() {
        return this.statoOffertaEnum == StatoOffertaEnum.IN_SCAMBIO;
    }

    /**
     * Metodo che permette di accettare un'offerta facendole
     * cambiare lo stato in chiusa.
     * <p>
     * Precondizione: Quella del metodo che ne fa uso.
     * Post condizione: lo stato dell'offerta che chiama
     * il metodo diventa: offerta chiusa.
     * Quindi segue il corrispondente aggiornamento della data.
     * Inoltre anche la sua offerta associata migra
     * nello stato di offerta chiusa, con il conseguente aggiornamento della sua data.
     * Infine a quest'ultima offerta, che aveva proposto l'appuntamento alla sua offerta associata, è settato
     * lo stesso appuntamento nel suo specifico attributo.
     * Dato che ora l'appuntamento è stato accettato dalle parti, è quindi condiviso in entrambe, cioè se ne
     * fa l'invio anche all'offerta che l'aveva inizialmente proposto all'altra. Così ora
     * entrambe le offerte conosco gli estremi dell'appuntamento in cui realizzare il baratto.
     */
    public void accettaAppuntamento() {
        this.statoOffertaEnum = StatoOffertaEnum.CHIUSA;
        impostaData();

        this.getOffertaAccoppiata().getStatoOfferta().statoOffertaEnum = StatoOffertaEnum.CHIUSA;
        this.altraOfferta.getStatoOfferta().setListaCampiAppuntamento(this.getListaCampiAppuntamento());
        this.getOffertaAccoppiata().getStatoOfferta().impostaData();
    }

    /**
     * Metodo che permette di proporre un altro appuntamento
     * passandone i dettagli tramite una lista in ingresso.
     * <p>
     * Precondizione: Quella del metodo che ne fa uso.
     * Post condizione: l'offerta, che chiama il metodo, setta
     * nella sua offerta associata la sua proposta di appuntamento, dato che
     * quella che era stata precedentemente proposta a lei è stata rifiutata.
     * Quindi si procede a cancellare l'appuntamento precedentemente inviatole e da lei
     * scartato, per avere un campo di nuovo vuoto, e che se riempito porta l'offerta
     * chiamante a reagire a una nuova proposta di scambio; Perché quello che eventualmente sta
     * proponendo ora lei può essere a sua volta rifiutato.
     * Quindi con la nuova proposta di appuntamento si deve dare alla controparte il tempo di rispondere,
     * per fare questo lo stato delle due offerte collegate rimane il medesimo ma la data viene reimpostata,
     * "rinnovando" ed estendendo così il tempo di scadenza delle due offerte.
     *
     * @param listaCampiAppuntamento la lista dei campi che definiscono un appuntamento
     */
    public void proponiAltroAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.altraOfferta.getStatoOfferta().setListaCampiAppuntamento(listaCampiAppuntamento);
        this.setListaCampiAppuntamento(null);

        this.impostaData();
        this.altraOfferta.getStatoOfferta().impostaData();
    }

    /**
     * Metodo che controlla se un'offerta è chiusa o meno.
     *
     * @return TRUE se l'offerta è chiusa
     * FALSE se l'offerta non è chiusa
     */
    public boolean isChiusa() {
        return this.statoOffertaEnum == StatoOffertaEnum.CHIUSA;
    }
}
