import java.io.*;

public class FiltroPortugues {

    public static void main(String[] args) {
        String csvEntrada = "../dados/games_formated_release_data.csv";
        String csvSaida = "../dados/portuguese_supported_games.csv";

        try {
            filtrar(csvEntrada, csvSaida);
        } catch (IOException e) {
            System.err.println("falha ao filtrar jogos com suporte a portugues: " + e.getMessage());
        }
    }

    public static void filtrar(String entrada, String saida) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(entrada));
        BufferedWriter escritor = new BufferedWriter(new FileWriter(saida));

        String cabecalho = leitor.readLine();
        escritor.write(cabecalho);
        escritor.newLine();

        //encontramos o índice da coluna "Supported languages"
        String[] colunas = cabecalho.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        int indiceIdiomas = -1;
        for (int i = 0; i < colunas.length; i++) {
            if (colunas[i].replace("\"", "").trim().equalsIgnoreCase("Supported languages")) {
                indiceIdiomas = i;
                break;
            }
        }

        if (indiceIdiomas == -1) {
            System.err.println("Coluna 'Supported languages' não encontrada.");
            leitor.close();
            escritor.close();
            return;
        }
        //verificamos se o jogo da suporte a língua portuguesa
        String linha;
        while ((linha = leitor.readLine()) != null) {
            String[] campos = linha.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if (campos.length > indiceIdiomas) {
                String idiomas = campos[indiceIdiomas].toLowerCase();
                if (idiomas.contains("portuguese")) {
                    escritor.write(linha);
                    escritor.newLine();
                }
            }
        }

        leitor.close();
        escritor.close();
    }
}
