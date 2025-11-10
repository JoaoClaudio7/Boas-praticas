package src.br.poo.Model;

import java.util.ArrayList;
import java.util.List;

public class Vendas {
    private List<ItemPedido> itens;
    private double valorTotal;

    public Vendas() {
        this.itens = new ArrayList<>();
    }


    public void adicionarItem(Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade);
        itens.add(item);
        valorTotal += item.getSubtotal();
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
