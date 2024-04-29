package br.com.curso.alura.principal;

import br.com.curso.alura.records.Ceps;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static br.com.curso.alura.principal.BuscaPrincipal.listaDeCeps;

public class GeradorDeArquivo {

    public void geraJson(List<Ceps> endereco) throws IOException {
        BuscaCep arquivoJson = new BuscaCep();
        Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setPrettyPrinting()
                    .create();
        try (FileWriter escrita = new FileWriter("ceps.json")) {
            gson.toJson(listaDeCeps, escrita);
        }
    }
}
