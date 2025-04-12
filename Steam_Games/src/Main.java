public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("convertendo as datas");
            ConversorData.main(args);

            System.out.println("filtrando os jogos para Linux");
            FiltroLinux.main(args);

            System.out.println("filtrando jogos com suporte a portugues");
            FiltroPortugues.main(args);

            System.out.println("todos os arquivos foram criados");
        } catch (Exception e) {
            System.err.println("erro" + e.getMessage());
        }
    }
}