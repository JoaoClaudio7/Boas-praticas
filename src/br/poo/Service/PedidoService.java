package src.br.poo.Service;
import java.util.*;
import src.br.poo.Model.*;


public class PedidoService {
    List<Pedido> pedidos = new ArrayList<>();

    public void adicionarNovoPedido(Pedido pedido, Cliente clienteBuscado) {
        if(clienteBuscado != null){
            pedido.setCliente(clienteBuscado);
            pedidos.add(pedido);
        }else {
            System.out.println("Cliente nao encontrado!");
        }
    }

    public Pedido buscarPedido(int cpf){
        for (Pedido pedido2 : pedidos) {
            if(pedido2.getCliente().getCpf() == cpf){
               return pedido2;
            }
        }
        return null;
    }

    public double calcularTotal(List<Produto> listaProdutos) {
        double valorTotalPedido = 0;
        for (Produto produto : listaProdutos) {
            valorTotalPedido += produto.getPreco();
        }
        return valorTotalPedido;
    }

    public void removerPedido(Pedido pedido) {
        pedido.setValorTotal(0);
        pedido.setFrete(null);
        pedido.setProdutos();
    }

    public void ListarPedido(Pedido listarPedido) {
        List<Produto> produtos = listarPedido.getProdutos();
        for (Produto pedido : produtos) {
            System.out.println("Id do produto: " + pedido.getCodigo() + "Nome do produto: "+pedido.getNomeProduto() + " Preço: " + pedido.getPreco());
        }
    }

}