package br.com.curso.alura.principal;

import br.com.curso.alura.records.Ceps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscaPrincipal {
    public static List<Ceps> listaDeCeps = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String cep = " ";

        while (!cep.equalsIgnoreCase("sair")) {
            System.out.println("----------------------------");
            System.out.println("API VIA CEP");
            System.out.println("----------------------------");

            System.out.println("Digite seu cep (apenas números): ");
            cep = leitura.nextLine();

            if (cep.equalsIgnoreCase("sair")){
                break;
            }

            BuscaCep consultaCep = new BuscaCep();
            try {
                Ceps novoEndereco = consultaCep.buscaEndereco(cep);
                System.out.println(novoEndereco);
                listaDeCeps.add(novoEndereco);
            }catch (RuntimeException erro) {
                System.out.println(erro.getMessage());
                System.out.println("Finalizando aplicação");
            }

            if (!listaDeCeps.isEmpty()) {
                GeradorDeArquivo gerador = new GeradorDeArquivo();
                gerador.geraJson(listaDeCeps);
                System.out.println("Resultados salvos no arquivo ceps.json");
            } else {
                System.out.println("Nenhum resultado para salvar.");
            }


        }

    }
}
