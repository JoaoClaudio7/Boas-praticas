package src.br.poo.Service;
import java.util.*;
import src.br.poo.Model.*;
import src.br.poo.Utils.RelatorioUtils;

public class PedidoService {
    private List<Pedido> pedidos = new ArrayList<>();

    public void adicionarNovoPedido(Pedido pedido, Cliente clienteBuscado) {
        if (clienteBuscado != null) {
            pedido.setCliente(clienteBuscado);
            pedidos.add(pedido);
        } 
    }

    public Pedido buscarPedido(int cpf) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente() != null && pedido.getCliente().getCpf() == cpf) {
                return pedido;
            }
        }
        return null;
    }

    public double calcularTotal(Pedido pedido) {
        double total = 0;
        for (ItemPedido item : pedido.getItens()) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void removerPedido(Pedido pedido) {
    pedido.setValorTotal(0);
    pedido.setFrete(null);
    pedido.setItens(new ArrayList<>());
}


    public void listarPedido(Pedido pedido) {
        System.out.println("\n=== ITENS DO PEDIDO ===");
        for (ItemPedido item : pedido.getItens()) {
            Produto p = item.getProduto();
            System.out.println(
                "Código: " + p.getCodigo() +
                " | Nome: " + p.getNomeProduto() +
                " | Quantidade: " + item.getQuantidade() +
                " | Subtotal: R$ " + item.getSubtotal()
            );
        }
        System.out.println("Valor total do pedido: R$ " + pedido.getValorTotal());
    }

    public void listarTodasVendas() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        System.out.println("\n=== LISTAGEM DE VENDAS ===");
        for (Pedido pedido : pedidos) {
            Cliente c = pedido.getCliente();
            System.out.println("\nCliente: " + (c != null ? c.getNome() : "Não informado"));
            listarPedido(pedido);
        }
    }
        public void gerarRelatorioConsolidado() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
    }

        Map<Integer, ProdutoResumo> consolidado = new HashMap<>();
        double valorTotalGeral = 0;
        int totalItensVendidos = 0;

        for (Pedido pedido : pedidos) {
            for (ItemPedido item : pedido.getItens()) {
                Produto produto = item.getProduto();
                int codigo = produto.getCodigo();
                int qtd = item.getQuantidade();
                double valor = item.getSubtotal();

                ProdutoResumo resumo = consolidado.getOrDefault(codigo, new ProdutoResumo(produto.getNomeProduto()));
                resumo.quantidadeVendida += qtd;
                 resumo.valorTotalVendas += valor;
                consolidado.put(codigo, resumo);

                valorTotalGeral += valor;
                totalItensVendidos += qtd;
        }
    }

        List<ProdutoResumo> lista = new ArrayList<>(consolidado.values());
        lista.sort((a, b) -> Integer.compare(b.quantidadeVendida, a.quantidadeVendida));

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO CONSOLIDADO DE VENDAS ===\n\n");

        for (ProdutoResumo r : lista) {
            relatorio.append("Produto: ").append(r.nomeProduto)
                .append(" | Quantidade Vendida: ").append(r.quantidadeVendida)
                .append(" | Valor Total: R$ ").append(String.format("%.2f", r.valorTotalVendas))
                .append("\n");
    }

        relatorio.append("\nQuantidade total de itens vendidos: ").append(totalItensVendidos)
                .append("\nValor total das vendas: R$ ").append(String.format("%.2f", valorTotalGeral))
                .append("\n");

        System.out.println(relatorio.toString());

        src.br.poo.Utils.RelatorioUtils.salvarRelatorio(relatorio.toString());

}

    private static class ProdutoResumo {
        String nomeProduto;
        int quantidadeVendida;
        double valorTotalVendas;

     ProdutoResumo(String nomeProduto) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = 0;
        this.valorTotalVendas = 0;
        }
    }
}
