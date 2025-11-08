package src.br.poo.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;
import src.br.poo.Model.Produto;

public class RelatorioUtils {

    public static void gerarRelatorioTxt(Map<Produto, List<Double>> consolidado, double totalItens, double totalVendas) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATÓRIO CONSOLIDADO DE VENDAS ===\n\n");

        for (Map.Entry<Produto, List<Double>> entry : consolidado.entrySet()) {
            Produto produto = entry.getKey();
            List<Double> dados = entry.getValue();
            sb.append(String.format("Produto: %s | Quantidade Vendida: %.0f | Valor Total: R$ %.2f%n",
                    produto.getNomeProduto(), dados.get(0), dados.get(1)));
        }

        sb.append("\nQuantidade total de itens vendidos: ").append(totalItens)
          .append("\nValor total das vendas: R$ ").append(String.format("%.2f", totalVendas))
          .append("\n");

        System.out.println(sb.toString());

        try {
            java.io.File pasta = new java.io.File("relatorios");
            if (!pasta.exists()) pasta.mkdir();

            FileWriter writer = new FileWriter("relatorios/relatorio_vendas.txt");
            writer.write(sb.toString());
            writer.close();
            System.out.println("Relatório gerado em: relatorios/relatorio_vendas.txt");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo de relatório.");
        }
    }
}
