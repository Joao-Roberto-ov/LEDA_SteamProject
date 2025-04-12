import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConversorData {

    public static void main(String[] args) {
        String csvOriginal = "../dados/games.csv";
        String csvTemp = "../dados/games_temp.csv";
        String csvFinal = "../dados/games_formated_release_data.csv";

        try {
            alterarCabecalho(csvOriginal, csvTemp);
            corrigirDatas(csvTemp, csvFinal);

            //apagar o arqui temporario
            File temp = new File(csvTemp);
            if (temp.delete()) {
                
            } else {
                System.out.println("nao deu para deletar o arquivo temporario.");
            }

        } catch (IOException e) {
            System.err.println("erro ao tentar executar: " + e.getMessage());
        }
    }

    //criamos o arquivo temporario com o cabecalho ajeitado
    private static void alterarCabecalho(String entrada, String saida) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(entrada));
        BufferedWriter escritor = new BufferedWriter(new FileWriter(saida));

        String cabecalho = leitor.readLine();
        if (cabecalho != null) {
            //troca o nome da coluna
            cabecalho = cabecalho.replace("DiscountDLC count", "Discount,DLC count");
            escritor.write(cabecalho);
            escritor.newLine();
        }

        //copiamos os dados sem alterar nada
        String linha;
        while ((linha = leitor.readLine()) != null) {
            escritor.write(linha);
            escritor.newLine();
        }

        leitor.close();
        escritor.close();
    }

    //corrigimos as datas no arquivo final
    private static void corrigirDatas(String entrada, String saida) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(entrada));
        BufferedWriter escritor = new BufferedWriter(new FileWriter(saida));

        String cabecalho = leitor.readLine();
        if (cabecalho != null) {
            escritor.write(cabecalho);
            escritor.newLine();
        }

        //procuramos o indice da coluna "Release date"
        String[] colunas = cabecalho.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        int indiceData = -1;
        for (int i = 0; i < colunas.length; i++) {
            if (colunas[i].replace("\"", "").trim().equalsIgnoreCase("Release date")) {
                indiceData = i;
                break;
            }
        }

        String linha;
        while ((linha = leitor.readLine()) != null) {
            String[] campos = linha.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if (campos.length <= indiceData) {
                escritor.write(linha);
                escritor.newLine();
                continue;
            }

            String dataOriginal = campos[indiceData].replace("\"", "").trim();
            String dataCorrigida = "\"" + formatarData(dataOriginal) + "\"";
            campos[indiceData] = dataCorrigida;

            for (int i = 0; i < campos.length; i++) {
                escritor.write(campos[i]);
                if (i < campos.length - 1) escritor.write(",");
            }
            escritor.newLine();
        }

        leitor.close();
        escritor.close();
    }

    //fomos fazer a conversão dos formatos disponiveis para dd/mm/aaaa
    private static String formatarData(String data) {
        if (data == null || data.isEmpty()) return data;

        SimpleDateFormat[] formatosEntrada = new SimpleDateFormat[] {
            new SimpleDateFormat("MM/yyyy"),
            new SimpleDateFormat("MM/dd/yyyy"),
            new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
        };
        SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

        for (SimpleDateFormat formato : formatosEntrada) {
            try {
                Date d = formato.parse(data);
                return formatoSaida.format(d);
            } catch (ParseException e) {
                //esse trecho testa todos os formatos
            }
        }

        return data; //se nao der para converter, ele retorna a data normal
    }
}
