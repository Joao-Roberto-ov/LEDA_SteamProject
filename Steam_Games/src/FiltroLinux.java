import java.io.*;

public class FiltroLinux {

    public static void main(String[] args) {
        String arquivoEntrada = "../dados/games_formated_release_data.csv";
        String arquivoSaida = "../dados/games_linux.csv";

        try {
            filtrar(arquivoEntrada, arquivoSaida);
        } catch (IOException e) {
            System.err.println("falha ao tentar filtrar alguns jogos para linux: " + e.getMessage());
        }
    }

    public static void filtrar(String entrada, String saida) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(entrada));
        BufferedWriter escritor = new BufferedWriter(new FileWriter(saida));

        String cabecalho = leitor.readLine();
        escritor.write(cabecalho);
        escritor.newLine();

        //procuramos o indice da coluna "Linux"
        String[] colunas = cabecalho.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        int indiceLinux = -1;
        for (int i = 0; i < colunas.length; i++) {
            if (colunas[i].replace("\"", "").trim().equalsIgnoreCase("Linux")) {
                indiceLinux = i;
                break;
            }
        }

        if (indiceLinux == -1) {
            System.err.println("nao foi possivel achar a coluna linux.");
            leitor.close();
            escritor.close();
            return;
        }
        //Verificams quais jogos suportam Linux
        String linha;
        while ((linha = leitor.readLine()) != null) {
            String[] campos = linha.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if (campos.length > indiceLinux) {
                String valorLinux = campos[indiceLinux].replace("\"", "").trim().toLowerCase();
                if (valorLinux.equals("true")) {
                    escritor.write(linha);
                    escritor.newLine();
                }
            }
        }

        leitor.close();
        escritor.close();
    }
}
