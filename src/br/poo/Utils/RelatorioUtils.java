package src.br.poo.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RelatorioUtils {

    private static final String CAMINHO_PASTA = "relatorios";
    private static final String NOME_ARQUIVO = "relatorio_vendas.txt";

    public static void salvarRelatorio(String conteudo) {
        criarPastaSeNaoExistir();

        try (FileWriter writer = new FileWriter(CAMINHO_PASTA + File.separator + NOME_ARQUIVO)) {
            writer.write(conteudo);
            System.out.println("\nRelatório salvo em: " + CAMINHO_PASTA + "/" + NOME_ARQUIVO);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o relatório: " + e.getMessage());
        }
    }

    private static void criarPastaSeNaoExistir() {
        File pasta = new File(CAMINHO_PASTA);
        if (!pasta.exists()) {
            boolean criada = pasta.mkdir();
            if (criada) {
                System.out.println("Pasta 'relatorios' criada.");
            }
        }
    }
}
