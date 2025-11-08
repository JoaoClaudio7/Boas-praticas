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
            System.out.println("\nNenhuma venda registrada.");
            return;
        }

        double totalGeral = 0;
        System.out.println("\n=== LISTAGEM DE TODAS AS VENDAS ===");

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            Cliente cliente = pedido.getCliente();

            System.out.println("\nVenda #" + (i + 1));
            System.out.println("Cliente: " + (cliente != null ? cliente.getNome() : "Não informado"));
            System.out.println("Itens do pedido:");

            double totalPedido = 0;

            for (ItemPedido item : pedido.getItens()) {
                Produto produto = item.getProduto();
                double subtotal = item.getSubtotal();
                totalPedido += subtotal;

                System.out.println(
                    "  - Código: " + produto.getCodigo() +
                    " | Produto: " + produto.getNomeProduto() +
                    " | Quantidade: " + item.getQuantidade() +
                    " | Subtotal: R$ " + String.format("%.2f", subtotal)
                );
            }

            System.out.println("Total da venda: R$ " + String.format("%.2f", totalPedido));
            totalGeral += totalPedido;
        }

        System.out.println("\n=== RESUMO FINAL ===");
        System.out.println("Total geral de vendas: R$ " + String.format("%.2f", totalGeral));
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
