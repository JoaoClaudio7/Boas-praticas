package src.br.poo.Model;
import java.util.ArrayList;
import java.util.List;

import src.br.poo.Service.Frete;

public class Pedido{
    private Cliente cliente;
    private List<Produto> itens;
    private Frete frete;
    private double valorTotal;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.frete = null;
    }

    public Pedido(){
        this.itens = new ArrayList<>();
        this.frete = null;
    }
    
    public void adicionarFrete(Frete frete) {
        this.frete = frete;
    }
    public Pedido buscarPedido(List<Pedido> pedido, int cpf){
        for (Pedido pedido2 : pedido) {
            if(pedido2.getCliente().getCpf() == cpf){
               return pedido2;
            }
        }

        return null;
    }
    public Produto buscarProduto(List<Produto> produtos, int idProduto){
        for (Produto produto2 : produtos) {
            if(produto2.getId() == idProduto){
                return produto2;
            }
        }
        return null;
    }   

    public Cliente buscarCliente(List<Cliente> itenCarrinho, int cpf){
        Cliente clienteEncontrado = null;
        for (Cliente client : itenCarrinho) {
            if(client.getCpf() == cpf){
                clienteEncontrado = client;
                return clienteEncontrado;
            }
        }

        return clienteEncontrado;
    }

    public void adicionarProduto(Produto produto){
        itens.add(produto);
    }

    public void listaProdutosClientes(){
        for (Produto produto : itens) {
            System.out.println("Id: "+produto.getId()+ " Nome: "+produto.getNomeProduto()+" preço: "+produto.getPreco());
        }
    }

    public void listarProdutos(){
        for (Produto produto : itens) {
            System.out.println("Id: "+produto.getId()+" Nome: "+produto.getNomeProduto()+" Preço: "+produto.getPreco());
        }
    }

    /*public double pesoTotal() {
        double pesoTotal = 0;
        for (Produto produto : itens) {
            pesoTotal = produto.getPeso();
        }
        return pesoTotal;
    }
*/
    public void calcularTotal() {
        double valorTotalPedido = 0;
        for (Produto produto : itens) {
            valorTotalPedido += produto.getPreco();
        }
        setValorTotal(valorTotalPedido);
    }

    public void removerPedido() {
        valorTotal = 0;
        frete.setFreteTotal(0);
        itens.clear();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFrete(Frete frete) {
        this.frete = frete;
    }
    public Frete getFrete() {
        return frete;
    }
    public double getValorTotal() {
        
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}