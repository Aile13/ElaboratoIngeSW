package it.unibs.elabingesw.view.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elia
 */
public class CategoriaRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        Categoria categoria = (Categoria) domainTypeToRender;
        return "nome='" + categoria.getNome() + '\'' +
                ", descrizione='" + categoria.getDescrizione() + '\'' +
                ", campiNativi=" + renderListaDiCampiNativi(categoria.getCampiNativi());
    }

    private String renderListaDiCampiNativi(List<Campo> campiNativi){
        return "[" + campiNativi.stream().map(campo -> new CampoRenderer().render(campo)).collect(Collectors.joining(", ")) +
                "]";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return false;
    }
}
