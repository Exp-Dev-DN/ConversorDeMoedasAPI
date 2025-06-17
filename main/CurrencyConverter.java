package main;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.ApiClient;

import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_KEY = "YOUR-API-KEY"; // Coloque sua chave aqui

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("*****************************************");
            System.out.println("Seja bem-vindo(a) ao Conversor de Moedas :)");
            System.out.println("*****************************************");
            System.out.println("(1) Euro => Dólar americano");
            System.out.println("(2) Dólar americano => Euro");
            System.out.println("(3) Dólar australiano => Iene japonês");
            System.out.println("(4) Iene japonês => Dólar australiano");
            System.out.println("(5) Dólar americano => Dólar canadense");
            System.out.println("(6) Dólar canadense => Dólar americano");
            System.out.println("(0) Sair");
            System.out.print("Escolha uma opção válida: ");

            int opcao = scanner.nextInt();

            if (opcao == 0) {
                System.out.println("Saindo... Obrigado por usar o conversor!");
                break;
            }

            System.out.print("Digite o valor que deseja converter: ");
            double valor = scanner.nextDouble();

            switch (opcao) {
                case 1 -> converter("EUR", "USD", valor);
                case 2 -> converter("USD", "EUR", valor);
                case 3 -> converter("AUD", "JPY", valor);
                case 4 -> converter("JPY", "AUD", valor);
                case 5 -> converter("USD", "CAD", valor);
                case 6 -> converter("CAD", "USD", valor);
                default -> System.out.println("Opção inválida!");
            }


            System.out.println();
        }

        scanner.close();
    }

    public static void converter(String de, String para, double valor) {
        try {
            String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + de;
            String json = ApiClient.getJsonFromUrl(url);

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            double taxa = rates.get(para).getAsDouble();
            double convertido = valor * taxa;

            System.out.printf("%.2f %s = %.2f %s\n", valor, de, convertido, para);
        } catch (Exception e) {
            System.out.println("Erro ao converter moedas: " + e.getMessage());
        }
    }
}
