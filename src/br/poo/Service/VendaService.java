package src.br.poo.Service;
import java.util.*;
import src.br.poo.Model.*;

public class VendaService {
    private List<Vendas> vendas = new ArrayList<>();

    public void adicionarNovaVenda(Vendas venda) {
        if (venda != null) {
            vendas.add(venda);
        }
    }

    public double calcularTotal(Vendas venda) {
        double total = 0;
        for (ItemPedido item : venda.getItens()) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void listarVendas(Vendas venda) {
        System.out.println("\n=== ITENS DO PEDIDO ===");
        for (ItemPedido item : venda.getItens()) {
            Produto p = item.getProduto();
            System.out.println(
                "Código: " + p.getCodigo() +
                " | Nome: " + p.getNomeProduto() +
                " | Quantidade: " + item.getQuantidade() +
                " | Subtotal: R$ " + item.getSubtotal()
            );
        }
        System.out.println("Valor total do venda: R$ " + venda.getValorTotal());
    }

    public void listarTodasVendas() {
        if (vendas.isEmpty()) {
            System.out.println("\nNenhuma venda registrada.");
            return;
        }

        double totalGeral = 0;
        System.out.println("\n=== LISTAGEM DE TODAS AS VENDAS ===");

        for (int i = 0; i < vendas.size(); i++) {
            Vendas venda = vendas.get(i);

            System.out.println("\nVenda #" + (i + 1));
            System.out.println("Itens do venda:");

            double totalPedido = 0;

            for (ItemPedido item : venda.getItens()) {
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
    
    public List<Vendas> getPedidos() {
        return this.vendas;
    }

    public boolean registrarVenda(Produto produto, Vendas venda, int quantidade) {

        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return false;
        }

        if (quantidade > produto.getQuantidade()) {
            System.out.println("Estoque insuficiente! Estoque atual: " + produto.getQuantidade());
            return false;
        }

        boolean subtraiu = produto.reduzirQuantidade(quantidade);
        if (!subtraiu) {
            System.out.println("Erro ao debitar do estoque.");
            return false;
        }

        venda.adicionarItem(produto, quantidade);
        vendas.add(venda);

        System.out.println("Venda registrada: " + quantidade + "x " + produto.getNomeProduto());
        System.out.println("Estoque restante: " + produto.getQuantidade());
        System.out.println("Valor total até agora: R$ " + venda.getValorTotal());
        return true;
    }
}