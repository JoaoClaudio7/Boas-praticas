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
