package it.unibs.elabingesw.businesslogic.gestione;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Elia
 */
abstract class GestoreGenerico<T extends Manageable> {
    private final List<T> listaElementi;
    private final String pathRepository;
    private final Type subClassCollType;
    private final Gson gson;

    GestoreGenerico(String pathRepository, Type subClassCollType) {
        this.pathRepository = pathRepository;
        this.subClassCollType = subClassCollType;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.listaElementi = new ArrayList<>();
        caricaElementi();
    }

    void salvaDati() {
        try (FileWriter writer = new FileWriter(pathRepository)) {
            gson.toJson(listaElementi, subClassCollType, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void caricaElementi() {
        File jsonFile = new File(pathRepository);
        if (jsonFile.exists()) {

            try (FileReader reader = new FileReader(pathRepository)) {
                List<T> listElmFromJson = gson.fromJson(reader, subClassCollType);
                if (listElmFromJson != null) {
                    this.listaElementi.addAll(listElmFromJson);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    boolean isElementoInListaByNome(String nome) {
        return trovaElementoConNome(nome).isPresent();
    }

    List<T> getListaElementi() {
        return listaElementi;
    }

    Optional<T> trovaElementoConNome(String nome) {
        for (T elemento :
                listaElementi) {
            if (elemento.isStessoNome(nome))
                return Optional.of(elemento);
        }

        return Optional.empty();
    }

    void inserisciElemento(T e) {
        this.listaElementi.add(e);
    }

}
