package src.br.poo.Model;
import java.util.ArrayList;
import java.util.List;

import src.br.poo.Service.Frete;

public class Pedido{
    private Cliente cliente;
    private List<Produto> produtos;
    private Frete frete;
    private double valorTotal;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
        this.frete = null;
    }

    public Pedido(){
        this.produtos = new ArrayList<>();
        this.frete = null;
    }
    
    public void adicionarFrete(Frete frete) {
        this.frete = frete;
    }
   
    public Produto buscarProduto(List<Produto> produtos, int idProduto){
        for (Produto produto2 : produtos) {
            if(produto2.getCodigo() == idProduto){
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
        produtos.add(produto);
    }

    public void listarProdutos(){
        for (Produto produto : produtos) {
            System.out.println("Id: "+produto.getCodigo()+" Nome: "+produto.getNomeProduto()+" Preço: "+produto.getPreco());
        }
    }

    /*public double pesoTotal() {
        double pesoTotal = 0;
        for (Produto produto : produtos) {
            pesoTotal = produto.getPeso();
        }
        return pesoTotal;
    }
*/
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFrete(Frete frete) {
        this.frete = frete;
    }
    
    public void setProdutos(){
        produtos.clear();
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

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }
}