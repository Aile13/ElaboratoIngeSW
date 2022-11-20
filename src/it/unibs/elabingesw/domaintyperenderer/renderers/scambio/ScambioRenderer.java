package it.unibs.elabingesw.domaintyperenderer.renderers.scambio;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.domaintyperenderer.SelectableDomainTypeRenderer;

import java.util.List;

/**
 * @author Elia
 */
public class ScambioRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        Scambio scambio = (Scambio) domainTypeToRender;
        return "Scambio{" + "\n"
                + "\tpiazza='" + scambio.piazza() +
                "',\n" + "\tlistaLuoghi=" + scambio.listaLuoghi() +
                ",\n" + "\tgiorni=" + scambio.giorni() + "\n" +
                "\tintervalliOrari=" + renderListaDiIntervalliOrari(scambio.intervalliOrari()) +
                ",\n" + "\tScadenza=" + scambio.scadenza() +
                "\n" + '}';
    }

    private String renderListaDiIntervalliOrari(List<IntervalloOrario> intervalliOrari) {
        return "[" + String.join(", ", (CharSequence) intervalliOrari.stream().map(intervalloOrario -> new IntervalloOrarioRenderer().render(intervalloOrario))) +
                "]";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof Scambio;
    }
}
