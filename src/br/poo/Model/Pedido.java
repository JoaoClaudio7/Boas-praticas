package src.br.poo.Model;

import java.util.ArrayList;
import java.util.List;
import src.br.poo.Service.Frete;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private Frete frete;
    private double valorTotal;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.frete = null;
    }

    public Pedido() {
        this.itens = new ArrayList<>();
        this.frete = null;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade);
        itens.add(item);
        valorTotal += item.getSubtotal();
    }

    public void adicionarFrete(Frete frete) {
        this.frete = frete;
    }

    public void listarProdutos() {
        for (ItemPedido item : itens) {
            Produto p = item.getProduto();
            System.out.println(
                "Codigo: " + p.getCodigo() +
                " | Nome: " + p.getNomeProduto() +
                " | Preço: R$ " + p.getPreco() +
                " | Quantidade: " + item.getQuantidade() +
                " | Subtotal: R$ " + item.getSubtotal()
            );
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Frete getFrete() {
        return frete;
    }

    public void setFrete(Frete frete) {
        this.frete = frete;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedido> getItens() {
        return new ArrayList<>(itens);
    }

    public void setItens(List<ItemPedido> itens) {
    this.itens = itens;
}
}
