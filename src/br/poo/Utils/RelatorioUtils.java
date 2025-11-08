package src.br.poo.Utils;
import java.io.FileWriter;
import java.util.List;

public class RelatorioUtils {
    public static void gerarRelatorioTxt(List<?> lista, double valorTotalGeral, int totalItensVendidos) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO CONSOLIDADO DE VENDAS ===\n\n");

        lista.forEach(obj -> {
            try {
                var field = obj.getClass().getDeclaredFields();
                String nome = field[0].get(obj).toString();
                int qtd = (int) field[1].get(obj);
                double valor = (double) field[2].get(obj);
                relatorio.append(String.format("Produto: %s | Quantidade Vendida: %d | Valor Total: R$ %.2f%n", nome, qtd, valor));
            } catch (Exception ignored) {}
        });

        relatorio.append("\nQuantidade total de itens vendidos: ").append(totalItensVendidos)
                .append("\nValor total das vendas: R$ ").append(String.format("%.2f", valorTotalGeral))
                .append("\n");

        System.out.println(relatorio);

        try (FileWriter writer = new FileWriter("relatorios/relatorio_vendas.txt")) {
            new java.io.File("relatorios").mkdirs();
            writer.write(relatorio.toString());
            System.out.println("Relatório salvo em: relatorios/relatorio_vendas.txt");
        } catch (Exception e) {
            System.out.println("Erro ao salvar relatório: " + e.getMessage());
        }
    }
}
