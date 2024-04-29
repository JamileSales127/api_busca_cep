import br.com.curso.alura.records.Ceps;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscaPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String cep= " ";
        List<Ceps> listaDeCeps = new ArrayList<>();

        while (!cep.equalsIgnoreCase("sair")) {
            System.out.println("----------------------------");
            System.out.println("API VIA CEP");
            System.out.println("----------------------------");

            System.out.println("Digite seu cep (apenas números): ");
            cep = leitura.nextLine();

            if (cep.equalsIgnoreCase("sair")){
                break;
            }

            String endereco = "https://viacep.com.br/ws/" + cep + "/json/";
//        System.out.println(endereco);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setPrettyPrinting()
                    .create();

            Ceps ceps = gson.fromJson(json, Ceps.class);
            System.out.println(ceps);


            listaDeCeps.add(ceps);

            FileWriter escrita = new FileWriter("ceps.json");
            escrita.write(gson.toJson(listaDeCeps));
            escrita.close();
        }
        System.out.println("Programa finalizado com sucesso!");

    }
}
